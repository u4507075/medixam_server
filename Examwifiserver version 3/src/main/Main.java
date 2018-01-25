/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import backup.Autosaverecord;
import check.Examcheck;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import viewer.Settingviewer;

/**
 *
 * @author bon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception e) {
        // If Nimbus is not available, you can set the GUI to another look and feel.
    }

        //Server
        Setting set = new Setting();
        set.setVisible(true);

        /*
        //Exam viewer
        Settingviewer view = new Settingviewer();
        view.setVisible(true);
        */
        
        //Exam check
        //Examcheck ck = new Examcheck();
        //ck.setVisible(true);
    }
}
