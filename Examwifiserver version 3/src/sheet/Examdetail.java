/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bon
 */
public class Examdetail {
    String eventname = "";
    String examtime = "";
    String survey = "";
    //String[] studentid;
    public String read(HSSFWorkbook wb)
    {
        HSSFSheet sheet = wb.getSheet("exam detail");
        eventname = getValue(sheet.getRow(0).getCell(1));
        examtime = getValue(sheet.getRow(1).getCell(1));
        survey = getValue(sheet.getRow(2).getCell(1));
        /*
        HSSFSheet studentidsheet = wb.getSheet("Studentid");
        studentid = new String[studentidsheet.getPhysicalNumberOfRows()-1];
        for(int i=1;i<studentidsheet.getPhysicalNumberOfRows();i++)
        {
            studentid[i-1] = getValue(studentidsheet.getRow(i).getCell(0));
        }*/
        if("".equals(eventname))
        {
            return "Event name is blank.";
        }
        if("".equals(examtime))
        {
            return "Exam time is blank.";
        }
        try
        {
            Integer.parseInt(examtime);
        }
        catch(Exception e)
        {
            return "Exam time is not a number";
        }
        if(survey.equals("yes") || survey.equals("no"))
        {
            
        }
        else
        {
            return "Survey input must be \"yes\" or \"no\".";
        }
        return "complete";
    }
    public String getEventname()
    {
        return eventname;
    }
    public String getExamtime()
    {
        return examtime;
    }
    public String getSurveystatus()
    {
        return survey;
    }
    /*
    public String[] getStudentid()
    {
        return studentid;
    }*/
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
}
