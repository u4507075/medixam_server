/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.awt.Color;
import java.util.TimerTask;
import javax.swing.JLabel;
import main.Mainframe;

/**
 *
 * @author bon
 */
public class Timer extends TimerTask{
    JLabel label;
    Mainframe main;
    boolean start = false;
    public Timer(JLabel label, Mainframe main)
    {
        this.label = label;
        this.main = main;
    }
    @Override
    public void run()
    {
        
        if(start)
        {
        if(!label.getText().equals("00:00:00"))
        {
            int time = timeStringtoInt(label.getText());
            time--;
            String t = timeInttoString(time);
            label.setText(t);
            setBackground(label,time);
        }
        else
        {
            main.stopAll();
        }
        }
    }
    public void startCountdown()
    {
        start = true;
    }
    public void stopCountdown()
    {
        start = false;
    }
private int timeStringtoInt(String time)
{
    int second = (Integer.parseInt(time.substring(0, 2))*3600)+(Integer.parseInt(time.substring(3, 5))*60)+(Integer.parseInt(time.substring(6, 8)));
    return second;
}
private String timeInttoString(int second)
{
    int hour = second/3600;
    int min = (second%3600)/60;
    int sec = (second%3600)%60;
    
    String h;
    String m;
    String s;
    
    if(hour<10)
    {
        h = "0"+hour;
    }
    else
    {
        h = ""+hour;
    }
    if(min<10)
    {
        m = "0"+min;
    }
    else
    {
        m = ""+min;
    }
    if(sec<10)
    {
        s = "0"+sec;
    }
    else
    {
        s = ""+sec;
    }
    
    return h+":"+m+":"+s;
}
private void setBackground(JLabel label, int time)
{
    if(time<1*60)
    {
        label.setBackground(Color.red);
    }
    else if(time<5*60)
    {
        label.setBackground(Color.yellow);
    }
    else
    {
        label.setBackground(Color.green);
    }
}
}
