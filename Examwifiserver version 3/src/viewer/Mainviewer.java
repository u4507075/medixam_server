/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import exammap.Exam;
import exammap.Exammap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import properties.P;

/**
 *
 * @author bon
 */
public class Mainviewer extends javax.swing.JFrame {

    /**
     * Creates new form Mainviewer
     */
    int select = 0;
    exammap.Exammap map = new Exammap();
    Viewer[] view = null;
    public Mainviewer() {
        initComponents();
        //P.DOCFILEPATH = "/home/bon/try";
        String result = map.read();
        if(result.equals("complete"))
        {
        view = new Viewer[map.getExamsize()];
        Object[] name = map.getKeyset();
        Arrays.sort(name);
        for(int j=0;j<name.length;j++)
        {
            Exam ex = map.getExam(name[j].toString());
            view[j] = new Viewer(ex);
        }
        
        if(view.length>0)
        {
            jPanel14.removeAll();
            jPanel14.add(view[select].getContentPane());
            jLabel3.setText((select+1)+"/"+map.getExamsize());
        }
        else
        {
            jLabel3.setText("No exam");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, result);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 700));
        setPreferredSize(new java.awt.Dimension(400, 700));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanel14.setMaximumSize(new java.awt.Dimension(400, 640));
        jPanel14.setMinimumSize(new java.awt.Dimension(400, 640));
        jPanel14.setPreferredSize(new java.awt.Dimension(400, 640));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel14);

        jPanel13.setMaximumSize(new java.awt.Dimension(400, 30));
        jPanel13.setMinimumSize(new java.awt.Dimension(400, 30));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setFont(new java.awt.Font("Arial", 0, 21)); // NOI18N
        jButton1.setText(" <<< ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton1);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setMaximumSize(new java.awt.Dimension(46000, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(46, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(46, 30));
        jPanel13.add(jLabel3);

        jButton2.setFont(new java.awt.Font("Arial", 0, 21)); // NOI18N
        jButton2.setText(" >>> ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2);

        getContentPane().add(jPanel13);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (select > 0) {
            select--;
            jPanel14.removeAll();
            jPanel14.add(view[select].getContentPane());
            jLabel3.setText((select+1)+"/"+map.getExamsize());
            jPanel14.repaint();
            jPanel14.revalidate();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (select < map.getExamsize()-1) {
            select++;
            jPanel14.removeAll();
            jPanel14.add(view[select].getContentPane());
            jLabel3.setText((select+1)+"/"+map.getExamsize());
            jPanel14.repaint();
            jPanel14.revalidate();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Mainviewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainviewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainviewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainviewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Mainviewer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    // End of variables declaration//GEN-END:variables
}