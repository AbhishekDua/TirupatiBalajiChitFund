/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tirupatibalajichitfund.db.CommitteeData;
import tirupatibalajichitfund.db.MemberData;
import tirupatibalajichitfund.db.MemberInfoClass;
import tirupatibalajichitfund.db.MemberInfoData;

/**
 *
 * @author abhishek
 */
public class MemberInfoUtility {

    static MemberInfoUtility memberInfoUtility;

    public static MemberInfoUtility getInstance() {
        if (memberInfoUtility == null) {
            memberInfoUtility = new MemberInfoUtility();
        }
        return memberInfoUtility;
    }

    public void addNewEntry(MemberData mem, CommitteeData com, int enteries, String referenceKey) {

        String refkey = referenceKey;
        if (refkey == null) {
            return;
        }
        final MemberInfoData memberinfodata = new MemberInfoData(com, mem, refkey, enteries);

        new Thread() {
            @Override
            public void run() {
                try {
                    if (MemberInfoClass.getInstance().createNewMemberToCommittee(memberinfodata)) {
                        JOptionPane.showMessageDialog(null, "Added member to committee");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable to Add Member to Commitee at this time");
                    Logger.getLogger(AllMemberUtility.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();

    }

    public String formRefKey(MemberData mem, CommitteeData com, int enteries) {
        String key = null;
        key = mem.getUid() + "_" + mem.getKName().trim() + "_" + com.getCfid() + "_" + com.getCname() + "_" + enteries;
        if (key == null) {
            System.out.println("Unable to form key");
        }
        return key;

    }
}
