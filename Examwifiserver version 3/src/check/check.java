/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package check;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import exam.Style;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.usermodel.*;
/**
 *
 * @author Piyapong
 */
public class check {
    public ArrayList read(File file)
    {
        ArrayList result = new ArrayList();
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
            //MCQ001
            FilenameUtils.removeExtension(file.getName());
            
            XWPFTable examtable = doc.getTables().get(1);
            String question = examtable.getRow(0).getCell(0).getText();
            if(question==null || question.equals(""))
            {
                result.add("ไม่พบข้อมูลช่องคำถาม");
            }
            
            for(int i=0;i<examtable.getRow(0).getCell(0).getParagraphs().size();i++)
            {
                String text = examtable.getRow(0).getCell(0).getParagraphs().get(i).getText();
                if(text.equals(""))
                {
                    for(int j=0;j<examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().size();j++)
                    {
                        List<XWPFPicture> pic = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).getEmbeddedPictures();
                        for(int k=0;k<pic.size();k++)
                        {
                            result.add("พบข้อมูลช่องคำถามที่เป็นรูปภาพรูปที่ "+(k+1));
                        }
                        if(pic.isEmpty())
                        {

                        }
                    }

                }
                else
                {

                }
                    if(!text.equals(""))
                    {
                    int num = 0;
                    ArrayList style = new ArrayList();
                    for(int j=0;j<examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().size();j++)
                    {
                        boolean bold = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).isBold();
                        boolean italic = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).isItalic();
                        boolean strike = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).isStrike();
                        int underline = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).getUnderline().getValue();
                        int script = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).getSubscript().getValue();
                        String textrun = examtable.getRow(0).getCell(0).getParagraphs().get(i).getRuns().get(j).getText(0);
                        if(textrun==null)
                            {
                                textrun = "";
                            }
                        if(bold)
                        {
                            Style s = new Style();
                            s.setValue(s.BOLD(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวหนา: "+textrun);
                        }
                        if(italic)
                        {
                            Style s = new Style();
                            s.setValue(s.ITALIC(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวเอียง: "+textrun);
                        }
                        if(strike)
                        {
                            Style s = new Style();
                            s.setValue(s.STRIKE(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวขีดฆ่า: "+textrun);
                        }
                        if(underline!=UnderlinePatterns.NONE.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.UNDERLINE(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวขีดเส้นใต้: "+textrun);
                        }
                        if(script==VerticalAlign.SUBSCRIPT.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.SUBSCRIPT(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวห้อย: "+textrun);
                        }
                        if(script==VerticalAlign.SUPERSCRIPT.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.SUPERSCRIPT(), num, num+textrun.length());
                            style.add(s);
                            result.add("พบข้อมูลช่องคำถามที่เป็นตัวยก: "+textrun);
                        }
                        num = num+textrun.length();
                    }

                    }
                    
            }
            
            for(int i=1;i<examtable.getRows().size();i++)
            {
                String choiceid = examtable.getRow(i).getCell(0).getText();
                String txt = checkchoice(choiceid);
                if(!txt.equals("correct"))
                {
                    result.add("พบ Choice id ที่ "+(i+1)+"("+choiceid+") ไม่ตรงตามตัวเลือก A - E");
                }

                ArrayList choice = new ArrayList();
                ArrayList style = new ArrayList();
                for(int l=0;l<examtable.getRow(i).getCell(1).getParagraphs().size();l++)
                {
                    String text = examtable.getRow(i).getCell(1).getParagraphs().get(l).getText();
                    if(text.equals(""))
                    {
                        for(int j=0;j<examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().size();j++)
                        {
                            List<XWPFPicture> pic = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).getEmbeddedPictures();
                            for(int k=0;k<pic.size();k++)
                            {
                                choice.add(pic.get(k).getPictureData().getData());
                                result.add("พบข้อมูลช่องคำตอบที่ "+(i+1)+" เป็นรูปภาพรูปที่ "+(k+1));
                            }
                            if(pic.isEmpty())
                            {
                                choice.add(text);
                            }
                        }

                    }
                    else
                    {
                        choice.add(text);
                    }
                        if(!text.equals(""))
                    {
                        int num = 0;
                        ArrayList substyle = new ArrayList();
                        for(int j=0;j<examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().size();j++)
                        {
                            boolean bold = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).isBold();
                            boolean italic = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).isItalic();
                            boolean strike = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).isStrike();
                            int underline = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).getUnderline().getValue();
                            int script = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).getSubscript().getValue();
                            String textrun = examtable.getRow(i).getCell(1).getParagraphs().get(l).getRuns().get(j).getText(0);
                            if(textrun==null)
                            {
                                textrun = "";
                            }
                            if(bold)
                            {
                                Style s = new Style();
                                s.setValue(s.BOLD(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวหนา: "+textrun);
                            }
                            if(italic)
                            {
                                Style s = new Style();
                                s.setValue(s.ITALIC(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวเอียง: "+textrun);
                            }
                            if(strike)
                            {
                                Style s = new Style();
                                s.setValue(s.STRIKE(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวขีดฆ่า: "+textrun);
                            }
                            if(underline!=UnderlinePatterns.NONE.getValue())
                            {
                                Style s = new Style();
                                s.setValue(s.UNDERLINE(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวขีดเส้นใต้: "+textrun);
                            }
                            if(script==VerticalAlign.SUBSCRIPT.getValue())
                            {
                                Style s = new Style();
                                s.setValue(s.SUBSCRIPT(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวห้อย: "+textrun);
                            }
                            if(script==VerticalAlign.SUPERSCRIPT.getValue())
                            {
                                Style s = new Style();
                                s.setValue(s.SUPERSCRIPT(), num, num+textrun.length());
                                substyle.add(s);
                                result.add("พบข้อมูลช่องตอบที่ "+(i+1)+" เป็นตัวยก: "+textrun);
                            }
                            num = num+textrun.length();
                        }
                        style.add(substyle);
                    }
                    else
                    {
                        ArrayList substyle = new ArrayList();
                        style.add(substyle);
                    }

                }

            }
            
            XWPFTable correcttable = doc.getTables().get(2);
            String correctanswer = correcttable.getRow(0).getCell(1).getText();

            String txt2 = checkchoice(correctanswer);
            if(!txt2.equals("correct"))
            {
                result.add("พบเฉลย"+"("+correctanswer+") ไม่ตรงตามตัวเลือก A - E");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
            result.add(ex.getMessage());
        }
        return result;
    }
    private String checkchoice(String text)
    {
        String[] choiceid = {"A","B","C","D","E"};
        for (String choiceid1 : choiceid) {
            if (text.equals(choiceid1)) {
                return "correct";
            }
        }
        return text;
    }
}
