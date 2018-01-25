/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import exam.Detail;
import exam.Examtoxml;
import exammap.Selectedchoice;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author bon
 */
public class Managemessage {
    public void getExamdetail(ExamThread socket, Detail d, String studentid, String studentname, String set, Selectedchoice select, HashMap<String,String> imagemap) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, IOException
    {
        Examtoxml xml = new Examtoxml();
        String text;
            
            text = xml.convert(d, studentid, studentname, set, select);
            String[][] map = new String[imagemap.size()][2];
            int i = 0;
            for ( String key : imagemap.keySet() ) {
                map[i][0] = key;
                map[i][1] = imagemap.get(key).toString();
                i++;
            }
            Object[] ob = {"EXAMDETAIL", text, map};

                socket.write(ob);
                System.out.println("Send exam details.");


    }
}
