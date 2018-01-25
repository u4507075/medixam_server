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
public class Timesocket extends Thread{
    ServerSocket socket;
    boolean listening = true;
    Mainframe main;
    public Timesocket(ServerSocket socket, Mainframe main)
    {
        this.socket = socket;
        this.main = main;
    }
    @Override
    public void run()
    {
        while (listening)
        {
            //System.err.println("Start listening on Time socket.");
                try {
                    new TimeThread(socket.accept(),main).start();
                } catch (IOException ex) {
                    Logger.getLogger(Timesocket.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Timesocket.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
