/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import exam.Detail;
import exammap.Exam;
import exammap.Selectedchoice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.*;
import sheet.Examset.Set;
import sheet.Studentdetail;

/**
 *
 * @author bon
 */
public class Result extends JTable{
    HashMap<String, Integer> studentmap = new HashMap<>();
    HashMap<String, Integer> questionidmap = new HashMap<>();
    public Result (Detail d)
    {
        setShowGrid(true);
        setFont(new Font("TH Niramit AS", Font.BOLD, 20));
        Object[][] ob = new Object[300][200];
        Object[] column = new Object[200];
        
        TableModel model = new DefaultTableModel(ob, column);
        setModel(model);
        for(int i=0;i<getColumnCount();i++)
        {
            getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRenderer());
        }
        
        setValueAt("Student id", 1, 0);
        setValueAt("Name",1,1);
        setValueAt("Set",1,2);
        setValueAt("Tablet id",1,3);
        setValueAt("Total",0,4);
        
        
        ArrayList students = d.getStudentdetail().getStudents();
        for(int i=0;i<students.size();i++)
        {
            Studentdetail.Student student = (Studentdetail.Student) students.get(i);
            setValueAt(student.getStudentid(), i+2, 0);
            setValueAt(student.getStudentname(), i+2, 1);
            setValueAt(student.getSet(), i+2, 2);
            studentmap.put(student.getStudentid(), i+2);
        }
        /*
        Set set = (Set)d.getExamset().getSets().get(0);
        setValueAt((set).getList().size(),1,4);
        
        for(int i=0;i<set.getList().size();i++)
        {
            setValueAt(set.getList().get(i).toString(), 0, i+5);
            
            Exam exam = d.getExammap().getExam(set.getList().get(i).toString());
            setValueAt(exam.getcorrectValue(),1,i+5);
            questionidmap.put(set.getList().get(i).toString(), i+5);

        }
        * 
        */
        Object[] namelist = d.getExammap().getKeyset();
        Arrays.sort(namelist);
        setValueAt(namelist.length,1,4);
        for(int i=0;i<namelist.length;i++)
        {
            setValueAt(namelist[i].toString(), 0, i+5);
            Exam exam = d.getExammap().getExam(namelist[i].toString());
            setValueAt(exam.getcorrectValue(),1,i+5);
            questionidmap.put(namelist[i].toString(), i+5);
        }

        updateRowHeights();
        updateColumnWidth();
    }
    public Integer getRow(String studentid)
    {
        return studentmap.get(studentid);
    }
    public Integer getColumn(String questionid)
    {
        return questionidmap.get(questionid);
    }
    public Selectedchoice getSelectedchoice(int row)
    {
        Selectedchoice select = new Selectedchoice();
        for(int i=5;i<questionidmap.size()+5;i++)
        {
            Object ob = getValueAt(row, i);
            if(ob!=null)
            {
                select.addSelectedchoice(getValueAt(0, i).toString(), ob.toString());
            }
            else
            {
                select.addSelectedchoice(getValueAt(0, i).toString(), "");
            }
        }
        return select;
    }
    public int[] calScore(int row)
    {

        int[] score = new int[2];
        
        for(int i=5;i<questionidmap.size()+5;i++)
        {
            Object ob = getValueAt(row, i);
            if(ob!=null && !"".equals(ob.toString()))
            {
                if(ob.toString().equals(getValueAt(1, i)))
                {
                    score[0]++;
                }
                score[1]++;
            }
        }
        return score;
    }
    public final void updateRowHeights()
    {
        try
        {
            for (int row = 0; row < getRowCount(); row++)
            {
                int rowheight = getRowHeight();

                for (int column = 0; column < getColumnCount(); column++)
                {
                    Component comp = prepareRenderer(getCellRenderer(row, column), row, column);
                    rowheight = Math.max(rowheight, comp.getPreferredSize().height);
                }

                setRowHeight(row, rowheight);
            }
        }
        catch(ClassCastException e) {}
    }
    public final void updateColumnWidth() {
    TableColumnModel columnmodel = getColumnModel();
    for (int column = 0; column < getColumnCount(); column++) {
        int width = 50; // Min width
        for (int row = 0; row < getRowCount(); row++) {
            TableCellRenderer renderer = getCellRenderer(row, column);
            Component comp = prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width, width);
        }
        columnmodel.getColumn(column).setPreferredWidth(width);
    }
    }
    public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
    @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(row==0)
            {
                setBackground(Color.lightGray);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if(row == 1)
            {
                setBackground(Color.orange);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            else
            {
                setBackground(Color.white);
                if(column==1)
                {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
            }
                
            if(isSelected)
            {
                setForeground(Color.blue);
            }
            else
            {
                setForeground(Color.black);
            }
            
            
            //select
            if(value!=null && row>=2 && column>=5)
            {
                if(value.toString().equals(getValueAt(1, column).toString()))
                {
                    setBackground(Color.green);
                }
                else
                {
                    setBackground(Color.white);
                }
            }
            return this;
    }
}

}
