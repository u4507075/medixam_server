/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author bon
 */
public class Survey {
    ArrayList survey = new ArrayList();
    public void read(HSSFWorkbook wb) throws FileNotFoundException, IOException
    {
        HSSFSheet sheet = wb.getSheet("Survey");
        for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
        {
            if(sheet.getRow(i).getCell(0) == null || sheet.getRow(i).getCell(0).getStringCellValue().equals(""))
            {
                break;
            }
            else
            {
                ArrayList topic = new ArrayList();
                topic.add(sheet.getRow(i).getCell(0).getStringCellValue());
                ArrayList value = new ArrayList();
                for(int j=1;j<=20;j++)
                {
                    if(sheet.getRow(i).getCell(j) == null)
                    {
                        break;
                    }
                    else
                    {
                        if(sheet.getRow(i).getCell(j).getCellType()==HSSFCell.CELL_TYPE_STRING)
                        {
                            if(sheet.getRow(i).getCell(j).getStringCellValue().equals(""))
                            {
                                break;
                            }
                            if(sheet.getRow(i).getCell(0).getStringCellValue().equals("Image"))
                            {
                                File image = new File(sheet.getRow(i).getCell(j).getStringCellValue());
                                FileInputStream imageInFile = new FileInputStream(image);
                                byte imageData[] = new byte[(int) image.length()];
                                imageInFile.read(imageData);
                                String text = Base64.encode(imageData);
                                value.add(text);
                                imageInFile.close();
                            }
                            else
                            {
                                value.add(sheet.getRow(i).getCell(j).getStringCellValue());
                            }
                        }
                        else if(sheet.getRow(i).getCell(j).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
                        {
                            value.add(fmt(sheet.getRow(i).getCell(j).getNumericCellValue()));
                        }
                    }
                    
                }
                topic.add(value);
                survey.add(topic);
            }
        }
    }
public ArrayList getSurvey()
{
    return survey;
}
private String fmt(double d)
{
    if(d == (long) d)
        return String.format("%d",(long)d);
    else
        return String.format("%s",d);
}
}
