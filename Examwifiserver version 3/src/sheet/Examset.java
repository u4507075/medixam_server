/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import exammap.Exammap;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bon
 */
public class Examset {
    int MAXCOLUMN = 20;
    ArrayList sets = new ArrayList();
    public String read(HSSFWorkbook wb, Exammap map)
    {
        HSSFSheet sheet = wb.getSheet("exam set");
        
        for(int i=0;i<MAXCOLUMN;i++)
        {
            if(sheet.getRow(0).getCell(i) == null || sheet.getRow(0).getCell(i).getStringCellValue().equals(""))
            {
                break;
            }
            else
            {
                Set set = new Set(sheet.getRow(0).getCell(i).getStringCellValue());
                
                
                //Get doc filename list
                for(int j=1;j<sheet.getPhysicalNumberOfRows();j++)
                {
                    if(sheet.getRow(j).getCell(i) == null || sheet.getRow(j).getCell(i).getStringCellValue().equals(""))
                    {
                        break;
                    }
                    else
                    {
                        if(map.getExam(sheet.getRow(j).getCell(i).getStringCellValue())!=null)
                        {
                            set.addExam(sheet.getRow(j).getCell(i).getStringCellValue());
                        }
                        else
                        {
                            return sheet.getRow(j).getCell(i).getStringCellValue()+" in the \"exam set\" sheet does not match to the doc file";
                        }
                        
                    }
                }
                sets.add(set);
            }

        }
        
        return "complete";
    }
    public ArrayList getSets()
    {
        return sets;
    }
    public class Set
    {
        String setname = "";
        ArrayList examlist = new ArrayList();
        public Set(String setname)
        {
            this.setname = setname;
        }
        public void addExam(String name)
        {
            examlist.add(name);
        }
        public String getSetname()
        {
            return setname;
        }
        public ArrayList getList()
        {
            return examlist;
        }
    }
}
