package pl.bak.userinterface;

import javax.swing.*;
import java.awt.*;

class CustButton extends JButton {

    public CustButton(String txt) {
        super(txt);
        setFont(new Font("Dialog", Font.PLAIN, 24));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();       // aktualna szerokość ...
        int h = getHeight();      // i wysokość komponentu
        g.setColor(Color.blue);    // ustalenie koloru rysunku
        // rysowanie kwadracików
        g.fillRect(0, 0, 10, 10);
        g.fillRect(w-10, 0, 10, 10);
        g.fillRect(0, h-10, 10, 10);
        g.fillRect(w-10, h-10, 10, 10);
    }
}



