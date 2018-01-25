/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import exammap.Exam;
import exammap.Exammap;
import sheet.Survey;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import properties.P;
import sheet.Examdetail;
import sheet.Examset;
import sheet.Examset.Set;
import sheet.Studentdetail;


/**
 *
 * @author bon
 */
public class Detail {

    Examdetail examdetail;
    Survey survey;
    Exammap map;
    Examset set;
    Studentdetail studentdetail;
    public String getDetail()
    {
        
        try {
            FileInputStream file = new FileInputStream(P.EXCELPATH);
            try {
                HSSFWorkbook wb = new HSSFWorkbook(file);
                examdetail = new Examdetail();
                String complete = examdetail.read(wb);
                if(complete.equals("complete"))
                {
                    if(examdetail.getSurveystatus().equals("yes"))
                    {
                        survey = new Survey();
                        survey.read(wb);
                    }
                    map = new Exammap();
                    complete = map.read();
                    if(complete.equals("complete"))
                    {
                        set = new Examset();
                        complete = set.read(wb, map);
                        if(complete.equals("complete"))
                        {
                            if(set.getSets().size()>=2)
                            {
                                for(int i =0;i<set.getSets().size()-1;i++)
                                {
                                    if(((Set)set.getSets().get(i)).getList().size()!=((Set)set.getSets().get(i+1)).getList().size())
                                    {
                                        return "All sets must contain the same number of questions.";
                                    }
                                }
                            }
                            studentdetail = new Studentdetail();
                            complete = studentdetail.read(wb, set);
                            if(complete.equals("complete"))
                            {

                            }
                            else
                            {
                                return complete;
                            }
                        }
                        else
                        {
                            return complete;
                        }
                    }
                    else
                    {
                        return complete;
                    }
                }
                else
                {
                    return complete;
                }
            } catch (IOException ex) {
                return "Excel file is not found.";
            }
            
        } catch (FileNotFoundException ex) {
            return "File is not found.";
        }
        return "complete";
    }
    public Examdetail getExamdetail()
    {
        return examdetail;
    }
    public Studentdetail getStudentdetail()
    {
        return studentdetail;
    }
    public Examset getExamset()
    {
        return set;
    }
    public Exammap getExammap()
    {
        return map;
    }
    public Survey getSurvey()
    {
        return survey;
    }
}
