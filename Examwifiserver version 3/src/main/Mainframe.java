/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import backup.Autosaverecord;
import exam.Detail;
import exammap.Selectedchoice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import properties.P;
import sheet.Examset.Set;
import sheet.Studentdetail.Student;
import socket.Mysocket;
import table.Excelfilefilter;
import table.Record;
import table.Result;
import table.Surveytable;

/**
 *
 * @author bon
 */
public class Mainframe extends javax.swing.JFrame {

    Detail d;
    Result result;
    Surveytable survey;
    Record record;
    socket.Timer mytimer;
    HashMap<String, progress> progressmap = new HashMap<>();
    public Mainframe(Detail d) {
        initComponents();
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        setSize(dim);
        this.d = d;
        //test();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel23 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exam server version 1.3");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setBackground(java.awt.Color.gray);
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 21)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel1.setBackground(java.awt.Color.black);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setBackground(java.awt.Color.white);
        jPanel7.setMaximumSize(new java.awt.Dimension(350000, 40));
        jPanel7.setMinimumSize(new java.awt.Dimension(0, 40));
        jPanel7.setPreferredSize(new java.awt.Dimension(1374, 40));
        jPanel7.setRequestFocusEnabled(false);
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setBackground(java.awt.Color.lightGray);
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setMaximumSize(new java.awt.Dimension(50, 50000));
        jPanel12.setMinimumSize(new java.awt.Dimension(50, 80));
        jPanel12.setPreferredSize(new java.awt.Dimension(50, 43));

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel5.setText("Time");
        jPanel12.add(jLabel5);

        jPanel7.add(jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setMaximumSize(new java.awt.Dimension(80, 50000));
        jPanel13.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel13.setPreferredSize(new java.awt.Dimension(80, 43));
        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        jLabel6.setBackground(java.awt.Color.green);
        jLabel6.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("00:00:00");
        jLabel6.setOpaque(true);
        jPanel13.add(jLabel6);

        jPanel7.add(jPanel13);

        jPanel14.setBackground(java.awt.Color.white);
        jPanel14.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel14.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel14.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel7.add(jPanel14);

        jLabel9.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel9.setMaximumSize(new java.awt.Dimension(51000000, 170));
        jLabel9.setMinimumSize(new java.awt.Dimension(30, 33));
        jLabel9.setPreferredSize(new java.awt.Dimension(30, 0));
        jPanel7.add(jLabel9);

        jPanel15.setBackground(java.awt.Color.white);
        jPanel15.setMaximumSize(new java.awt.Dimension(150, 50000));
        jPanel15.setMinimumSize(new java.awt.Dimension(0, 60));
        jPanel15.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jPanel16.setBackground(java.awt.Color.white);
        jPanel16.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel16.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel16.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel15.add(jPanel16);

        jPanel7.add(jPanel15);

        jPanel17.setBackground(java.awt.Color.white);
        jPanel17.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel17.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel17.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel7.add(jPanel17);

        jPanel18.setBackground(java.awt.Color.orange);
        jPanel18.setMaximumSize(new java.awt.Dimension(90, 50000));
        jPanel18.setMinimumSize(new java.awt.Dimension(90, 60));
        jPanel18.setPreferredSize(new java.awt.Dimension(90, 60));

        jComboBox1.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "5", "30", "60", "-1", "-5", "-30", "-60", "reset" }));
        jComboBox1.setToolTipText("minute");
        jComboBox1.setMaximumSize(new java.awt.Dimension(700, 30));
        jComboBox1.setMinimumSize(new java.awt.Dimension(70, 30));
        jComboBox1.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel18.add(jComboBox1);

        jPanel7.add(jPanel18);

        jPanel19.setBackground(java.awt.Color.orange);
        jPanel19.setMaximumSize(new java.awt.Dimension(90, 50000));
        jPanel19.setMinimumSize(new java.awt.Dimension(90, 60));
        jPanel19.setPreferredSize(new java.awt.Dimension(90, 60));

        jButton1.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jButton1.setText("ADD");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setMaximumSize(new java.awt.Dimension(70, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(70, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(70, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton1);

        jPanel7.add(jPanel19);

        jPanel21.setBackground(java.awt.Color.orange);
        jPanel21.setMaximumSize(new java.awt.Dimension(100, 50000));
        jPanel21.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel21.setPreferredSize(new java.awt.Dimension(100, 60));

        jButton5.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jButton5.setText("START");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setMaximumSize(new java.awt.Dimension(80, 30));
        jButton5.setMinimumSize(new java.awt.Dimension(80, 30));
        jButton5.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton5);

        jPanel7.add(jPanel21);

        jPanel22.setBackground(java.awt.Color.orange);
        jPanel22.setMaximumSize(new java.awt.Dimension(100, 50000));
        jPanel22.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel22.setPreferredSize(new java.awt.Dimension(100, 60));

        jButton6.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jButton6.setText("STOP");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setMaximumSize(new java.awt.Dimension(80, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(80, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton6);

        jPanel7.add(jPanel22);

        jPanel1.add(jPanel7);

        jScrollPane1.setBackground(java.awt.Color.black);

        jPanel8.setBackground(java.awt.Color.black);
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(jPanel8);

        jPanel1.add(jScrollPane1);

        jTabbedPane1.addTab("status", jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel20.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel20.setMinimumSize(new java.awt.Dimension(81, 40));
        jPanel20.setPreferredSize(new java.awt.Dimension(947, 40));
        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

        jButton2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton2.setText("Load records");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton2);

        jButton3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton3.setText("Export");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton3);

        jPanel9.add(jPanel20);

        jPanel23.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane2.setViewportView(jPanel23);

        jPanel9.add(jScrollPane2);

        jPanel2.add(jPanel9);

        jTabbedPane1.addTab("result", jPanel2);

        jPanel4.setBackground(java.awt.Color.lightGray);
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel25.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane4.setViewportView(jPanel25);

        jPanel4.add(jScrollPane4);

        jTabbedPane1.addTab("survey", jPanel4);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel24.setBackground(java.awt.Color.black);
        jPanel24.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel24.setMinimumSize(new java.awt.Dimension(81, 40));
        jPanel24.setPreferredSize(new java.awt.Dimension(947, 40));
        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.LINE_AXIS));

        jPanel27.setBackground(java.awt.Color.black);
        jPanel27.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel27.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel27.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel24.add(jPanel27);

        jLabel11.setBackground(java.awt.Color.lightGray);
        jLabel11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel11.setText(" Last record time: ");
        jLabel11.setOpaque(true);
        jPanel24.add(jLabel11);

        jPanel26.setBackground(java.awt.Color.black);
        jPanel26.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel26.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel26.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel24.add(jPanel26);

        jLabel12.setBackground(java.awt.Color.white);
        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("00:00:00");
        jLabel12.setMaximumSize(new java.awt.Dimension(110, 29));
        jLabel12.setMinimumSize(new java.awt.Dimension(110, 29));
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(110, 29));
        jPanel24.add(jLabel12);

        jPanel28.setBackground(java.awt.Color.black);
        jPanel28.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel28.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel28.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel24.add(jPanel28);

        jLabel13.setBackground(java.awt.Color.lightGray);
        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setText(" Next record in: ");
        jLabel13.setOpaque(true);
        jPanel24.add(jLabel13);

        jPanel29.setBackground(java.awt.Color.black);
        jPanel29.setMaximumSize(new java.awt.Dimension(10, 60));
        jPanel29.setMinimumSize(new java.awt.Dimension(10, 60));
        jPanel29.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel24.add(jPanel29);

        jLabel14.setBackground(java.awt.Color.yellow);
        jLabel14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("60");
        jLabel14.setMaximumSize(new java.awt.Dimension(110, 29));
        jLabel14.setMinimumSize(new java.awt.Dimension(110, 29));
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(110, 29));
        jPanel24.add(jLabel14);

        jPanel3.add(jPanel24);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane3.setViewportView(jPanel10);

        jPanel3.add(jScrollPane3);

        jTabbedPane1.addTab("record", jPanel3);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int time = timeStringtoInt(jLabel6.getText());
        if(jComboBox1.getSelectedItem().toString().equals("reset"))
        {
            time = 0;
        }
        else
        {
            time = time +(Integer.parseInt(jComboBox1.getSelectedItem().toString())*60);
            if(time<0)
            {
                time = 0;
            }
        }
        jLabel6.setText(timeInttoString(time));
        if(mytimer!=null)
        {
            updateTIme();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        startAll();

        if(mytimer!=null)
        {
            mytimer.startCountdown();
        }
        Object[] ob = {"START ALL"};
         record.insertData(ob);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        forcestopAll();

        if(mytimer!=null)
        {
            mytimer.stopCountdown();
        }

        Object[] ob = {"STOP ALL"};
         record.insertData(ob);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // export a result sheet
        try {
            

                JFileChooser fc = new JFileChooser();
                FileFilter filter = new Excelfilefilter(".xls", new String[] { "xls" });
                fc.setFileFilter(filter);
                File file = new File(d.getExamdetail().getEventname()+" result.xls");
                fc.setSelectedFile(file);
                int returnVal = fc.showSaveDialog(this); //parent component to JFileChooser
                
                if (returnVal == JFileChooser.APPROVE_OPTION) { //OK button pressed by user

                        createSheet(fc.getSelectedFile());
                        JOptionPane.showMessageDialog(rootPane, "Successfully created an excel file.");
                }

            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Failure to create an excel file.");
        } catch (IOException ex) {
            Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Failure to create an excel file.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // load record
        
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose a record excel file.");
        FileFilter filter = new Excelfilefilter(".xls", new String[] { "xls" });
        chooser.setFileFilter(filter);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            try {
                FileInputStream file = new FileInputStream(new File(chooser.getSelectedFile().getAbsolutePath()));
                try {
                    HSSFWorkbook wb = new HSSFWorkbook(file);
                    HSSFSheet recordsheet = wb.getSheet("record");
                    for(int i=0;i<recordsheet.getPhysicalNumberOfRows();i++)
                    {
                        if(getValue(recordsheet.getRow(i).getCell(2)).equals("SELECT"))
                        {
                            String studentid = getValue(recordsheet.getRow(i).getCell(3));
                            String questionid = getValue(recordsheet.getRow(i).getCell(4));
                            String selectedchoiceid = getValue(recordsheet.getRow(i).getCell(5));
                            setSelectedchoice(studentid, questionid, selectedchoiceid);
                        }
                        else if(getValue(recordsheet.getRow(i).getCell(2)).equals("TABLET REGISTRATION"))
                        {
                            String studentid = getValue(recordsheet.getRow(i).getCell(3));
                            String tabletid = getValue(recordsheet.getRow(i).getCell(4));
                            setRegistertablet(studentid, tabletid);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Mainframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
        //System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JLabel[] l = new JLabel[1];
        JLabel l1 = new JLabel("Do you really want to exit the program?");
        Font font = new Font(getFont().getFontName(),getFont().getStyle(),40);
        l1.setFont(font);
        l[0] = l1;
        int close = JOptionPane.showConfirmDialog(rootPane, l, "Close", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(close == JOptionPane.YES_OPTION)
        {
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        else
        {
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Mainframe m = new Mainframe(null);
                m.test();
                m.setVisible(true);
            }
        });
    }
    public void test()
    {
        
        P.DOCFILEPATH = "/home/bon/NetBeansProjects/Examwifiserver/Doc file";
        P.EXCELPATH = "/home/bon/NetBeansProjects/Examwifiserver/Excel file/set.xls";
        
        d = new Detail();
        String result = d.getDetail();
        System.out.println(result);
        
        start();
    }
    public void start()
    {
         jLabel6.setText(timeInttoString(Integer.parseInt(d.getExamdetail().getExamtime())*60));
        jLabel9.setText(d.getExamdetail().getEventname());
        addRecordtable();
        addProgression(d);
        addResulttable(d);
        addSurveytable();
        
        
        Mysocket socket = new Mysocket();
        socket.start(this);
        
        java.util.Timer timer = new java.util.Timer();
        mytimer= new socket.Timer(jLabel6,this);
        timer.schedule(mytimer, 0,1000);

        Object[] ob = {"START SERVER"};
        record.insertData(ob);
    }
    public Detail getExamdetail()
    {
        return d;
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
    private void addProgression(Detail d)
    {
        ArrayList students = d.getStudentdetail().getStudents();
        ArrayList pp = new ArrayList();
        int num = 0;
        for(int i=0;i<students.size();i++)
        {
            Student student = (Student) students.get(i);
            progress p = new progress(this,d.getExamdetail().getSurveystatus(),record);
            p.setStudentID(student.getStudentid());
            for(int j=0;j<d.getExamset().getSets().size();j++)
            {
                Set s = (Set) d.getExamset().getSets().get(j);
                if(s.getSetname().equals(student.getSet()))
                {
                    int total = s.getList().size();
                    int done = 0;
                    p.setTotal(total);
                    p.setProgression(done);
                    break;
                }
            }
            p.getContentPane().setBackground(Color.black);
            progressmap.put(student.getStudentid(), p);
            if(num == 9 || i == 0)
            {
                JPanel panel = new JPanel();
                BoxLayout box = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
                panel.setLayout(box);
                panel.setMinimumSize(new Dimension(430,600));
                panel.setPreferredSize(new Dimension(430,600));
                pp.add(panel);
                ((JPanel)pp.get(pp.size()-1)).add(p.getContentPane());
                num = 0;
            }
            else
            {
                ((JPanel)pp.get(pp.size()-1)).add(p.getContentPane());
                num++;
            }
            //jPanel8.add(p.getContentPane());
        }
        for(int i=0;i<pp.size();i++)
        {
            jPanel8.add((JPanel)pp.get(i));
        }
    }
    public void startAll()
    {
        for (progress p : progressmap.values()) {
            p.startExam();
        }
    }
    public void updateTIme()
    {
        for (progress p : progressmap.values()) {
            p.updateTime();
        }
    }
    public void forcestopAll()
    {
        for (progress p : progressmap.values()) {
            p.forcestopExam();
        }
    }
    public void stopAll()
    {
        for (progress p : progressmap.values()) {
            p.stopExam();
        }
    }
    private void addResulttable(Detail d)
    {
        result = new Result(d);
        jPanel23.add(result);
    }
    private void addSurveytable()
    {
        survey = new Surveytable();
        jPanel25.add(survey);
    }
    private void addRecordtable()
    {
        record = new Record();
        jPanel10.add(record);
        
        Autosaverecord save = new Autosaverecord(this,record, d.getExamdetail().getEventname());
        save.start();
    }
    public void setCountdown(String num)
    {
        jLabel14.setText(num);
    }
    public void setLastrecordtime(String time)
    {
        jLabel12.setText(time);
    }
    private void createSheet(File file) throws FileNotFoundException, IOException
    {
        if(result!=null && survey!=null)
        {
        Workbook wb = new HSSFWorkbook();
        Sheet resultsheet = wb.createSheet("result");
        Sheet surveysheet = wb.createSheet("survey");
        
        for(int i=0;i<result.getRowCount();i++)
        {
            Row row = resultsheet.createRow(i);
            for(int j=0;j<result.getColumnCount();j++)
            {
                Cell cell = row.createCell(j);
                Object v = result.getValueAt(i,j);
                if(v==null)
                {
                    cell.setCellValue("");
                }
                else if(v instanceof String)
                {
                    cell.setCellValue(v.toString());
                }
                else if(v instanceof Integer)
                {
                    cell.setCellValue((int)v);
                }
                else
                {
                    cell.setCellValue("");
                }
                
            }
        }
        for(int i=0;i<survey.getRowCount();i++)
        {
            Row row = surveysheet.createRow(i);
            for(int j=0;j<survey.getColumnCount();j++)
            {
                Cell cell = row.createCell(j);
                Object v = survey.getValueAt(i,j);
                if(v==null)
                {
                    cell.setCellValue("");
                }
                else if(v instanceof String)
                {
                    cell.setCellValue(v.toString());
                }
                else if(v instanceof Integer)
                {
                    cell.setCellValue((int)v);
                }
                else
                {
                    cell.setCellValue("");
                }
                
            }
        }
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
            }
        }
    }
    public int setRegisteredtablet(String tabletname, Socket socket)
    {
        int registerrow = -1;
        int emptyrow = -1;
        for(int i=1;i<result.getRowCount();i++)
        {
            if(result.getValueAt(i, 0)==null || result.getValueAt(i, 0).toString().equals(""))
            {
                break;
            }
            if(result.getValueAt(i, 3)==null || result.getValueAt(i, 3).toString().equals(""))
            {
                if(emptyrow == -1)
                {
                    emptyrow = i;
                }
            }
            if(result.getValueAt(i, 3)!=null && result.getValueAt(i, 3).toString().equals(tabletname))
            {
                if(registerrow == -1)
                {
                    registerrow = i;
                }
            }
            /*
            if(result.getValueAt(i, 3)!=null && result.getValueAt(i, 3)==tabletname)
            {
                JOptionPane.showMessageDialog(rootPane, "Tablet name \""+tabletname+"\" is already registered at row"+i);
                return -1;
            }
            * 
            */
        }
        if(registerrow>=0)
        {
            result.setValueAt(tabletname, registerrow, 3);
            progress p = progressmap.get(result.getValueAt(registerrow, 0).toString());
            if(p!=null)
            {
                p.setSocket(socket);
                //p.setRegisteredstatus();
                Object[] ob = {"TABLET REGISTRATION", result.getValueAt(registerrow, 0).toString(), tabletname};
                record.insertData(ob);
            }
            result.updateColumnWidth();
            return registerrow;
        }
        else if(emptyrow>=0)
        {
            result.setValueAt(tabletname, emptyrow, 3);
            progress p = progressmap.get(result.getValueAt(emptyrow, 0).toString());
            if(p!=null)
            {
                p.setSocket(socket);
                //p.setRegisteredstatus();
                Object[] ob = {"TABLET REGISTRATION", result.getValueAt(emptyrow, 0).toString(),tabletname};
                record.insertData(ob);
            }
            result.updateColumnWidth();
            return emptyrow;
        }
        else
        {
            return -1;
        }
    }
    public String getStudentid(int row)
    {
        return result.getValueAt(row, 0).toString();
    }
    public String getStudentname(int row)
    {
        return result.getValueAt(row, 1).toString();
    }
    public String getSet(int row)
    {
        return result.getValueAt(row, 2).toString();
    }
    public void setRegistercompletestatus(int row)
    {
        //result.setValueAt("", row, 3);
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setRegisteredstatus();
            Object[] ob = {"REGISTER COMPLETE", getStudentid(row)};
            record.insertData(ob);
        }
    }
    public void setDisconnectedstatus(int row)
    {
        //result.setValueAt("", row, 3);
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setDisconnectedstatus();
            Object[] ob = {"DISCONNECT", getStudentid(row)};
            record.insertData(ob);
        }
    }
    public void setDeactivatestatus(int row)
    {
        //result.setValueAt("", row, 3);
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setDeactivatestatus();
            Object[] ob = {"DEACTIVATE", getStudentid(row)};
            record.insertData(ob);
        }
    }
    public void setSurveydstatus(int row)
    {
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setSurveystatus();
            Object[] ob = {"SURVEY", getStudentid(row)};
             record.insertData(ob);
        }
    }
    public void setSubmitstatus(int row)
    {
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setSubmitstatus();
            Object[] ob = {"SUBMIT", getStudentid(row)};
             record.insertData(ob);
        }
    }
    public JLabel getTimelabel()
    {
        return jLabel6;
    }
    public void setSelectedchoice(String studentid, String questionid, String selectedchoiceid)
    {
        int row = result.getRow(studentid);
        int column = result.getColumn(questionid);
        result.setValueAt(selectedchoiceid, row, column);
        int[] score = result.calScore(row);
        result.setValueAt(""+score[0],row,4);
        progressmap.get(studentid).setProgression(score[1]);
        Object[] ob = {"SELECT", studentid, questionid, selectedchoiceid};
        record.insertData(ob);
    }
    private void setRegistertablet(String studentid, String tabletid)
    {
        int row = result.getRow(studentid);
        if(row > 1)
        {
            result.setValueAt(tabletid, row, 3);
        }
    }
    public void addSurveyresponse(int row, Object[] value)
    {
        survey.insertData(value);
        record.insertData(value);
        
        progress p = progressmap.get(result.getValueAt(row, 0).toString());
        if(p!=null)
        {
            p.setSubmitstatus();
            Object[] ob = {"SUBMIT", getStudentid(row)};
             record.insertData(ob);
        }
    }
    public Selectedchoice getSelectedchoice(int row)
    {
        return result.getSelectedchoice(row);
    }
    private String getValue(HSSFCell cell)
    {
        if(cell == null || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
        {
            return "";
        }
        else if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING)
        {
            return cell.getStringCellValue();
        }
        else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
        {
            return fmt(cell.getNumericCellValue());
        }
        else
        {
            return "";
        }
    }
    private String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
