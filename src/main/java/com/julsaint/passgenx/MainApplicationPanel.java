/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.julsaint.passgenx;

/**
 *
 * @author Ralph Julsaint
 */
public class MainApplicationPanel extends javax.swing.JFrame {
    private static final MainApplicationPanel Instance = new MainApplicationPanel();
    /** Creates new form MainApplicationPanel */
    private MainApplicationPanel() {
        setSize(500, 400);
        setResizable(false);
        if(!LoginPanel.IsLoggedIn){
            add(new LoginPanel());
        }
        else{
            add(new PasswordGenerationPanel());
        }
        setLocationRelativeTo(null);
    }

public static MainApplicationPanel GetInstance(){
    return Instance;
}   

public static void UpdatePanelContent(javax.swing.JFrame Frame, javax.swing.JPanel Panel){
    java.awt.Container Pane = Frame.getContentPane();
    Pane.removeAll();
    Pane.add(Panel);
    Pane.repaint();
    Pane.revalidate();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
