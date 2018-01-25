/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Mainframe;

/**
 *
 * @author bon
 */
public class Examsocket extends Thread{
    ServerSocket socket;
    Mainframe main;
    boolean listening = true;
    public Examsocket(ServerSocket socket, Mainframe main)
    {
        this.socket = socket;
        this.main = main;
        listening = true;
    }
    @Override
    public void run()
    {
        while (listening)
        {
            System.out.println("Start listening on Exam socket.");
            if(socket!=null)
            {
                try {
                    ExamThread client = new ExamThread(socket.accept(),main);
                    client.start();

                } catch (IOException ex) {
                    System.out.println("Stop listening on Exam socket.");
                }
             }
        }

            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ExamThread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void stopSocket()
    {
        listening = false;
        if(socket!=null)
        {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Examsocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
