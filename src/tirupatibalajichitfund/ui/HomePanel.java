/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import tirupatibalajichitfund.db.AllCommitteeClass;
import tirupatibalajichitfund.utility.Constants;

/**
 *
 * @author abhishek
 */
public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form HomePanel
     */
    public HomePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        conductButton = new javax.swing.JButton();
        addMemberToCommButton = new javax.swing.JButton();
        shortcutText = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1600, 800));

        conductButton.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        conductButton.setText("CONDUCT A COMMITTEE");
        conductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conductButtonActionPerformed(evt);
            }
        });

        addMemberToCommButton.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        addMemberToCommButton.setText("QUICK ADD MEMBER TO COMMITTEE");
        addMemberToCommButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberToCommButtonActionPerformed(evt);
            }
        });

        shortcutText.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        shortcutText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shortcutText.setText("SHORTCUT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shortcutText, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(conductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(addMemberToCommButton, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(shortcutText, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMemberToCommButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(390, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addMemberToCommButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberToCommButtonActionPerformed
        // TODO add your handling code here:
        if (addMemberToCommButton.equals(evt.getSource())) {
            AddMemberToCommittee j = new AddMemberToCommittee();
            j.setVisible(true);
        }
    }//GEN-LAST:event_addMemberToCommButtonActionPerformed

    private void conductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conductButtonActionPerformed
        // TODO add your handling code here:
        if (conductButton.equals(evt.getSource())) {
            QuickAddTransaction qat = new QuickAddTransaction();
            try {
                qat.QuickAddForConduct("Conduct Committee", Constants.CONDUCT_COMMITTEE, AllCommitteeClass.getInstance().getAllCommitteesActive());
            } catch (Exception ex) {
                Logger.getLogger(MainFrameChitFund.class.getName()).log(Level.SEVERE, null, ex);
            }
            qat.setVisible(true);
        }

    }//GEN-LAST:event_conductButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberToCommButton;
    private javax.swing.JButton conductButton;
    private javax.swing.JLabel shortcutText;
    // End of variables declaration//GEN-END:variables
}