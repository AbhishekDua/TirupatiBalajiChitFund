/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tirupatibalajichitfund.db.CommitteeData;
import tirupatibalajichitfund.db.MemberData;
import tirupatibalajichitfund.db.MemberInfoClass;
import tirupatibalajichitfund.db.MemberInfoData;
import tirupatibalajichitfund.utility.Constants;
import tirupatibalajichitfund.utility.TransactionUtility;

/**
 *
 * @author abhishek
 */
public class QuickAddTransaction extends javax.swing.JFrame {

    /**
     * Creates new form QuickAddTransaction
     */
    private String TAG = "";
    private MemberData member;
    private CommitteeData committee;
    int enteries;
    ArrayList<CommitteeData> cList;
    ArrayList<MemberData> mList;
    String given_heading;
    ArrayList<MemberInfoData> memcomList;

    public QuickAddTransaction() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void QuickAddTransactionSetup(MemberData mem, CommitteeData comm, String heading, int entry, String tag) {
        TAG = tag;
        enteries = entry;
        member = mem;
        committee = comm;
        given_heading = heading;
        setupForIncomMem();
    }

    public void QuickAddForConduct(String heading, String tag, ArrayList<CommitteeData> commList) {
        TAG = tag;
        titleLabel.setText(heading);
        memberList.setVisible(false);
        selectMemberLabel.setVisible(false);
        committeeList.addItem("None");
        for (CommitteeData c : commList) {
            committeeList.addItem(c.getCname());
        }
        cList = commList;
        dateText.setText(this.getTodayDate());
        amountText.setText("0.0");
        forTurnText.setText("   " + "0" + "");
        addButton.setText("Conduct Committee");

    }

    void setupForIncomMem() {
        if (committee != null) {
            committeeList.addItem(committee.getCname());
        }
        if (member != null) {
            memberList.addItem(member.getUid() + " " + member.getName());
        }
        dateText.setText(this.getTodayDate());
        amountText.setText("0.0");
        forTurnText.setText("   " + committee.getTurn() + "");
        committeeList.setEnabled(false);
        memberList.setEnabled(false);
    }

    //Called to setup through Committee Add Transaction
    public void QuickAddTransactionSetup(ArrayList<MemberInfoData> memberList, CommitteeData com, String heading, String tag) {
        committee = com;
        given_heading = heading;
        TAG = tag;
        memcomList = memberList;
        setupForCommittee();

    }

    private void setupForCommittee() {
        if (committee != null) {
            committeeList.addItem(committee.getCname());
        }
        committeeList.setEnabled(false);
        if (memcomList != null) {
            memberList.addItem("None");
            for (MemberInfoData mid : memcomList) {
                memberList.addItem(mid.getMember().getUid() + "- " + mid.getMember().getName());
            }
        }
        dateText.setText(this.getTodayDate());
        amountText.setText("0.0");
        forTurnText.setText("   " + committee.getTurn() + "");
    }

    //called to setup through Member Add Transaction
    public void QuickAddTransactionSetup(ArrayList<MemberInfoData> comList, MemberData mem, String heading, String tag) {
        member = mem;
        given_heading = heading;
        TAG = tag;
        memcomList = comList;
        setupForMember();
    }

    public void QuickAddTransactionMainFrameSetup(ArrayList<CommitteeData> commList) {
        committeeList.addItem("None");
        for (CommitteeData c : commList) {
            committeeList.addItem(c.getCname());
        }
        cList = commList;
        memberList.setEnabled(false);
        dateText.setText(this.getTodayDate());
        amountText.setText("0.0");
        forTurnText.setText("   " + "0" + "");
    }

    private void setupForMember() {
        if (member != null) {
            memberList.addItem(member.getUid() + "- " + member.getName());
        }
        memberList.setEnabled(false);
        if (memcomList != null) {
            committeeList.addItem("None");
            for (MemberInfoData mid : memcomList) {
                committeeList.addItem(mid.getCommittee().getCname());
            }
        }
        dateText.setText(this.getTodayDate());
        amountText.setText("0.0");
        forTurnText.setText("   " + "0" + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        selectMemberLabel = new javax.swing.JLabel();
        memberList = new javax.swing.JComboBox();
        selectCommitteeLabel = new javax.swing.JLabel();
        committeeList = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        amountText = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        transactionTypeCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        forTurnText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("ADD TRANSACTION");

        selectMemberLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        selectMemberLabel.setText("SELECT MEMBER:");

        memberList.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        memberList.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        memberList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberListActionPerformed(evt);
            }
        });

        selectCommitteeLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        selectCommitteeLabel.setText("SELECT COMMITTEE:");

        committeeList.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        committeeList.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        committeeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                committeeListActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("AMOUNT:");

        amountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountTextActionPerformed(evt);
            }
        });

        typeLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        typeLabel.setText("SELECT  TYPE:");

        transactionTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "DEBIT", "CREDIT"}));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("DATE :");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("FOR TURN:");

        addButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(selectMemberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberList, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(selectCommitteeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(74, 74, 74)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(committeeList, 0, 219, Short.MAX_VALUE)
                            .addComponent(transactionTypeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(forTurnText)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectMemberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberList, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectCommitteeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(committeeList, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(transactionTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forTurnText, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void memberListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberListActionPerformed
        // TODO add your handling code here:
        if (memberList.equals(evt.getSource())) {
            if (TAG.trim().isEmpty() || TAG.trim().equals(Constants.QUICK_ADD)) {
                String selMem = (String) memberList.getSelectedItem();
                if (selMem != null && selMem.equals("None")) {
                    return;
                } else {
                    TAG = Constants.QUICK_ADD;
                }
            }
        }
    }//GEN-LAST:event_memberListActionPerformed

    private void committeeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_committeeListActionPerformed
        // TODO add your handling code here:
        if (committeeList.equals(evt.getSource())) {
            int cfid = -1;
            memberList.setEnabled(true);
            if (TAG.trim().isEmpty() || TAG.trim().equals(Constants.QUICK_ADD)) {
                String selCom = (String) committeeList.getSelectedItem();
                if (selCom.equals("None")) {
                    TAG = "";
                    memberList.setEnabled(false);
                    return;
                } else {
                    int pos = committeeList.getSelectedIndex();
                    if (pos == 0) {
                        memberList.setEnabled(false);
                        return;
                    }
                    if (cList != null && cList.get(pos - 1).getCname().contains(selCom)) {
                        cfid = cList.get(pos - 1).getCfid();
                        committee = cList.get(pos - 1);
                    } else {
                        for (CommitteeData cd : cList) {
                            if (cd.getCname().contains(selCom)) {
                                cfid = cd.getCfid();
                                committee = cd;
                                break;
                            }
                        }
                    }
                    memcomList = MemberInfoClass.getInstance().getDatasetForCfid(cfid);
                    memberList.removeAllItems();
                    memberList.addItem("None");
                    for (MemberInfoData m : memcomList) {
                        memberList.addItem(m.getMember().getUid() + "- " + m.getMember().getName());
                    }
                }
            } else if (TAG.equals(Constants.CONDUCT_COMMITTEE)) {
                String selCom = (String) committeeList.getSelectedItem();
                if (selCom.equals("None")) {
                    return;
                } else {
                    int pos = committeeList.getSelectedIndex();
                    if (pos == 0) {
                        return;
                    }
                    if (cList != null && cList.get(pos - 1).getCname().contains(selCom)) {
                        cfid = cList.get(pos - 1).getCfid();
                        committee = cList.get(pos - 1);
                    } else {
                        for (CommitteeData cd : cList) {
                            if (cd.getCname().contains(selCom)) {
                                cfid = cd.getCfid();
                                committee = cd;
                                break;
                            }
                        }
                    }
                    memcomList = MemberInfoClass.getInstance().getDatasetForCfid(cfid);
                }
            }
        }
    }//GEN-LAST:event_committeeListActionPerformed

    private void amountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountTextActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        if (resetButton.equals(evt.getSource())) {
            committeeList.setSelectedIndex(0);
            memberList.setSelectedIndex(0);
            transactionTypeCombo.setSelectedIndex(0);
            dateText.setText(getTodayDate());
            forTurnText.setText("0");
            amountText.setText("0.0");

        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private boolean validateEnteries() {
        String selCom = (String) committeeList.getSelectedItem();
        if (selCom == null || selCom.isEmpty() || selCom.equals("None")) {
            JOptionPane.showMessageDialog(null, "Select valid Committee");
            return false;
        }
        if (!TAG.equals(Constants.CONDUCT_COMMITTEE)) {
            String memsel = (String) memberList.getSelectedItem();
            if (memsel == null || memsel.isEmpty() || memsel.equals("None")) {
                JOptionPane.showMessageDialog(null, "Select valid Member");
                return false;
            }
        }
        String type = (String) transactionTypeCombo.getSelectedItem();
        if (type == null || type.isEmpty() || type.equals("None")) {
            JOptionPane.showMessageDialog(null, "Select valid Type");
            return false;
        }
        if (dateText == null || dateText.getText().isEmpty() || dateText.getText().length() < 9) {
            JOptionPane.showMessageDialog(null, "Select valid Date");
            return false;
        }
        if (amountText == null || amountText.getText().isEmpty() || checkAlphabetic(amountText.getText())) {
            JOptionPane.showMessageDialog(null, "Select valid Amount");
            return false;
        }
        if (forTurnText == null || forTurnText.getText().isEmpty() || checkAlphabetic(forTurnText.getText())) {
            JOptionPane.showMessageDialog(null, "Select a valid Turn");
            return false;
        }
        return true;

    }

    boolean checkAlphabetic(String input) {
        for (int i = 0; i != input.length(); ++i) {
            if (Character.isLetter(input.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        if (addButton.equals(evt.getSource())) {
            if (!validateEnteries()) {
                return;
            } else {
                Double val = Double.parseDouble(amountText.getText());
                int forTurn = Integer.parseInt(forTurnText.getText().trim());
                if (forTurn >= 20) {
                    JOptionPane.showMessageDialog(null, "Turn caanot be 20 or more");
                    return;
                }
                String dateStr = dateText.getText();
                String typeSel = (String) transactionTypeCombo.getSelectedItem();
                if (TAG.equals(Constants.INCOMMEM) || TAG.equals(Constants.INMEMCOM)) {
                    MemberInfoData meminf = new MemberInfoData(committee, member, given_heading, enteries);
                    if (typeSel.toLowerCase().contains("debit")) {
                        TransactionUtility.getInstance().addTransaction(meminf, val, forTurn, dateStr, Constants.DEBIT);
                        this.dispose();
                    } else if (typeSel.toLowerCase().contains("credit")) {
                        TransactionUtility.getInstance().addTransaction(meminf, val, forTurn, dateStr, Constants.CREDIT);
                        this.dispose();
                    }

                } else if (TAG.equals(Constants.COMMITTEE) || TAG.equals(Constants.QUICK_ADD)) {
                    int pos = memberList.getSelectedIndex();
                    MemberInfoData mif = null;
                    String entry = (String) memberList.getSelectedItem();
                    if (memcomList != null && entry.contains(memcomList.get(pos - 1).getMember().getUid() + "")
                            && entry.contains(memcomList.get(pos - 1).getMember().getName())) {
                        mif = memcomList.get(pos - 1);
                    } else {
                        for (MemberInfoData m : memcomList) {
                            if (entry.contains(m.getMember().getUid() + "")) {
                                mif = m;
                                break;
                            }
                        }
                    }
                    if (typeSel.toLowerCase().contains("debit")) {
                        TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.DEBIT);
                        this.dispose();
                    } else if (typeSel.toLowerCase().contains("credit")) {
                        TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.CREDIT);
                        this.dispose();
                    }
                } else if (TAG.equals(Constants.MEMBER)) {
                    int pos = committeeList.getSelectedIndex();
                    MemberInfoData mif = null;
                    String entry = (String) committeeList.getSelectedItem();
                    if (memcomList != null && entry.contains(memcomList.get(pos - 1).getCommittee().getCname())) {
                        mif = memcomList.get(pos - 1);
                    } else {
                        for (MemberInfoData m : memcomList) {
                            if (entry.contains(m.getCommittee().getCname())) {
                                mif = m;
                                break;
                            }
                        }
                    }
                    if (typeSel.toLowerCase().contains("debit")) {
                        TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.DEBIT);
                        this.dispose();
                    } else if (typeSel.toLowerCase().contains("credit")) {
                        TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.CREDIT);
                        this.dispose();
                    }
                } else if (TAG.equals(Constants.CONDUCT_COMMITTEE)) {
                    if (memcomList != null && memcomList.size() < 20) {
                        if (typeSel.toLowerCase().contains("debit")) {
                            for (MemberInfoData mif : memcomList) {
                                TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.DEBIT);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuickAddTransaction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            this.dispose();
                        } else if (typeSel.toLowerCase().contains("credit")) {
                            for (MemberInfoData mif : memcomList) {
                                TransactionUtility.getInstance().addTransaction(mif, val, forTurn, dateStr, Constants.CREDIT);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuickAddTransaction.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            this.dispose();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private String getTodayDate() {
        String today = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        today = sdf.format(new Date());
        return today;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuickAddTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuickAddTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuickAddTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuickAddTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuickAddTransaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField amountText;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox committeeList;
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField forTurnText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox memberList;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel selectCommitteeLabel;
    private javax.swing.JLabel selectMemberLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox transactionTypeCombo;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables

}
