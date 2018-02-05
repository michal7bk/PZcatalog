package pl.bak.userinterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bak.database.ConnectionManager;

import javax.swing.*;
import java.awt.*;

public class Plaf {

    private static final Logger log = LogManager.getLogger(ConnectionManager.class);

    public Plaf(String plaf , Component c) {
        try {
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(c);
        } catch (Exception exc) {
            log.error("Error while changing plaf", exc);
            throw new PlafException("Error while changing plaf",exc);
        }
    }
}
