/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import main.Mainframe;

/**
 *
 * @author bon
 */
class ExamThread extends Thread{
    java.net.Socket socket;
    Mainframe main;
    int row = -1;
    public ExamThread(java.net.Socket socket, Mainframe main)
    {
        this.socket = socket;
        this.main = main;
    }
    @Override
    public void run()
    {
        //Object[] ob = {"HELLO","ANDROID"};
        //System.out.println("Send message");
        /*
        try {
            write(ob);
        } catch (IOException ex) {
            Logger.getLogger(ExamThread.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        while(true) 
        {
            //byte[] b = new byte[1024];

                /*
                int bytes = socket.getInputStream().read(b);
                String s = new String(b,0,bytes);
                System.out.println("Get message "+s);
                * 
                */
    
            ObjectInputStream in;
                try {
                    InputStream input = socket.getInputStream();
                    in = new ObjectInputStream(input);
                    try {
                        Object[] value = (Object[])in.readObject();
                        System.out.println(value[1].toString());
                        Managemessage m = new Managemessage();
                        if(value[0].toString().equals("REGISTER"))
                        {
                            row = main.setRegisteredtablet(value[1].toString(),socket);
                            if(row>=0)
                            {
                            try {
                                m.getExamdetail(this, main.getExamdetail(), main.getStudentid(row), main.getStudentname(row), main.getSet(row), main.getSelectedchoice(row), main.getExamdetail().getExammap().getImagemap());
                            } catch (ParserConfigurationException ex) {
                                System.out.println(ex);
                                System.out.println("Socket closed");
                                if(row>=0)
                                {
                                    main.setDisconnectedstatus(row);
                                }
                                break;
                            } catch (TransformerConfigurationException ex) {
                                System.out.println(ex);
                                System.out.println("Socket closed");
                                if(row>=0)
                                {
                                    main.setDisconnectedstatus(row);
                                }
                                break;
                            } catch (TransformerException ex) {
                                System.out.println(ex);
                                System.out.println("Socket closed");
                                if(row>=0)
                                {
                                    main.setDisconnectedstatus(row);
                                }
                                break;
                            }
                            }
                        }
                        else if(value[0].toString().equals("REGISTER COMPLETE"))
                        {
                            main.setRegistercompletestatus(row);
                        }
                        else if(value[0].toString().equals("SURVEY"))
                        {
                            main.setSurveydstatus(row);
                        }
                        else if(value[0].toString().equals("SUBMIT"))
                        {
                            main.setSubmitstatus(row);
                        }
                         else if(value[0].toString().equals("SELECT"))
                        {
                            main.setSelectedchoice(value[1].toString(), value[2].toString(), value[3].toString());
                            Object[] ob = {"COMPLETE",(int)value[4]};
                            write(ob);
                        }
                        else if(value[0].toString().equals("SENDSURVEY"))
                        {
                            main.addSurveyresponse(row,value);
                        }
                        else if(value[0].toString().equals("DEACTIVATE"))
                        {
                            main.setDeactivatestatus(row);
                        }
                        else if(value[0].toString().equals("IMAGE"))
                        {
                            //System.out.println(value[1].toString());
                            String encodedimage = main.getExamdetail().getExammap().getImagemap().get(value[1].toString());
                            if(encodedimage == null)
                            {
                                encodedimage = "";
                            }
                            Object[] ob = {"IMAGE",value[1].toString(),encodedimage};
                            write(ob);
                        }
                        //System.out.println(value[0]+" "+value[1]);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                        System.out.println("Socket closed");
                        if(row>=0)
                        {
                            main.setDisconnectedstatus(row);
                        }
                        break;
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                    System.out.println("Socket closed");
                    if(row>=0)
                    {
                        main.setDisconnectedstatus(row);
                    }
                    break;
                }
        }
    }
    public void write(Object[] ob) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(ob);
    }
}
