/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julsaint.passgenx;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Ralph Julsaint
 */
public class PasswordGenerationPanel extends javax.swing.JPanel {
    private String GeneratedPassword;
    /**
     * Creates new form PasswordGenerationPanel
     */
    public PasswordGenerationPanel() {
        initComponents();
        PasswordLengthSpinner.setValue(8);
        try {
            PasswordsTextArea.setText(new PasswordGenerator().GetPasswordsToDisplay());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(PasswordGenerationPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        PasswordEntityLabel = new javax.swing.JLabel();
        PasswordEntityTextField = new javax.swing.JTextField();
        PasswordLengthLabel = new javax.swing.JLabel();
        PasswordLengthSpinner = new javax.swing.JSpinner();
        PasswordGenerateButton = new javax.swing.JButton();
        GeneratedPasswordLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        PasswordsTextAreaScrollPane = new javax.swing.JScrollPane();
        PasswordsTextArea = new javax.swing.JTextArea();
        PasswordDeleteButton = new javax.swing.JButton();
        PassIdLabel = new javax.swing.JLabel();
        PassIdTextField = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setMinimumSize(new java.awt.Dimension(500, 400));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 400));

        PasswordEntityLabel.setText("Name:");
        PasswordEntityLabel.setToolTipText("");

        PasswordEntityTextField.setColumns(18);
        PasswordEntityTextField.setToolTipText("Name of the password entity");

        PasswordLengthLabel.setText("Length:");
        PasswordLengthLabel.setToolTipText("");

        PasswordLengthSpinner.setModel(new javax.swing.SpinnerNumberModel(8, 8, 50, 1));
        PasswordLengthSpinner.setToolTipText("Change password length here");

        PasswordGenerateButton.setText("Generate");
        PasswordGenerateButton.setToolTipText("Generate a new password");
        PasswordGenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordGenerateButtonActionPerformed(evt);
            }
        });

        GeneratedPasswordLabel.setToolTipText("");
        GeneratedPasswordLabel.setName(""); // NOI18N
        GeneratedPasswordLabel.setPreferredSize(new java.awt.Dimension(400, 14));

        SaveButton.setText("Save");
        SaveButton.setToolTipText("Save generated password");
        SaveButton.setActionCommand("");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        PasswordsTextArea.setEditable(false);
        PasswordsTextArea.setColumns(20);
        PasswordsTextArea.setRows(5);
        PasswordsTextAreaScrollPane.setViewportView(PasswordsTextArea);

        PasswordDeleteButton.setText("Delete");
        PasswordDeleteButton.setToolTipText("Delete password");
        PasswordDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordDeleteButtonActionPerformed(evt);
            }
        });

        PassIdLabel.setText("Pass ID:");
        PassIdLabel.setToolTipText("");

        PassIdTextField.setColumns(10);
        PassIdTextField.setToolTipText("Enter number of password to delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PasswordEntityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordEntityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GeneratedPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PasswordLengthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordGenerateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(PassIdLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PassIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PasswordDeleteButton))
                        .addComponent(PasswordsTextAreaScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordEntityLabel)
                    .addComponent(PasswordEntityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLengthLabel)
                    .addComponent(PasswordLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordGenerateButton)
                    .addComponent(SaveButton))
                .addGap(11, 11, 11)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GeneratedPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(PasswordsTextAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PassIdLabel)
                    .addComponent(PassIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordDeleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordGenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordGenerateButtonActionPerformed
        if(ValidateSpinnerValue()){
            PasswordGenerator Generator = new PasswordGenerator();
            GeneratedPassword = Generator.GeneratePassword((int) PasswordLengthSpinner.getValue());
            GeneratedPasswordLabel.setText(GeneratedPassword);
        }
    }//GEN-LAST:event_PasswordGenerateButtonActionPerformed
    /**
     * Validates password generation fields
     * @return true if valid, false if invalid
     */
    private boolean ValidatePasswordFields(){
        if(PasswordEntityTextField.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "You must enter a value in the name field!");
            return false;
        }
        else if(GeneratedPasswordLabel.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "You must generate a password first!");
            return false;
        }
        return true;
    }
    
    /**
     * Save the generated password
     * @param evt 
     */
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed

        if(ValidatePasswordFields()){
            PasswordGenerator PassGenerator = new PasswordGenerator();
            java.util.Random Generator = new java.util.Random();
            int PasswordId = Generator.nextInt(10000 + 1) + 10000;
            String Name = PasswordEntityTextField.getText();
            String Password = GeneratedPasswordLabel.getText();
            byte[] PasswordCipherText;
            String CiperTextToString = "";
            /*encrypt password*/
            try{
                javax.crypto.KeyGenerator KeyGenerator = javax.crypto.KeyGenerator.getInstance("AES");
                KeyGenerator.init(256);
                PasswordGenerator.key = KeyGenerator.generateKey();               
                javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, PasswordGenerator.key, new java.security.SecureRandom());
                PasswordCipherText = cipher.doFinal(Password.getBytes());
                CiperTextToString = Base64.getEncoder().encodeToString(PasswordCipherText);
                PasswordGenerator.CiperTextMap.put(CiperTextToString, PasswordGenerator.key);
            }
            catch(NoSuchAlgorithmException e){
                System.err.println(e);  
            } catch (NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(PasswordGenerationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean IsSaved = PassGenerator.SavePassword(PasswordId, Name, CiperTextToString);
            if(IsSaved){
                javax.swing.JOptionPane.showMessageDialog(this, "Password saved.");
                try {
                    PasswordsTextArea.setText(new PasswordGenerator().GetPasswordsToDisplay());//update text area after adding password
                } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(PasswordGenerationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Password not saved.");
            }
            PasswordEntityTextField.setText("");
            GeneratedPasswordLabel.setText("");
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void PasswordDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordDeleteButtonActionPerformed
        String PassId = PassIdTextField.getText();
        PasswordGenerator Generator = new PasswordGenerator();
        if(!PassId.equals("")){
            boolean result = Generator.DeletePassword(PassId);
            if(result){
                javax.swing.JOptionPane.showMessageDialog(this, "Password deleted.");
                try {
                    PasswordsTextArea.setText(new PasswordGenerator().GetPasswordsToDisplay());//update text area after deleting password
                } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(PasswordGenerationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this, "Password not deleted.");
            }
        }
        else{
            javax.swing.JOptionPane.showMessageDialog(this, "Enter a valid password ID.");
        }
    }//GEN-LAST:event_PasswordDeleteButtonActionPerformed

    
    private boolean ValidateSpinnerValue(){
        if((int) PasswordLengthSpinner.getValue() < 8){
            javax.swing.JOptionPane.showMessageDialog(this, "Password length must be 8 or greater!");
            return false;
        }
        return true;
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GeneratedPasswordLabel;
    private javax.swing.JLabel PassIdLabel;
    private javax.swing.JTextField PassIdTextField;
    private javax.swing.JButton PasswordDeleteButton;
    private javax.swing.JLabel PasswordEntityLabel;
    private javax.swing.JTextField PasswordEntityTextField;
    private javax.swing.JButton PasswordGenerateButton;
    private javax.swing.JLabel PasswordLengthLabel;
    private javax.swing.JSpinner PasswordLengthSpinner;
    private javax.swing.JTextArea PasswordsTextArea;
    private javax.swing.JScrollPane PasswordsTextAreaScrollPane;
    private javax.swing.JButton SaveButton;
    private javax.swing.Box.Filler filler1;
    // End of variables declaration//GEN-END:variables
}
