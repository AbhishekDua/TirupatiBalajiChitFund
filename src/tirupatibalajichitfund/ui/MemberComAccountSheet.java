/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tirupatibalajichitfund.db.TransactionData;
import tirupatibalajichitfund.db.MemberInfoData;
import tirupatibalajichitfund.db.MemberData;
import tirupatibalajichitfund.db.TransactionTableClass;
import tirupatibalajichitfund.utility.Constants;
import tirupatibalajichitfund.utility.TransactionUtility;

/**
 *
 * @author abhishek
 */
public class MemberComAccountSheet extends javax.swing.JPanel {

    DefaultTableModel dtm;
    /**
     * Creates new form MemberComAccountSheet
     */
    public static TransactionTableClass transactiontable;
    String name;           //Member Name
    int tid;               //Transaction ID
    int row;
    int cfid = -10, uid = -10;
    String TAG = "";
    String given_heading = "";
    String refid;

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }

    private int getSingleClickCfid() {
        return cfid;
    }

    private void setCfid(int cfid) {
        this.cfid = cfid;
    }

    private int getSingleClickUid() {
        return uid;
    }

    private void setUid(int uid) {
        this.uid = uid;
    }
    ArrayList<TransactionData> dataset_old;

    public MemberComAccountSheet(ArrayList<TransactionData> dataset, String heading, String tag) {
        initComponents();
        cancelButton.setEnabled(false);
        deleteButton.setEnabled(false);
        given_heading = heading;
        TAG = tag;
        transactiontable = TransactionTableClass.getInstance();
        dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        allTransactionTable.setModel(dtm);
        allTransactionTable.getTableHeader().setFont(new Font("Ubuntu", Font.PLAIN, 16));
        allTransactionTable.getAutoscrolls();
        allTransactionTable.setRowHeight(40);
        allTransactionTable.setFont(new Font("Serif", Font.PLAIN, 16));
        dataset_old = dataset;
        setupTable(dataset);
//        dataset2=new ArrayList(dataset);
    }

    private void setupTable(ArrayList<TransactionData> dataset) {
        int count = 0;
        dtm.addColumn("Sno");
        dtm.addColumn("Trans. ID");
        dtm.addColumn("Mem. ID");
        dtm.addColumn("Mem. Name");
        dtm.addColumn("Com. ID");
        dtm.addColumn("Com.Name");
        dtm.addColumn("Enteries");
        dtm.addColumn("For Turn");
        dtm.addColumn("Credit");
        dtm.addColumn("Debit");
        dtm.addColumn("Date");
        for (TransactionData data : dataset) {
            if (data.getTransactionType() == 0) {
                count++;
                dtm.addRow(new Object[]{count, data.getTransactionID(), data.getUID(), data.getName(), data.getCFID(), data.getCName(),
                    data.getEnteries(), data.getForTurn(), data.getCredit(), data.getDebit(),
                    data.getDate()});
            }

        }

    }

    private void setSingleClicktid(int tid) {
        this.tid = tid;
    }

    private int getSingleClicktid() {
        return this.tid;
    }

    private String getSingleClickname() {
        return this.name;
    }

    private void setSingleClickname(String name) {
        this.name = name;
    }

    private void setSingleClickrow(int row) {
        this.row = row;
    }

    private int getSingleClickrow() {
        return this.row;
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
        allTransactionTable = new javax.swing.JTable();
        addDebitButton = new javax.swing.JButton();
        addCreditButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();

        setPreferredSize(new java.awt.Dimension(1600, 800));

        allTransactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sno", "Trans.ID", "Mem. ID", "Mem.  Name", "Com.  ID", "Com. Name", "Enteries", "For Turn", "Credit", "Debit", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allTransactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                allTransactionTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(allTransactionTable);

        addDebitButton.setText("ADD DEBIT");

        addCreditButton.setText("ADD CREDIT");
        addCreditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCreditButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addDebitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCreditButton, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinner1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addDebitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCreditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 320, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void addCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCreditButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addCreditButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if (deleteButton.equals(evt.getSource())) {
            /*if (getSingleClickrow() < 0 || getSingleClickname() == null || getSingleClicktid() < 10000000)
            {
                JOptionPane.showMessageDialog(this,"Please select a transaction to delete: ");
                return;
            }*/
            int dialogres = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this transaction?");
            if (dialogres == JOptionPane.YES_OPTION) {
                try {
                    TransactionUtility.getInstance().deleteTransaction(getSingleClicktid());
                    
                    refresh();
                } /*catch(SQLException ex)
                {
                    Logger.getLogger(MemberComAccountSheet.class.getName()).log(Level.SEVERE, null, ex);                
                }*/ catch (Exception ex) {
                    Logger.getLogger(MemberComAccountSheet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                return;
            }
        }

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        if (refreshButton.equals(evt.getSource())) {
            try {
                this.refresh();
            } catch (Exception ex) {
                Logger.getLogger(MemberComAccountSheet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void allTransactionTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allTransactionTableMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int row = allTransactionTable.getSelectedRow();
            int column = allTransactionTable.getSelectedColumn();
            int tid = (Integer) allTransactionTable.getValueAt(row, 1);
            setUid((Integer) allTransactionTable.getValueAt(row, 2));
            String name = (String) allTransactionTable.getValueAt(row, 3);
            setCfid((Integer) allTransactionTable.getValueAt(row, 4));
            setSingleClickrow(row);
            setSingleClicktid(tid);
            if (dataset_old != null && !dataset_old.isEmpty()) {
                setRefid(dataset_old.get(row).getReferenceKey());
            }
            //System.out.println("TID-"+tid+"\nName-"+name);
            setSingleClickname(name);
            deleteButton.setEnabled(true);
            cancelButton.setEnabled(true);
            addCreditButton.setEnabled(false);
            addDebitButton.setEnabled(false);

        }

    }//GEN-LAST:event_allTransactionTableMousePressed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        deleteButton.setEnabled(false);
        addCreditButton.setEnabled(true);
        addDebitButton.setEnabled(true);
        refreshButton.setEnabled(true);
        allTransactionTable.clearSelection();
        cancelButton.setEnabled(false);
    }//GEN-LAST:event_cancelButtonActionPerformed
    public void refresh() throws Exception {
        if (TAG != null) {
            ArrayList<TransactionData> dataset2;
            switch (TAG) {
                case Constants.MEMBER:
                    if (getSingleClickUid() == -10) {
                        dataset2 = dataset_old;
                        break;
                    }
                    dataset2 = TransactionTableClass.getInstance().getAllTransactionsForUID(getSingleClickUid());
                    break;
                case Constants.COMMITTEE:
                    if (getSingleClickCfid() == -10) {
                        dataset2 = dataset_old;
                        break;
                    }
                    dataset2 = TransactionTableClass.getInstance().getAllTransactionsForCFID(getSingleClickCfid());
                    break;
                case Constants.INMEMCOM:
                case Constants.INCOMMEM:
                    if (getRefid() == null) {
                        dataset2 = dataset_old;
                        break;
                    }
                    dataset2 = TransactionTableClass.getInstance().getAllTransactionsForRefID(getRefid());
                    break;
                default:
                    dataset2 = dataset_old;
                    break;

            }
            MainFrameChitFund.getInstance().setupTransactionTable(dataset2, given_heading, TAG);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCreditButton;
    private javax.swing.JButton addDebitButton;
    private javax.swing.JTable allTransactionTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
