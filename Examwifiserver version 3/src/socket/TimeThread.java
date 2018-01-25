package socket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import main.Mainframe;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


public final class TimeThread extends Thread {
    private Socket socket = null;
    private Mainframe main;
    public TimeThread(Socket socket, Mainframe main) {
        this.socket = socket;
        this.main = main;
    }

    @Override
    public void run() {
        
        
        final JLabel time = main.getTimelabel();
        time.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                
                if(socket!=null)
                {
                    try {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    if(out!=null)
                    {
                        Object[] ob = {timeStringtoInt(time.getText())};
                        out.writeObject(ob);
                            out.flush();
                    }
                } catch (IOException ex) {
                        try {
                            socket.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                }
                    
                    /*
                try {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    if(out!=null)
                    {
                        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss",Locale.UK);
                        out.writeObject(date.parse(time.getText()).getTime());
                        out.flush();
                    }
                } catch (ParseException | IOException ex) {
                        try {
                            socket.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                }*/
                }
                    

            }
        });
        
        
        //Timer timer = new Timer();
        //timer.schedule(new Systemtimer(null), 0,1000);
    }
private int timeStringtoInt(String time)
{
    int second = (Integer.parseInt(time.substring(0, 2))*3600)+(Integer.parseInt(time.substring(3, 5))*60)+(Integer.parseInt(time.substring(6, 8)));
    return second;
}
  /*
    public class Systemtimer extends TimerTask{
    JLabel label;
    public Systemtimer(JLabel label)
    {
        this.label = label;
    }
    @Override
    public void run()
    {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(main.getTime());
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
*/
}
