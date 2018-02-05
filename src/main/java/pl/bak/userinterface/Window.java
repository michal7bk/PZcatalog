package pl.bak.userinterface;

import pl.bak.catalog.CatalogEntry;
import pl.bak.catalog.CatalogRepository;
import pl.bak.catalog.InvalidCatalogEntryException;
import pl.bak.database.GetConstant;
import pl.bak.webdate.*;
import pl.bak.messagefactory.*;
import pl.bak.word.WordReader;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class Window extends JFrame implements Runnable, ActionListener, FocusListener {

    private CatalogEntryTableModel tableModel;
    private JTable table;
    private CatalogRepository catalogRepo = CatalogRepository.anInstance();
    private ResourceBundle mybundle = ResourceBundle.getBundle("MessagesBundle");
    private ActionFactory abstactPanel = new ActionFactory();
    private WebClient webClient= new WebClient();
    private GetConstant properites = new GetConstant();
    private final WordReader INSTANCE = new WordReader();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame(properites.getPropValues().get(7));



    private String wname;
    private String wdescription;
    private  String wSearchingName ;
    private boolean languageFlag = false;
    private String mes1 = mybundle.getString("mes1");
    private String mes2 = mybundle.getString("mes2");
    private String messerror = "Nie mozna dodac pustego rekordu";
    private String banederror = mybundle.getString("banmes");
    private String blankrow = mybundle.getString("blankrow");
    private CustJTextArea name = new CustJTextArea(Integer.valueOf(properites.getPropValues().get(4)), Integer.valueOf(properites.getPropValues().get(5)));
    private CustJTextArea description = new CustJTextArea(Integer.valueOf(properites.getPropValues().get(4)), Integer.valueOf(properites.getPropValues().get(5)));
    private CustJTextArea searchingName = new CustJTextArea(1, 8);
    private JLabel datelabel = new JLabel();
    private JLabel namelabel = new JLabel(mybundle.getString("name"));
    private JLabel descriptionlabel = new JLabel(mybundle.getString("desc"));


    private JComboBox box = new JComboBox();
    private CustButton badd = new CustButton(mybundle.getString("add"));
    private JButton bremove = new JButton(mybundle.getString("remove"));
    private JButton bchange = new JButton(mybundle.getString("change"));
    private JButton bsearch = new JButton(mybundle.getString("search"));
    private JButton bCloseSearch = new JButton("Refresh");

    //private JTextArea textArea = new JTextArea(1,8);


      Window() throws IOException {
          super("Catalog ");
          badd.addActionListener(this);
          bremove.addActionListener(this);
          bchange.addActionListener(this);
          bsearch.addActionListener(this);
          bCloseSearch.addActionListener(this);
          searchingName.addFocusListener(this);

          searchingName.getDocument().addDocumentListener(new DocumentListener() {


              public void removeUpdate(DocumentEvent e) {

              }

              public void insertUpdate(DocumentEvent e) {
                  while (tableModel.getRowCount() > 0) {
                      tableModel.removeRow(0);
                  }
                  int RowSearched = catalogRepo.findName(searchingName.getText()).size();
                  if (RowSearched >= 0) {
                      for (int i = 0; i < RowSearched; i++)
                          tableModel.addRow(catalogRepo.findName(searchingName.getText()).get(i));
                      tableModel.fireTableDataChanged();
                      RowSearched--;
                  }

              }

              public void changedUpdate(DocumentEvent arg0) {

              }
          });

          name.addFocusListener(this);
          name.setLineWrap(true);
          description.addFocusListener(this);
          description.setLineWrap(true);
          FillBox(box);
          catalogRepo.execute();
          try {
              tableModel = new CatalogEntryTableModel(catalogRepo.returnstatm());
              table = new JTable(tableModel);

          } catch (ExecutionException e) {
              e.printStackTrace();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }


          String response = webClient.query();
          datelabel.setText(response);
          SetTable(table);
          JScrollPane scrollPane = new JScrollPane();
          scrollPane.setBounds(10, 45, 680, 205);

          panel.add(badd);
          panel.add(namelabel);
          panel.add(name);
          panel.add(descriptionlabel);
          panel.add(description);
          panel.setBackground(Color.ORANGE);
          panel.add(bchange);
          panel.add(abstactPanel.createToolBar());
          panel.add(bremove);
          panel.add(box);
          panel.add(bCloseSearch);
          panel.add(bsearch);

          panel.add(searchingName);
          panel.add(table.getTableHeader());
          panel.add(table);


          panel.add(scrollPane);
          scrollPane.setViewportView(table);


          panel.add(datelabel);

          frame.add(panel);
          frame.setSize(510, 650);
          frame.setVisible(true);
          frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//          while (true) {
//              wSearchingName = searchingName.getText();
//              if (wSearchingName != null &&  wSearchingName.trim().length() != 0) {
//                  while (tableModel.getRowCount() > 0) {
//                      tableModel.removeRow(0);
//                  }
//                  int RowSearched = catalogRepo.findName(wSearchingName).size();
//                  if (RowSearched >= 0) {
//                      for (int i = 0; i < RowSearched; i++)
//                          tableModel.addRow(catalogRepo.findName(wSearchingName).get(i));
//                      tableModel.fireTableDataChanged();
//                      RowSearched--;
//                  }
//              }
//          }
      }




    public void focusGained(FocusEvent event) {

        try {
            wname = name.getText();
            wdescription = description.getText();
            wSearchingName = searchingName.getText();
        } catch (Exception e) {
        }
    }


    @Override
    public void focusLost(FocusEvent event) {
        focusGained(event);
    }

    public void propertyChanged(PropertyChangeEvent event){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MsgDisplay msg = MsgDisplayFactory.getInstance();

        Object source = e.getSource();
        if (source == bchange) {
            if (!languageFlag) {
                Locale.setDefault(new Locale("en", "US"));
                languageFlag = true;

            } else {
                Locale.setDefault(new Locale("pl", "PL"));
                languageFlag = false;
            }
            ResourceBundle mybundle = ResourceBundle.getBundle("MessagesBundle");

                badd.setText(mybundle.getString("add"));
                bremove.setText(mybundle.getString("remove"));
                bchange.setText(mybundle.getString("change"));
                bsearch.setText(mybundle.getString("search"));
                mes1 = mybundle.getString("mes1");
                mes2 = mybundle.getString("mes2");
                banederror = mybundle.getString("banmes");
                messerror = mybundle.getString("messerror");
                namelabel.setText(mybundle.getString("name"));
                descriptionlabel.setText(mybundle.getString("desc"));
                blankrow  = mybundle.getString("blankrow");
                table.getColumnModel().getColumn(0).setHeaderValue(mybundle.getString("id"));
                table.getColumnModel().getColumn(1).setHeaderValue(mybundle.getString("name"));
                table.getColumnModel().getColumn(2).setHeaderValue(mybundle.getString("desc"));
                table.getTableHeader().repaint();
        } else if (source == badd) {
            try {
                if (INSTANCE.getForbiddenWords().contains(wname.trim()) || INSTANCE.getForbiddenWords().contains(wdescription.trim()) ) {
                    msg.show(banederror+" " + wname +" / "+wdescription);
                    throw new ForbiddenArgumentException("Invalid description or name");
                } else {

                    CatalogEntry newEntry = catalogRepo.add(new CatalogEntry(wname, wdescription));
                    tableModel.addRow(newEntry);
                    box.addItem(newEntry.getId());
                    msg.show(mes1);
                    wname = null;
                    wdescription = null;}
                } catch(InvalidCatalogEntryException exception){
                    msg.show(messerror);
                }
                repaint();
                description.setText(null);
                name.setText(null);

        } else if (source == bsearch && wSearchingName.trim().length() != 0) {
            //tableModel = new CatalogEntryTableModel(catalogRepo.findName(wSearchingName));
            //SearchWindow s = new SearchWindow(wSearchingName);
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
                int RowSearched = catalogRepo.findName(wSearchingName).size();
                if (RowSearched >= 0) {
                    for (int i = 0; i < RowSearched; i++)
                        tableModel.addRow(catalogRepo.findName(wSearchingName).get(i));
                    tableModel.fireTableDataChanged();
                    RowSearched--;
                }

                searchingName.setText(null);
                wSearchingName = null;
                if (ActionFactory.komponent == null) {
                    ActionFactory.komponent = panel;
                }


        } else if (source == bremove) {
            if (0 == table.getRowCount()){
                msg.show(blankrow);
                throw new NullRowException("Can not remove blank row");
            }else {
                int idToRemove = (Integer) box.getSelectedItem();
                catalogRepo.remove(idToRemove);
                tableModel.removeRow(box.getSelectedIndex());
                box.removeItem(idToRemove);
                repaint();
                msg.show(mes2);
            }
        } else if (source == bCloseSearch){

            while (tableModel.getRowCount()>0){
                tableModel.removeRow(0);
            }
            for (int i=0 ; i <catalogRepo.findAll().size();i++) {
                tableModel.addRow(catalogRepo.findAll().get(i));
                tableModel.fireTableDataChanged();
            }

        }
        repaint();
    }

    private void FillBox(JComboBox box) {
        for (int id : catalogRepo.findAllIds()) {
            box.addItem(id);
        }
    }

    public static void SetTable(JTable table) {
        TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(50);
            }
        }

    }

    public void run() {
        while (true) {


            // panel.repaint();
        }
    }


}


