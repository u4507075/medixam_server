/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import main.Mainframe;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import properties.P;
import table.Record;

/**
 *
 * @author bon
 */
public class Autosaverecord extends Thread{
    
    File file;
    Mainframe main;
    Record record;
    Workbook wb;
    Sheet recordsheet;
    int loop = 60;
    
    Logger logger;
    FileHandler fh;  
    public Autosaverecord (Mainframe main, Record record, String examtitle)
    {
        this.main = main;
        this.record = record;
        File excelfile = new File(P.EXCELPATH);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String path = excelfile.getParentFile().getAbsolutePath()+"/"+examtitle.replace('/', '_') +" ("+df.format(new Date())+").xls";
        String path2 = excelfile.getParentFile().getAbsolutePath()+"/"+examtitle.replace('/', '_') +" ("+df.format(new Date())+").log";
        file = new File(path);
        try {
            createSheet();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logger = Logger.getLogger(examtitle+" ("+df.format(new Date())+")");  
        try {
            fh = new FileHandler(path2,true);
            logger.addHandler(fh);
            // This block configure the logger with handler and formatter  
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
            //remove console out
            logger.setUseParentHandlers(false);
        } catch (IOException ex) {
            Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run()
    {
        while(true)
        {
            for(int i=loop;i>=0;i--)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
                }
                main.setCountdown(""+i);
            }
            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss",Locale.UK);
            main.setLastrecordtime(date.format(new Date()));
            insertNewrecord();
        }
    }

    private void createSheet() throws FileNotFoundException, IOException
    {
        wb = new HSSFWorkbook();
        recordsheet = wb.createSheet("record");
        
        for(int i=0;i<record.getRowCount();i++)
        {
            Row row = recordsheet.createRow(i);
            for(int j=0;j<record.getColumnCount();j++)
            {
                Cell cell = row.createCell(j);
                Object v = record.getValueAt(i,j);
                if(v==null)
                {
                    cell.setCellValue("");
                }
                else if(v instanceof String)
                {
                    cell.setCellValue(v.toString());
                }
                else if(v instanceof Integer)
                {
                    cell.setCellValue((int)v);
                }
                else
                {
                    cell.setCellValue("");
                }
                
            }
        }
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }
        }
    private void insertNewrecord()
    {
        ArrayList list = new ArrayList();
        for(int i =0;i<record.getRowCount();i++)
        {
            if(!record.getValueAt(i, 0).toString().equals("record"))
            {
                list.add(i);
            }
        }
        //System.out.println(record.getRowCount());
        for(int i=0;i<list.size();i++)
        {
            record.setValueAt("record", (int)list.get(i), 0);
            if(recordsheet!=null)
            {
                Row row = recordsheet.createRow(recordsheet.getPhysicalNumberOfRows());
                for(int j=0;j<record.getColumnCount();j++)
                {
                    Cell c = row.createCell(j);
                    Object value = record.getValueAt((int)list.get(i), j);
                    if(value==null)
                    {
                        c.setCellValue("");
                    }
                    else if(value instanceof String)
                    {
                        c.setCellValue(value.toString());
                        addLog(value.toString());
                    }
                    else if(value instanceof Integer)
                    {
                        c.setCellValue((int)value);
                        addLog(""+(int)value);
                    }
                    else
                    {
                        c.setCellValue("");
                    }

                }
            }
        }
        if(list.size()>0 && wb!=null)
        {
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(file);
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
            }  
            finally {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(Autosaverecord.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    private void addLog(String text)
    {
        if(logger!=null && fh!=null)
        {
            logger.info(text);  
        }
    }
}
