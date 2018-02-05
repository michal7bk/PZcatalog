package pl.bak.userinterface;

import pl.bak.catalog.CatalogEntry;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

class CatalogEntryTableModel extends AbstractTableModel {

    private static final String COL_NAMES[] = {"ID", "Name", "Description"};
    private static final Class<?> COL_CLASSES[] = {Integer.class, String.class, String.class};

    private final List<CatalogEntry> data;

    CatalogEntryTableModel(List<CatalogEntry> data) {
        this.data = data;
    }

    void addRow(CatalogEntry p) {
        this.data.add(p);
        this.fireTableDataChanged();
    }


    void removeRow(int find) {
        data.remove(find);
        this.fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COL_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).getId();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getName();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getDescription();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COL_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COL_CLASSES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }
}