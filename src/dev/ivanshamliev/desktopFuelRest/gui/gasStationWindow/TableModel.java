package dev.ivanshamliev.desktopFuelRest.gui.gasStationWindow;

import dev.ivanshamliev.desktopFuelRest.dtos.FuelRead;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel{

    private ArrayList<FuelRead> data;
    private Field fieldlist[];

    public TableModel(ArrayList<FuelRead> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var row = data.get(rowIndex);
        Object[] result = new Object[4];
        result[0] = row.getId();
        result[1] = row.getName();
        result[2] = row.getPricePerLiter();
        result[3] = row.getLastUpdate();

        return result[columnIndex];
    }

    public String getColumnName(int columnIndex) {
        try{
            Class<?> cls = FuelRead.class;
            fieldlist = cls.getDeclaredFields();
            String name = fieldlist[columnIndex].toString();
            return name.substring(name.lastIndexOf('.') + 1).toUpperCase();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
