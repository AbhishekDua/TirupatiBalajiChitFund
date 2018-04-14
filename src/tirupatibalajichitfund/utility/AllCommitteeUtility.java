/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tirupatibalajichitfund.db.AllCommitteeClass;
import tirupatibalajichitfund.db.CommitteeData;

/**
 *
 * @author abhishek
 */
public class AllCommitteeUtility {

    static AllCommitteeUtility allComUtil;

    public static AllCommitteeUtility getInstance() {
        if (allComUtil == null) {
            allComUtil = new AllCommitteeUtility();
        }
        return allComUtil;
    }

    public void updateCommittee(int cfid, String newstartdate, String newenddate, int amount, int turn) {
        try {
            //update db as per changes made
            final CommitteeData com = AllCommitteeClass.getInstance().getCommittee(cfid);
            com.setAmount(amount);
            com.setTurn(turn);
            com.setstartdate(newstartdate);
            com.setenddate(newenddate);
            new Thread() {
                @Override
                public void run() {
                    try {
                        AllCommitteeClass.getInstance().changeCommitteeTurn(com);
                        AllCommitteeClass.getInstance().changeCommitteeBeginDate(com);
                        AllCommitteeClass.getInstance().changeCommitteeEndDate(com);
                        AllCommitteeClass.getInstance().changeCommitteeAmount(com);
                        JOptionPane.showMessageDialog(null, "Updated");
                    } catch (Exception ex) {
                        Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }.start();
        } catch (Exception ex) {
            Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCommittee(final int cfid) {

        new Thread() {
            @Override
            public void run() {
                CommitteeData com;
                try {
                    com = AllCommitteeClass.getInstance().getCommittee(cfid);
                    AllCommitteeClass.getInstance().deleteCommittee(com);
                } catch (Exception ex) {
                    Logger.getLogger(AllCommitteeUtility.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();
    }

    public boolean addNewCommittee() {

        final String name = JOptionPane.showInputDialog("Please Enter name of the  New Committee\n"
                + "Note: You won't be able to change it later");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "Invalid Name ....Aborting Process");
            return false;
        }
        final String startdate = JOptionPane.showInputDialog("Please Enter Start Date (dd/mm/yyyy):");
        final String enddate = JOptionPane.showInputDialog("Please Enter End Date (dd/mm/yyyy):");
        final String amount = JOptionPane.showInputDialog("Please Enter Amount (dd/mm/yyyy):");
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to add committee" + name + " with amount-" + amount);
        if (result == JOptionPane.YES_OPTION) {
            new Thread() {
                @Override
                public void run() {
                    CommitteeData com = new CommitteeData();
                    com.setCname(name);
                    com.setstartdate(startdate);
                    com.setenddate(enddate);
                    if (amount != null) {
                        com.setAmount(Integer.parseInt(amount));
                    }
                    try {
                        AllCommitteeClass.getInstance().createNewCommittee(com);
                    } catch (Exception ex) {
                        Logger.getLogger(AllCommitteeUtility.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
            return true;
        }
        return false;
    }
}
