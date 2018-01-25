/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import exammap.Exam;
import exammap.Selectedchoice;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sheet.Examset.Set;
import sheet.Survey;

/**
 *
 * @author bon
 */
public class Examtoxml {
    public String convert(Detail detail, String studentid, String studentname, String set, Selectedchoice select) throws ParserConfigurationException, TransformerConfigurationException, TransformerException
    {
        //Create XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        // root element = examination
        Document doc = docBuilder.newDocument();
        doc.setXmlVersion("1.0");
        Element examination = doc.createElement("examination");
        doc.appendChild(examination);
        
        //Add exam attribute
        addAttribute(examination, "examtitle", detail.getExamdetail().getEventname());
        addAttribute(examination, "examtime", detail.getExamdetail().getExamtime());
        addAttribute(examination, "studentid", studentid);
        addAttribute(examination, "studentname", studentname);
        //addAttribute(examination, "password", detail.getPassword());
        
        
        //Add survey
        addSurvey(doc, examination, detail.getSurvey());
        
        ArrayList sets = detail.getExamset().getSets();
        for(int i=0;i<sets.size();i++)
        {
            Set s = (Set) sets.get(i);
            if(s.getSetname().equals(set))
            {
                for(int j=0;j<s.getList().size();j++)
                {
                    Exam exam = detail.getExammap().getExam(s.getList().get(j).toString());
                    exam.setSelectedchoice(select.getSelectedchoice(s.getList().get(j).toString()));
                    addExamset(doc, examination, exam, detail.getExammap().getImagemap());
                }
                break;
            }
        }

        return DoctoString(doc);

    }
    private void addSurvey(Document doc, Element examlist, Survey s)
    {
        Element sur = addElement(doc,examlist,"survey");
         if(s==null)
        {
            addAttribute(sur, "status", "no");
        }
         else
         {
             ArrayList survey = s.getSurvey();
             addAttribute(sur, "status", "yes");
             for(int i=0;i<survey.size();i++)
             {
                  Element topicelement = addElement(doc,sur,"topic");
                  
                 ArrayList topic = (ArrayList)survey.get(i);
                 String topicname = topic.get(0).toString();
                 addAttribute(topicelement, "type", topicname);
                 ArrayList value = (ArrayList)topic.get(1);
                 for(int j=0;j<value.size();j++)
                 {
                     Element item = addElement(doc,topicelement,"item");
                     addAttribute(item, "text", value.get(j).toString());
                 }
             }
         }
    }
    private void addExamset(Document doc, Element examlist, Exam ex, HashMap<String,String> imagemap)
    {
                        Element exam = addElement(doc,examlist,"exam");
                        
                        addAttribute(exam, "column", ""+ex.getColumn());
                        addElementandtext(doc, exam, "examid", ex.getexamID());
                        addElementandtext(doc, exam, "examtype", ex.getexamType());
                        addElementandtext(doc, exam, "correct", ex.getcorrectValue());
                        addElementandtext(doc, exam, "selectedanswer", ex.getSelectedchoice());
                        addElementandtext(doc, exam, "topiccode", ex.getTopiccode());
                        addElementandtext(doc, exam, "examlevel", ex.getexamLevel());
                        addElementandtext(doc, exam, "medcode", ex.getmedCode());
                        addElementandtext(doc, exam, "textcode", ex.gettextCode());
                        addElementandtext(doc, exam, "reference", ex.getReference());
                        
                        Element question = addElement(doc,exam,"question");
                        
                        ArrayList questionlist = ex.getQuestiondetails();
                        int numpic = 0;
                        for(int l=0;l<questionlist.size();l++)
                        {
                            Element questionstem = addElement(doc, question, "stem");
                            switch (questionlist.get(l).getClass().getName()) 
                            {
                                case "java.lang.String":
                                    addElementtext(doc, questionstem, questionlist.get(l).toString());
                                    addAttribute(questionstem, "type", "text");
                                    break;
                                case "[B":
                                    //String encodedImage = Base64.encodeBase64String((byte[])questionlist.get(l));
                                    String key = ex.getexamID()+"_Q_"+numpic;
                                    imagemap.put(key,Base64.encodeBase64String((byte[])questionlist.get(l)));
                                    String encodedImage = key;
                                    addElementtext(doc, questionstem, encodedImage);
                                    addAttribute(questionstem, "type", "image");
                                    numpic++;
                                    try {
                                        BufferedImage imag=ImageIO.read(new ByteArrayInputStream((byte[])questionlist.get(l)));
                                        //max width = 1500 px, mix height = 800
                                        addAttribute(questionstem, "width", ""+imag.getWidth());
                                        addAttribute(questionstem, "height", ""+imag.getHeight());
                                    } catch (IOException ex1) {
                                        Logger.getLogger(Examtoxml.class.getName()).log(Level.SEVERE, null, ex1);
                                        addAttribute(questionstem, "width", "");
                                        addAttribute(questionstem, "height", "");
                                    }
                                    break;
                            }
                        }
                        
                        Element answer = addElement(doc,exam,"answer");
                        ArrayList answerlist = ex.getChoicedetails();
                        for(int l=0;l<answerlist.size();l++)
                        {
                            Element choice = addElement(doc, answer, "choice");
                            addAttribute(choice, "id", ex.getChoiceid().get(l).toString());
                            ArrayList choicelist = (ArrayList)answerlist.get(l);
                            
                            int numapic = 0;
                            for(int m=0;m<choicelist.size();m++)
                            {
                                Element choicestem = addElement(doc, choice, "stem");
                            switch (choicelist.get(m).getClass().getName()) 
                            {
                                case "java.lang.String":
                                    addElementtext(doc, choicestem, choicelist.get(m).toString());
                                    addAttribute(choicestem, "type", "text");
                                    break;
                                case "[B":
                                    String key = ex.getexamID()+"_C_"+ex.getChoiceid().get(l).toString()+"_"+numapic;
                                    imagemap.put(key,Base64.encodeBase64String((byte[])choicelist.get(m)));
                                    String encodedImage = key;
                                    //String encodedImage = Base64.encodeBase64String((byte[])choicelist.get(m));
                                    addElementtext(doc, choicestem, encodedImage);
                                    addAttribute(choicestem, "type", "image");
                                    numapic++;
                                    break;
                            }
                            }
                            
                        }
                        
                        Element style = addElement(doc,exam,"style");
                        
                        Element questionstyle = addElement(doc, style, "question");   
                        ArrayList questionstylelist = ex.getQuestionstyle();
                        for(int l=0;l<questionstylelist.size();l++)
                        {
                            Element questionstem = addElement(doc, questionstyle, "stem");
                            ArrayList qstylelist = (ArrayList)questionstylelist.get(l);
                            for(int n=0;n<qstylelist.size();n++)
                            {
                                Element questions = addElement(doc, questionstem, "s");
                                Element type = addElement(doc, questions, "type");
                                Element start = addElement(doc, questions, "start");
                                Element end = addElement(doc, questions, "end");
                                Style s = (Style)qstylelist.get(n);
                                addElementtext(doc, type, ""+s.getType());
                                addElementtext(doc, start, ""+s.getStart());
                                addElementtext(doc, end, ""+s.getEnd());
                            }
                        }
                        
                        Element answerstyle = addElement(doc, style, "answer");
                        ArrayList choicestylelist = ex.getChoicestyle();
                        for(int m=0;m<choicestylelist.size();m++)
                        {
                            Element choice = addElement(doc, answerstyle, "choice");
                            addAttribute(choice, "id", ex.getChoiceid().get(m).toString());
                            
                            ArrayList choicestemlist = (ArrayList)choicestylelist.get(m);
                            
                            for(int l=0;l<choicestemlist.size();l++)
                            {
                                Element choicestem = addElement(doc, choice, "stem");
                                ArrayList substyle = (ArrayList)choicestemlist.get(l);
                                for(int n=0;n<substyle.size();n++)
                                {
                                    Element choices = addElement(doc, choicestem, "s");
                                    Element type = addElement(doc, choices, "type");
                                    Element start = addElement(doc, choices, "start");
                                    Element end = addElement(doc, choices, "end");
                                    Style s = (Style)substyle.get(n);
                                    addElementtext(doc, type, s.getType());
                                    addElementtext(doc, start, ""+s.getStart());
                                    addElementtext(doc, end, ""+s.getEnd());
                                }
                            }
                            
                        }
    }
    private void addElementandtext(Document doc, Element rootelement, String name, String text)
    {
        Element element = doc.createElement(name);
        rootelement.appendChild(element);
        element.appendChild(doc.createTextNode(text));
    }
    private Element addElement(Document doc, Element rootelement, String name)
    {
        Element element = doc.createElement(name);
        rootelement.appendChild(element);
        return element;
    }
    private void addElementtext(Document doc, Element element, String text)
    {
        element.appendChild(doc.createTextNode(text));
    }
    private void addAttribute(Element element, String name, String value)
    {
        element.setAttribute(name, value);
    }
    private String DoctoString(Document doc) throws TransformerConfigurationException, TransformerException {

        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
}
}
