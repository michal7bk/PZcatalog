package pl.bak.userinterface;

import pl.bak.catalog.CatalogRepository;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class SearchWindow extends JFrame   {
    private CatalogEntryTableModel tableModel;
    private  JTable table;

    private CatalogRepository catalogRepo = CatalogRepository.anInstance();
    private ActionFactory abstactPanel = new ActionFactory();

    JPanel panel =  new JPanel();
    JFrame frame = new JFrame();

    public  JFrame getFrame() {
        return this;
    }
    public SearchWindow(String search){
        super("Search/Szukaj");
        tableModel = new CatalogEntryTableModel(catalogRepo.findName(search));
        table = new JTable(tableModel);
        SetTablee(table);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        panel.add(table);
        frame.setJMenuBar(abstactPanel.createMenuBar());
        frame.add(panel);
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void SetTablee(JTable table) {
        TableColumn column = null;
        for (int i = 0; i <3; i++) {
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


}
