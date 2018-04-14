/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tirupatibalajichitfund.utility;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tirupatibalajichitfund.db.AllMemberClass;
import tirupatibalajichitfund.db.MemberData;
import tirupatibalajichitfund.db.MemberInfoClass;
import tirupatibalajichitfund.db.TransactionData;
import tirupatibalajichitfund.db.TransactionTableClass;

/**
 *
 * @author abhishek
 */
public class AllMemberUtility {
    static  AllMemberUtility allMemberUtil;
   
   public static AllMemberUtility getInstance(){
   if(allMemberUtil==null){
   allMemberUtil=new AllMemberUtility();
   }
   return allMemberUtil;
   }
    
    public void deleteMember(final int uid){
        new Thread(){
               @Override
             public void run(){
       try {
         if(TransactionTableClass.getInstance().deleteTransactionsForUid(uid)){
             if(MemberInfoClass.getInstance().deleteDatasetForUid(uid)){
                MemberData mem= AllMemberClass.getInstance().getMember(uid);
                AllMemberClass.getInstance().deleteMember(mem);
             }
         }
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Unable to delete member");
           Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
      }.start();
    }
    public void updateMember(int uid,String newname,String phone){
       try {
          final  MemberData mem= AllMemberClass.getInstance().getMember(uid);
           mem.setName(newname);
           mem.setPhone(phone);
           new Thread(){
               @Override
             public void run(){
                   try {
                       AllMemberClass.getInstance().changeMemberName(mem);
                        AllMemberClass.getInstance().changePhoneNumber(mem);
                        JOptionPane.showMessageDialog(null,"Updated");
                   } catch (Exception ex) {
                       Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
                   }
          
           }
           }.start();
       } catch (Exception ex) {
           Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public boolean addNewMemeber(){
    final String name=JOptionPane.showInputDialog("Please Enter name of the  New Member");
    if(name==null){
    JOptionPane.showMessageDialog(null,"Invalid Name ....Aborting Process");
    return false;
    }
    final String phone=JOptionPane.showInputDialog("Please Enter Phone Number of the  New Member");
    
    int result=JOptionPane.showConfirmDialog(null,"Are you sure you want to add "+name+" with phone number"+phone);
    if(result==JOptionPane.YES_OPTION){
    new Thread(){
    @Override
    public void run(){
        MemberData mem=new MemberData();
        mem.setkName(name);
        mem.setName(name);
        mem.setPhone(phone);
        try {
            AllMemberClass.getInstance().createNewMember(mem);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Unable to Add Name");
            Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }.start();
    return true;
    }
    return false;
    }
}
