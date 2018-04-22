/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tirupatibalajichitfund.db.AllMemberClass;
import tirupatibalajichitfund.db.MemberInfoClass;
import tirupatibalajichitfund.db.MemberInfoData;
import tirupatibalajichitfund.db.MemberData;
import tirupatibalajichitfund.db.TransactionData;
import tirupatibalajichitfund.db.TransactionTableClass;
import static tirupatibalajichitfund.utility.Constants.CREDIT;
import static tirupatibalajichitfund.utility.Constants.DEBIT;

/**
 *
 * @author abhishek
 */
public class TransactionUtility {

    static TransactionUtility transactionUtility;
    private boolean global_transaction_flag = false;

    public static TransactionUtility getInstance() {
        if (transactionUtility == null) {
            transactionUtility = new TransactionUtility();
        }
        return transactionUtility;
    }

    public boolean addTransaction(final MemberInfoData memberInfo, double amount, int forTurn, String date, String tag) {
        final TransactionData data = new TransactionData();
        data.setReferenceKey(memberInfo.getReferenceKey());
        data.setCFID(memberInfo.getCommittee().getCfid());
        data.setCName(memberInfo.getCommittee().getCname());
        data.setForTurn(forTurn);
        data.setEnteries(memberInfo.getEnteries());
        data.setUID(memberInfo.getMember().getUid());
        data.setName(memberInfo.getMember().getName());
        if (date != null && !date.trim().isEmpty()) {
            data.setDate(date);
        } else {
            data.setDate(getCurrentDate());
        }
        data.setTransactionType(0);
        switch (tag) {
            case CREDIT:
                data.setDebit(0.0);
                data.setCredit(amount);
                break;
            case DEBIT:
                data.setDebit(amount);
                data.setCredit(0.0);
                break;
        }
        new Thread() {
            @Override
            public void run() {
                if (TransactionTableClass.getInstance().addNewTransaction(data)) {
                    handleTransactionTableChangesForTransaction(memberInfo.getCommittee().getCfid(), memberInfo.getMember().getUid(), "ADD");

                } else {
                    showUserMessage("Transaction Failed");
                }
            }
        }.start();
        return global_transaction_flag;
    }

    public void showUserMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String getCurrentDate() {
        String currentDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = sdf.format(new Date());
        return currentDate;
    }

    public boolean deleteTransaction(final int tid) {
        //boolean res=false;
        new Thread() {
            @Override
            public void run() {
                try {
                    if (TransactionTableClass.getInstance().deleteTransactionForTid(tid)) {

                        TransactionData data = TransactionTableClass.getInstance().getTransactionForTransactionId(tid);
                        String refkey = data.getReferenceKey();   //for Member Info Table
                        int uid = data.getUID();   //for All Member Table
                        int cfid = data.getCFID();
                        handleTransactionTableChangesForTransaction(cfid, uid, "DELETE");

                        //                        MemberData md=AllMemberClass.getInstance().getMember(uid);
                        //                        double credit=data.getCredit();
                        //                        double debit=data.getDebit();
                        //System.out.println("Credit: "+credit+" Debit: "+debit);
                        //System.out.println("Uid is "+uid+" Refkey--"+refkey);
                        //                        MemberInfoData mid=MemberInfoClass.getInstance().getDatasetForReferenceKey(refkey);
                        //                        if(mid!=null && credit!=0.0) {
                        //                            //Calculate new credit and update in memberinfo and allmember table
                        //                            if(MemberInfoClass.getInstance().calculateMemberInfoCredit(mid, credit))
                        //                            {
                        //                                //System.out.println("Refkey of meminfoclass is "+mid.getReferenceKey());
                        //                                res=AllMemberClass.getInstance().calculateMemberCredit(md, credit);
                        //                                res=true;
                        //                                
                        //                                
                        //                            }
                        //                            
                        //                            
                        //                            
                        //                        }  else if(mid!=null && debit!=0.0) {
                        //                       
                        //                            //Calculate new debit and update in memberinfo and allmember table
                        //                            //System.out.println("Refkey of meminfoclass debit is "+mid.getReferenceKey());
                        //                            if(MemberInfoClass.getInstance().calculateMemberInfoDebit(mid, debit))
                        //                            {
                        //                                res=AllMemberClass.getInstance().calculateMemberDebit(md, debit);
                        //                                res=true;
                        //                            }
                        //                           
                        //                        } else {
                        //                        
                        //                            res=false;
                        //                        }
                        //                            
                        //                    } else {
                        //                        res=false;
                        //                        JOptionPane.showMessageDialog(null,"Some error occurred while deleting");
                        //                    }
                        //                    if(res==true)
                        //                    {
                        //                        JOptionPane.showMessageDialog(null,"Transaction deleted successfully!");
                        //                    }
                        //                    else
                        //                    {
                        //                        JOptionPane.showMessageDialog(null, "Transaction not deleted properly!");
                        //                    }
                    } else {
                        showUserMessage("Transaction not deleted properly!");

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable to delete transaction");
                    Logger.getLogger(TransactionUtility.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

        }.start();
        return global_transaction_flag;
    }

    public boolean handleTransactionTableChangesForTransaction(final int cfid, final int uid, final String action) {
        new Thread() {
            @Override
            public void run() {
                try {
                    double netDebit = TransactionTableClass.getInstance().getSumDebitTransactionForAMemberInCommittee(uid, cfid);
                    double netCredit = TransactionTableClass.getInstance().getSumCreditTransactionForAMemberInCommittee(uid, cfid);
                    double totalMemberDebit = TransactionTableClass.getInstance().getSumTotalDebitTransactionForAMember(uid);
                    double totalMemberCredit = TransactionTableClass.getInstance().getSumTotalCreditTransactionForAMember(uid);
                    if (AllMemberClass.getInstance().changeMemberDebit(uid, totalMemberDebit) && AllMemberClass.getInstance().changeMemberCredit(uid, totalMemberCredit)) {
                        if (!global_transaction_flag) {
                            global_transaction_flag = true;
                        }
                    } else {
                        global_transaction_flag = false;
                    }

                    if (MemberInfoClass.getInstance().changeMemberInfoDebitWithoutRefKey(cfid, uid, netDebit) && MemberInfoClass.getInstance().changeMemberInfoCreditWithoutRefKey(cfid, uid, netCredit)) {
                        if (!global_transaction_flag) {
                            if (action.equals("ADD")) {
                                showUserMessage("Added Successfully");
                            }
                            if (action.equals("DELETE")) {
                                showUserMessage("Deleted Successfully");
                            }
                            global_transaction_flag = true;
                        }
                    } else {
                        global_transaction_flag = false;
                    }

                    showUserMessage("Values --netdebit " + netDebit + "  Net credit--" + netCredit + " Total memeber Debit,Credit-" + totalMemberDebit + " , " + totalMemberCredit);

                } catch (Exception ex) {
                    Logger.getLogger(TransactionUtility.class.getName()).log(Level.SEVERE, null, ex);
                    showUserMessage("Failed");
                }
            }
        }.start();
        System.out.println("global_transaction_flag--" + global_transaction_flag);
        return global_transaction_flag;
    }

}
