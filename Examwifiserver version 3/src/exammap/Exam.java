/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exammap;

import exam.Style;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.usermodel.*;
/**
 *
 * @author bon
 */
public class Exam {
    
    String examID;
    String examType;
    String correctValue;
    String question;
    ArrayList questionarray = new ArrayList();
    ArrayList questionstyle = new ArrayList();
    ArrayList choiceid = new ArrayList();
    ArrayList choicearray = new ArrayList();
    ArrayList choicestyle = new ArrayList();
    ArrayList choicetextarray = new ArrayList();
    String selectedchoice = "";
    String topiccode;
    String examLevel;
    String medCode;
    String textCode;
    String reference;
    ArrayList keywordarray = new ArrayList();
    int column;

    public String read(File file) throws IOException
    {

        try
        {
            
        XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
        setexamID(FilenameUtils.removeExtension(file.getName()));
        /*
        if(!doc.getHeaderList().isEmpty())
        {
            setexamID(doc.getHeaderList().get(0).getText().trim());
        }*/
        XWPFTable infotable = doc.getTables().get(0);
        setTopiccode(infotable.getRow(0).getCell(1).getText());
        setexamType(infotable.getRow(2).getCell(1).getText());
        setexamLevel(infotable.getRow(3).getCell(1).getText());
        setmedCode(infotable.getRow(4).getCell(1).getText());
        settextCode(infotable.getRow(5).getCell(1).getText());
        setReference(infotable.getRow(6).getCell(1).getText());
        
        XWPFTable examtable = doc.getTables().get(1);
        setQuestion(examtable.getRow(0).getCell(0).getText());

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
                        addQuestiondetail(pic.get(k).getPictureData().getData());
                    }
                    if(pic.isEmpty())
                    {
                        addQuestiondetail(text);
                    }
                }
                
            }
            else
            {
                addQuestiondetail(text);
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
                    }
                    if(italic)
                    {
                        Style s = new Style();
                        s.setValue(s.ITALIC(), num, num+textrun.length());
                        style.add(s);
                    }
                    if(strike)
                    {
                        Style s = new Style();
                        s.setValue(s.STRIKE(), num, num+textrun.length());
                        style.add(s);
                    }
                    if(underline!=UnderlinePatterns.NONE.getValue())
                    {
                        Style s = new Style();
                        s.setValue(s.UNDERLINE(), num, num+textrun.length());
                        style.add(s);
                    }
                    if(script==VerticalAlign.SUBSCRIPT.getValue())
                    {
                        Style s = new Style();
                        s.setValue(s.SUBSCRIPT(), num, num+textrun.length());
                        style.add(s);
                    }
                    if(script==VerticalAlign.SUPERSCRIPT.getValue())
                    {
                        Style s = new Style();
                        s.setValue(s.SUPERSCRIPT(), num, num+textrun.length());
                        style.add(s);
                    }
                    num = num+textrun.length();
                }
                addQuestionstyle(style);
                }
                else
                {
                    ArrayList style = new ArrayList();
                    addQuestionstyle(style);
                }
        }
        
        for(int i=1;i<examtable.getRows().size();i++)
        {
            addChoiceid(examtable.getRow(i).getCell(0).getText());
            addChoice(examtable.getRow(i).getCell(1).getText());
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
                        }
                        if(italic)
                        {
                            Style s = new Style();
                            s.setValue(s.ITALIC(), num, num+textrun.length());
                            substyle.add(s);
                        }
                        if(strike)
                        {
                            Style s = new Style();
                            s.setValue(s.STRIKE(), num, num+textrun.length());
                            substyle.add(s);
                        }
                        if(underline!=UnderlinePatterns.NONE.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.UNDERLINE(), num, num+textrun.length());
                            substyle.add(s);
                        }
                        if(script==VerticalAlign.SUBSCRIPT.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.SUBSCRIPT(), num, num+textrun.length());
                            substyle.add(s);
                        }
                        if(script==VerticalAlign.SUPERSCRIPT.getValue())
                        {
                            Style s = new Style();
                            s.setValue(s.SUPERSCRIPT(), num, num+textrun.length());
                            substyle.add(s);
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
            addChoicestyle(style);
            
            if(choice.isEmpty())
            {
                choice.add("");
            }
            addChoicedetail(choice);
        }
        XWPFTable correcttable = doc.getTables().get(2);
        setcorrectValue(correcttable.getRow(0).getCell(1).getText());
        
        
        XWPFTable keywordtable = doc.getTables().get(3);
        for(int i=0;i<keywordtable.getRow(0).getCell(1).getParagraphs().size();i++)
        {
            addKeyword(keywordtable.getRow(0).getCell(1).getParagraphs().get(i).getText());
        }
        if(getcorrectValue().equals("A") || getcorrectValue().equals("B") || getcorrectValue().equals("C") || getcorrectValue().equals("D") || getcorrectValue().equals("E") )
        {
            return "complete";
        }
        else
        {
            return "Correct answer in "+getexamID()+" is incorrect.";
        }
        }
        catch(Exception e)
        {
            //return getexamID()+" is not found.";
            return file.getName()+" has a problem with its content or the file name is not found.";
        }
    }

    private void setexamID(String examID)
    {
        this.examID = examID;
    }
    private void setexamType(String examType)
    {
        this.examType = examType;
    }
    private void setcorrectValue(String correctValue)
    {
        this.correctValue = correctValue;
    }
    private void setQuestion(String question)
    {
        this.question = question;
    }
    private void addQuestiondetail(Object question)
    {
        questionarray.add(question);
    }
    private void addQuestionstyle(ArrayList style)
    {
        questionstyle.add(style);
    }
    private void addChoice (String choice )
    {
        choicetextarray.add(choice);
    }
    private void addChoicestyle(ArrayList style)
    {
        choicestyle.add(style);
    }
    private void addChoiceid (String choice )
    {
        choiceid.add(choice);
    }
    private void addChoicedetail(ArrayList choice)
    {
        choicearray.add(choice);
    }
    private void setTopiccode(String topiccode)
    {
        this.topiccode = topiccode;
    }
    private void setexamLevel(String examLevel)
    {
        this.examLevel = examLevel;
    }
    private void setmedCode(String medCode)
    {
        this.medCode = medCode;
    }
    private void settextCode(String textCode)
    {
        this.textCode = textCode;
    }
    private void setReference(String reference)
    {
        this.reference = reference;
    }
    private void addKeyword (String keyword)
    {
        keywordarray.add(keyword);
    }
    public String getexamID()
    {
        return examID;
    }
    public String getexamType()
    {
        return examType;
    }
    public String getcorrectValue()
    {
        return correctValue;
    }
    public String getQuestion()
    {
        return question;
    }
    public ArrayList getQuestiondetails()
    {
        return questionarray;
    }
    public ArrayList getQuestionstyle()
    {
        return questionstyle;
    }
    public ArrayList getChoiceid()
    {
        return choiceid;
    }
    public ArrayList getChoice()
    {
        return choicetextarray;
    }
    public ArrayList getChoicedetails()
    {
        return choicearray;
    }
    public ArrayList getChoicestyle()
    {
        return choicestyle;
    }
    public String getTopiccode()
    {
        return topiccode;
    }
    public String getexamLevel()
    {
        return examLevel;
    }
    public String getmedCode()
    {
        return medCode;
    }
    public String gettextCode()
    {
        return textCode;
    }
    public String getReference()
    {
        return reference;
    }
    public ArrayList getKeyword ()
    {
        return keywordarray;
    }
     public void setColumn(int column)
    {
        this.column = column;
    }
    public int getColumn()
    {
        return column;
    }
    public void setSelectedchoice(String selectedchoice)
    {
        this.selectedchoice = selectedchoice;
    }
    public String getSelectedchoice()
    {
        return selectedchoice;
    }
}
