/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sheet.Examset.Set;

/**
 *
 * @author bon
 */
public class Studentdetail {
    ArrayList<Student> students = new ArrayList();
    public String read(HSSFWorkbook wb, Examset set)
    {
        HSSFSheet sheet = wb.getSheet("student detail");
        for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
        {
            String studentid = getValue(sheet.getRow(i).getCell(0));
            String studentname = getValue(sheet.getRow(i).getCell(1));
            String exset = getValue(sheet.getRow(i).getCell(2));
            if(studentid.equals(""))
            {
                return "complete";
                //return "Student id at row "+(i+1)+"cannot be empty.";
            }
            if(studentname.equals(""))
            {
                return "Student name at row "+(i+1)+"cannot be empty.";
            }
            boolean matchset = false;
            for(int j=0;j<set.getSets().size();j++)
            {
                Set s = (Set)set.getSets().get(j);
                if(s.getSetname().equals(exset))
                {
                    matchset = true;
                    break;
                }
            }
            if(!matchset)
            {
                return "Set name in the student detail sheet at row "+(i+1)+"does not match to the exam set.";
            }
            Student student = new Student(studentid, studentname, exset);
            students.add(student);
        }
        return "complete";
    }
    public ArrayList getStudents()
    {
        return students;
    }
    private String getValue(HSSFCell cell)
    {
        if(cell == null || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
        {
            return "";
        }
        else if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING)
        {
            return cell.getStringCellValue();
        }
        else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
        {
            return fmt(cell.getNumericCellValue());
        }
        else
        {
            return "";
        }
    }
    private String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    public class Student
    {
        String studentid, studentname, set = "";
        public Student(String studentid, String studentname, String set)
        {
            this.studentid = studentid;
            this.studentname = studentname;
            this.set = set;
        }
        public String getStudentid()
        {
            return studentid;
        }
        public String getStudentname()
        {
            return studentname;
        }
        public String getSet()
        {
            return set;
        }
    }
}
