/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.ui;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tirupatibalajichitfund.db.*;
import tirupatibalajichitfund.utility.AllMemberUtility;
import tirupatibalajichitfund.utility.Constants;

/**
 *
 * @author abhishek
 */
public class All_Member_Panel extends javax.swing.JPanel {

    public static AllMemberClass allmember;
    DefaultTableModel dtm;
    String name;
    int uid, row;

    /**
     * Creates new form All_Member_Panel
     */
    public All_Member_Panel() throws Exception {
        initComponents();
        getTransactions.setEnabled(false);
        netValue.setText("");
        saveButton.setEnabled(false);
        allmember = AllMemberClass.getInstance();
        dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                if (!editButton.isEnabled()) {
                    if ((column == 2 || column == 3) && row == getSingleClickrow()) {
                        return true;
                    }
                }
                return false;
            }
        };
        allMemberTable.setModel(dtm);
        allMemberTable.getTableHeader().setFont(new Font("Ubuntu", Font.PLAIN, 16));
        allMemberTable.getAutoscrolls();
        allMemberTable.setRowHeight(40);
        allMemberTable.setFont(new Font("Serif", Font.PLAIN, 16));
        setuptable(allmember.getAllMembersActive());
    }

    private void setuptable(ArrayList<MemberData> members) {
        int count = 0;
        dtm.addColumn("Sno");
        dtm.addColumn("User ID");
        dtm.addColumn("Member Name");
        dtm.addColumn("Phone Number");
        dtm.addColumn("Credit");
        dtm.addColumn("Debit");

        for (MemberData member : members) {
            count++;
            dtm.addRow(new Object[]{count, member.getUid(), member.getName(), member.getPhone(), member.getCredit(), member.getDebit()});

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

        jScrollPane1 = new javax.swing.JScrollPane();
        allMemberTable = new javax.swing.JTable();
        getTransactions = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        addMemberButton = new javax.swing.JButton();
        deleteMemberButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        netValue = new javax.swing.JLabel();

        setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1600, 700));

        allMemberTable.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        allMemberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Sno", "User ID", "Member Name", "Phone Number", "Credit", "Debit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allMemberTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                allMemberTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(allMemberTable);
        if (allMemberTable.getColumnModel().getColumnCount() > 0) {
            allMemberTable.getColumnModel().getColumn(2).setPreferredWidth(240);
            allMemberTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            allMemberTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        getTransactions.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        getTransactions.setText("GET TRANSACTIONS");
        getTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTransactionsActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        addMemberButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        addMemberButton.setText("ADD MEMBER");
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });

        deleteMemberButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        deleteMemberButton.setText("DELETE MEMBER");
        deleteMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMemberButtonActionPerformed(evt);
            }
        });

        openButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        openButton.setText("OPEN");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        refreshButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        totalLabel.setText("  TOTAL ( CREDIT- DEBIT ) :");

        netValue.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        netValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1136, 1136, 1136)
                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(netValue, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1632, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addMemberButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getTransactions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteMemberButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(getTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(deleteMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 246, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(netValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setSingleClickuid(int uid) {
        this.uid = uid;
    }

    private void setSingleClickname(String name) {
        this.name = name;
    }

    private int getSingleClickuid() {
        return this.uid;
    }

    private String getSingleClickname() {
        return this.name;
    }

    private void setSingleClickrow(int row) {
        this.row = row;
    }

    private int getSingleClickrow() {
        return this.row;
    }


    private void allMemberTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allMemberTableMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int row = allMemberTable.getSelectedRow();
            int column = allMemberTable.getSelectedColumn();
            int uid = (Integer) allMemberTable.getValueAt(row, 1);
            String name = (String) allMemberTable.getValueAt(row, 2);
            if (editButton.isEnabled()) {
                double val = ((double) allMemberTable.getValueAt(row, 4)) - ((double) allMemberTable.getValueAt(row, 5));
                netValue.setText(Double.toString(val));
                setSingleClickrow(row);
                setSingleClickuid(uid);
                setSingleClickname(name);
                getTransactions.setEnabled(true);
            }
        }
        if (evt.getClickCount() == 2 && editButton.isEnabled()) {
            allMemberTable = (JTable) evt.getSource();
            int row = allMemberTable.getSelectedRow();
            int column = allMemberTable.getSelectedColumn();
            if (column == 1) {
                int uid = (Integer) allMemberTable.getValueAt(row, column);
                String name = (String) allMemberTable.getValueAt(row, 2);
                //String cfid=Integer.toString((Integer)allMemberTable.getValueAt(row, 4));
                try {
                    ArrayList<MemberInfoData> meminfo = MemberInfoClass.getInstance().getDatasetForUID(uid);
                    MainFrameChitFund.getInstance().setupInMemComPanel(meminfo, uid + "-" + name);
                } catch (Exception ex) {
                    Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_allMemberTableMousePressed

    private void getTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTransactionsActionPerformed
        // TODO add your handling code here:
        try {

            if (getSingleClickname() == null) {
                JOptionPane.showMessageDialog(this, "Please select a member");
            }
            ArrayList<TransactionData> dataset = TransactionTableClass.getInstance().getAllTransactionsForUID(getSingleClickuid());
            MainFrameChitFund.getInstance().setupTransactionTable(dataset, getSingleClickname(), Constants.MEMBER);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Occurred while fetching committee inside members");
            Logger.getLogger(All_Committee_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_getTransactionsActionPerformed
    private void disableButtons() {
        editButton.setEnabled(false);
        getTransactions.setEnabled(false);
        try {
            MainFrameChitFund.getInstance().disableBack();
        } catch (Exception ex) {
            Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveButton.setEnabled(true);
        openButton.setEnabled(false);
        addMemberButton.setEnabled(false);
        deleteMemberButton.setEnabled(false);
        refreshButton.setEnabled(false);
    }

    private void enableButtons() {
        editButton.setEnabled(true);
        getTransactions.setEnabled(false);
        try {
            MainFrameChitFund.getInstance().enableBack();
        } catch (Exception ex) {
            Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveButton.setEnabled(false);
        openButton.setEnabled(true);
        addMemberButton.setEnabled(true);
        deleteMemberButton.setEnabled(true);
        refreshButton.setEnabled(true);
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (editButton.equals(evt.getSource())) {
            if (getSingleClickrow() < 0 || getSingleClickname() == null) {
                JOptionPane.showMessageDialog(this, "Please select a member");
                return;
            }
            disableButtons();
            //  dtm.isCellEditable(getSingleClickrow(),2);
            // dtm.isCellEditable(getSingleClickrow(),3);

        }
    }//GEN-LAST:event_editButtonActionPerformed


    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (saveButton.equals(evt.getSource())) {
            if (!editButton.isEnabled()) {
                String newname = (String) allMemberTable.getValueAt(getSingleClickrow(), 2);
                String newnumb = (String) allMemberTable.getValueAt(getSingleClickrow(), 3);
                AllMemberUtility.getInstance().updateMember(getSingleClickuid(), newname, newnumb);

                enableButtons();
                try {
                    refresh();
                } catch (Exception ex) {
                    Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_saveButtonActionPerformed
    public void refresh() throws Exception {
        MainFrameChitFund.getInstance().setupMemberTable();
//   int rcount= dtm.getRowCount();
//   for(int i=rcount-1;i>=0;i--){
//   dtm.removeRow(i);
//   }
//   setuptable(allmember.getAllMembersActive());
    }
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        // TODO add your handling code here:
        try {
            ArrayList<MemberInfoData> meminfo = MemberInfoClass.getInstance().getDatasetForUID(getSingleClickuid());
            MainFrameChitFund.getInstance().setupInMemComPanel(meminfo, getSingleClickuid() + "-" + getSingleClickname());
        } catch (Exception ex) {
            Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        if (refreshButton.equals(evt.getSource())) {
            try {
                this.refresh();
            } catch (Exception ex) {
                Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMemberButtonActionPerformed
        // TODO add your handling code here:
        if (deleteMemberButton.equals(evt.getSource())) {
            if (getSingleClickrow() < 0 || getSingleClickname() == null || getSingleClickuid() < 1000) {
                JOptionPane.showMessageDialog(this, "Please select a member");
                return;
            }
            int dialogres = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete a member?");
            if (dialogres == JOptionPane.YES_OPTION) {
                try {
                    AllMemberUtility.getInstance().deleteMember(getSingleClickuid());

                    refresh();
                } catch (SQLException ex) {
                    Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_deleteMemberButtonActionPerformed

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        // TODO add your handling code here:
        if (addMemberButton.equals(evt.getSource())) {
            if (AllMemberUtility.getInstance().addNewMemeber()) {
                JOptionPane.showMessageDialog(this, "New member added");
                try {
                    refresh();
                } catch (Exception ex) {
                    Logger.getLogger(All_Member_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_addMemberButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberButton;
    private javax.swing.JTable allMemberTable;
    private javax.swing.JButton deleteMemberButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton getTransactions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel netValue;
    private javax.swing.JButton openButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
