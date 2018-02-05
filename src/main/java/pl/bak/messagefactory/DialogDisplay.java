package pl.bak.messagefactory;

import javax.swing.*;

public class DialogDisplay implements MsgDisplay {
    public void show(String s) {
        JOptionPane.showMessageDialog(null, s );
    }
}
