/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author bon
 */
public class Surveytable extends JTable{
    public Surveytable()
    {
        setShowGrid(true);
        setFont(new Font("TH Niramit AS", Font.PLAIN, 20));
        Object[][] ob = new Object[0][200];
        Object[] column = new Object[200];
        TableModel model = new DefaultTableModel(ob, column);
        setModel(model);
    }
    public void insertData(Object[] data)
    {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.insertRow(model.getRowCount(), data);
    }
}
