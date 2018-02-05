package pl.bak.userinterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ActionFactory {

    Action NimbusAction,MotifAction,WindowsAction;
    private String plaf;
    JMenuBar menuBar = new JMenuBar();
    JToolBar toolBar = new JToolBar();
    public  static Component komponent ;


    public ActionFactory()
    {

        createActions();
    }
    private void createActions()
    {
        NimbusAction = new NimbusAction("NimbusAction",  "Set Plaf : Nimbus");
        MotifAction = new MotifAction("MotifAction", "Set Plaf : Motif");
        WindowsAction = new WindowsAction("WindowsAction",  "Set Plaf : Windows");
    }
    public JMenuBar createMenuBar()

    {
        JMenu editMenu = new JMenu("Plaf");
        CustJMenu nimbusMenuItem = new CustJMenu((String) NimbusAction.getValue("name"),NimbusAction,1);
        CustJMenu motifMenuItem = new CustJMenu( (String) MotifAction.getValue("name"),MotifAction,2);
        CustJMenu windowsMenuItem = new CustJMenu((String) WindowsAction.getValue("name"), WindowsAction,3);


        editMenu.add(nimbusMenuItem);
        editMenu.add(motifMenuItem);
        editMenu.add(windowsMenuItem);
        menuBar.add(editMenu);
        return menuBar;
    }
    public JToolBar createToolBar()
    {
        JButton bnimbusButton = new JButton(NimbusAction);
        JButton bmotifButton = new JButton(MotifAction);
        JButton bwindowsButton = new JButton(WindowsAction);
        toolBar.add(bnimbusButton);
        toolBar.add(bmotifButton);
        toolBar.add(bwindowsButton);

        return toolBar;
    }
    public class NimbusAction extends AbstractAction
    {
        public NimbusAction(String name, String shortDescription)
        {
            super(name);
            putValue(SHORT_DESCRIPTION, shortDescription);
        }
        public void actionPerformed(ActionEvent e)
        {
            plaf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            try {

                if (toolBar.getParent() != null){
                    new Plaf(plaf, toolBar.getParent());
                }else if (menuBar.getParent().getComponent(0)!= null ){
                    new Plaf(plaf, menuBar.getParent().getComponent(0));
                    new Plaf(plaf, komponent);
                }
            }catch (NullPointerException exc)
            {
                System.out.println("Error when changing plaf"+exc);
            }
        }
    }
    public class MotifAction extends AbstractAction {
        public MotifAction(String name, String shortDescription) {
            super(name);

            putValue(SHORT_DESCRIPTION, shortDescription);
        }
        public void actionPerformed(ActionEvent e) {
            plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            try {
                if (toolBar.getParent() != null){
                    new Plaf(plaf, toolBar.getParent());
                }else if (menuBar.getParent().getComponent(0)!= null ){
                    new Plaf(plaf, menuBar.getParent().getComponent(0));
                    new Plaf(plaf, komponent);

                }
            }catch (NullPointerException exc)
            {
                System.out.println("Error when changing plaf"+exc);
            }
        }
    }
        public class WindowsAction extends AbstractAction {
            public WindowsAction(String name, String shortDescription) {
                super(name);
                putValue(SHORT_DESCRIPTION, shortDescription);
            }
            public void actionPerformed(ActionEvent e) {
                plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                try {
                    if (toolBar.getParent() != null){
                        new Plaf(plaf, toolBar.getParent());
                    }else if (menuBar.getParent().getComponent(0)!= null ){
                        new Plaf(plaf, menuBar.getParent().getComponent(0));
                        new Plaf(plaf, komponent);
                    }
                }catch (NullPointerException exc)
                {
                    System.out.println("Error when changing plaf"+exc);
                }
            }
        }
    }

