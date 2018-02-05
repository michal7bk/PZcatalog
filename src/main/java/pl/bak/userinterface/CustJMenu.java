package pl.bak.userinterface;

import javax.swing.*;

public class CustJMenu extends JMenuItem {

    ImageIcon nimbusIcon = new ImageIcon("src/main/resources/nimbus.png");
        ImageIcon motifIcon = new ImageIcon("src/main/resources/motif.png");
    ImageIcon windowsIcon = new ImageIcon("src/main/resources/windows.png");

    public CustJMenu(String text, Action a , int position) {
        super(text);
        setAction(a);
        if(position == 1){
            setModel(new DefaultButtonModel());
            init(text,nimbusIcon);
            this.setIcon(nimbusIcon);
            initFocusability();
        }else if (position ==2){
            setModel(new DefaultButtonModel());
            init(text,motifIcon);
            this.setIcon(motifIcon);
            initFocusability();
        }else if (position==3){
            setModel(new DefaultButtonModel());
            init(text,windowsIcon);
            this.setIcon(windowsIcon);
            initFocusability();
        }else{


        }
    }
    void initFocusability() {
        setFocusable(false);
    }


}
