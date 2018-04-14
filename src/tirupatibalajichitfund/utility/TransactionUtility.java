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

    public static TransactionUtility getInstance() {
        if (transactionUtility == null) {
            transactionUtility = new TransactionUtility();
        }
        return transactionUtility;
    }

    public void addTransaction(MemberInfoData memberInfo, double amount, int forTurn, String date, String tag) {
        TransactionData data = new TransactionData();
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
        TransactionTableClass.getInstance().addNewTransaction(data);
    }

    public String getCurrentDate() {
        String currentDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = sdf.format(new Date());
        return currentDate;
    }
    public void deleteTransaction(final int tid)
    {
        //boolean res=false;
        new Thread()
        {
            @Override
            public void run()
            {
                boolean res=false;
                try
                {
                    if(TransactionTableClass.getInstance().deleteTransactionForTid(tid)) {
                       
                        TransactionData data=TransactionTableClass.getInstance().getTransactionForTransactionId(tid);
                        String refkey=data.getReferenceKey();   //for Member Info Table
                        int uid=data.getUID();   //for All Member Table
                        MemberData md=AllMemberClass.getInstance().getMember(uid);
                        double credit=data.getCredit();
                        double debit=data.getDebit();
                        //System.out.println("Credit: "+credit+" Debit: "+debit);
                        //System.out.println("Uid is "+uid+" Refkey--"+refkey);
                        MemberInfoData mid=MemberInfoClass.getInstance().getDatasetForReferenceKey(refkey);
                        if(mid!=null && credit!=0.0) {
                            //Calculate new credit and update in memberinfo and allmember table
                            if(MemberInfoClass.getInstance().calculateMemberInfoCredit(mid, credit))
                            {
                                //System.out.println("Refkey of meminfoclass is "+mid.getReferenceKey());
                                res=AllMemberClass.getInstance().calculateMemberCredit(md, credit);
                                res=true;
                                
                                
                            }
                            
                            
                            
                        }  else if(mid!=null && debit!=0.0) {
                       
                            //Calculate new debit and update in memberinfo and allmember table
                            //System.out.println("Refkey of meminfoclass debit is "+mid.getReferenceKey());
                            if(MemberInfoClass.getInstance().calculateMemberInfoDebit(mid, debit))
                            {
                                res=AllMemberClass.getInstance().calculateMemberDebit(md, debit);
                                res=true;
                            }
                           
                        } else {
                        
                            res=false;
                        }
                            
                    } else {
                        res=false;
                        JOptionPane.showMessageDialog(null,"Some error occurred while deleting");
                    }
                    if(res==true)
                    {
                        JOptionPane.showMessageDialog(null,"Transaction deleted successfully!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Transaction not deleted properly!");
                    }
                }
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Unable to delete transaction");
                    Logger.getLogger(TransactionUtility.class.getName()).log(Level.SEVERE, null, ex);
                
                }
            }
            
        }.start();
        
    }

}
