/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Mainframe;

/**
 *
 * @author bon
 */
public class Mysocket {
    Examsocket ex;
    //Timesocket time;
    public void start(Mainframe main)
    {
        ServerSocket s;
        //ServerSocket t;
        try {
            s = new ServerSocket(6000);
            ex = new Examsocket(s,main);
            ex.start();
            
            //t = new ServerSocket(7000);
            //time = new Timesocket(t, main);
            //time.start();
        } catch (IOException ex) {
            Logger.getLogger(Mysocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void stop(Mainframe main)
    {
        if(ex!=null)
        {
            ex.stopSocket();
        }
    }
}
