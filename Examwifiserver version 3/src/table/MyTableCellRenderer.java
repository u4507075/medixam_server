/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author bon
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
    @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            
            return this;
    }
}
