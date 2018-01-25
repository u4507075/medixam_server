/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import backup.Autosaverecord;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author bon
 */
public class Record extends JTable{
    public Record()
    {
        setShowGrid(true);
        setFont(new Font("TH Niramit AS", Font.PLAIN, 20));
        Object[][] ob = new Object[0][20];
        Object[] column = new Object[20];
        TableModel model = new DefaultTableModel(ob, column);
        setModel(model);
        for(int i=0;i<getColumnCount();i++)
        {
            getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer());
            if(i==1 || i==2 || i ==3)
            {
                getColumnModel().getColumn(i).setPreferredWidth(200);
            }
        }
    }
    
    public void insertData(Object[] data)
    {
        Object[] newdata = new Object[data.length+2];
        newdata[0] = "";
        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss",Locale.UK);
        newdata[1] = date.format(new Date());
        for(int i=2;i<data.length+2;i++)
        {
            newdata[i] = data[i-2];
        }

        DefaultTableModel model = (DefaultTableModel) getModel();
        
        if(newdata.length>getColumnCount())
        {
            int diff = newdata.length-getColumnCount();
            for(int i=0;i<diff;i++)
            {
                model.addColumn("");
            }
        }
        
        model.insertRow(model.getRowCount(), newdata);
    }
    
    public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
    @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(column==0 && value!=null && value.equals("record"))
            {
                setBackground(Color.green);
            }
            else
            {
                setBackground(Color.white);
            }
                
            if(isSelected)
            {
                setForeground(Color.blue);
            }
            else
            {
                setForeground(Color.black);
            }
            
            return this;
    }
    }
}
