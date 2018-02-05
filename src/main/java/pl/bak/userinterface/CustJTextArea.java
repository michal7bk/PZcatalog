package pl.bak.userinterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustJTextArea extends JTextArea{


    private boolean wrap;

    public CustJTextArea(int rows, int columns) {

        setColumns(columns);
        setRows(rows);
        setFont(new Font("Dialog", Font.PLAIN, 14));
        setBackground();
        setBorder(blackline);
        setLineWrap(true);

    }


    public void setBackground( ) {
        super.setBackground(Color.white);
    }
    Border blackline = BorderFactory.createLineBorder(Color.black, 3);



}
