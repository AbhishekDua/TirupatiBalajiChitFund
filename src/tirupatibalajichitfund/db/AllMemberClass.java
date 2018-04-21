/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABHISHEK
 */
public class AllMemberClass {

    static Connection member_con;
    static Statement member_stmt;
    static ResultSet member_rs;
    public static AllMemberClass allmember;

    public AllMemberClass() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            member_con = DriverManager.getConnection("jdbc:mysql://localhost/tirupatichitfund", "root", "root");
            member_stmt = member_con.createStatement();
        } catch (Exception any) {
            System.out.println("MemberClassCalled");
            JOptionPane.showMessageDialog(null, "Cannot connect to member dataset");
        }
    }

    public static AllMemberClass getInstance() throws Exception {
        if (allmember == null) {
            allmember = new AllMemberClass();
            return allmember;
        } else {
            return allmember;
        }
    }

    public static ArrayList<MemberData> getAllMembersActive() {
        ArrayList<MemberData> members = new ArrayList<MemberData>();
        try {
            member_rs = member_stmt.executeQuery("select * from allmembers where StatusCode=0");
            while (member_rs.next()) {
                members.add(new MemberData(member_rs.getInt("UID"), member_rs.getString("KName"), member_rs.getString("Name"),
                        member_rs.getString("PhoneNumber"), member_rs.getDouble("Credit"), member_rs.getDouble("Debit")));

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "");
        }
        //JOptionPane.showMessageDialog(null, members.size());
        return members;
    }

    public static ArrayList<MemberData> getAllMembersInActive() {

        ArrayList<MemberData> members = new ArrayList<MemberData>();
        try {
            member_rs = member_stmt.executeQuery("select * from allmembers where StatusCode=1");
            while (member_rs.next()) {
                members.add(new MemberData(member_rs.getInt("UID"), member_rs.getString("KName"), member_rs.getString("Name"),
                        member_rs.getString("PhoneNumber"), member_rs.getDouble("Credit"), member_rs.getDouble("Debit")));

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "");
        }
        //JOptionPane.showMessageDialog(null, members.size());
        return members;
    }

    public boolean changeMemberName(MemberData member) {

        try {
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET Name = '" + member.getName() + "' "
                    + "WHERE allmembers.UID = '" + member.getUid() + "'");
            JOptionPane.showMessageDialog(null, "Name of member changed to  " + member.getName());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public boolean changePhoneNumber(MemberData member) {

        try {
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET PhoneNumber = '" + member.getPhone() + "' "
                    + "WHERE allmembers.UID = '" + member.getUid() + "'");
            JOptionPane.showMessageDialog(null, "Number of member changed to  " + member.getPhone());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public boolean changeMemberCredit(int member_uuid,double credits) {

        try {
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET Credit = '" + credits + "' "
                    + "WHERE allmembers.UID = '" + member_uuid + "'");
            JOptionPane.showMessageDialog(null, "Credit of member changed to  " +credits);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public boolean calculateMemberCredit(MemberData member, double credit) {
        try {
            //member_rs=member_stmt.executeQuery("select * from tirupatichitfund.allmembers where allmembers.UID="+uid);
            double newcredit = member.getCredit() - credit;
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET Credit = '" + newcredit + "'" + "WHERE allmembers.UID='" + member.getUid() + "'");
            JOptionPane.showMessageDialog(null, "Credit of member changed to " + newcredit);
            return true;

        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public boolean changeMemberDebit(int member_uuid, double member_debit) {

        try {
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET Debit = '" + member_debit + "' "
                    + "WHERE allmembers.UID = '" + member_uuid + "'");
            JOptionPane.showMessageDialog(null, "Debit of member changed to  " + member_debit);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public boolean calculateMemberDebit(MemberData member, double debit) {
        try {
            //member_rs=member_stmt.executeQuery("select * from tirupatichitfund.allmembers where allmembers.UID="+uid);
            double newdebit = member.getDebit() - debit;
            member_stmt.executeUpdate("UPDATE tirupatichitfund.allmembers SET Debit = '" + newdebit + "'" + "WHERE allmembers.UID='" + member.getUid() + "'");
            JOptionPane.showMessageDialog(null, "Debit of member changed to " + newdebit);
            return true;

        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }
    }

    public int getAllMembersCount() {
        try {
            member_rs = member_stmt.executeQuery("select COUNT(*) from tirupatichitfund.allmembers");
            member_rs.next();
            return member_rs.getInt(0);
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public boolean createNewMember(MemberData member) {
        try {
            member_stmt.executeUpdate("insert into tirupatichitfund.allmembers(KName,Name,PhoneNumber,Credit,Debit)"
                    + " values('" + member.getKName() + "','" + member.getName() + "','" + member.getPhone() + "','" + member.getCredit() + "','" + member.getDebit() + "')");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Occurred");
            return false;
        }
    }

    public boolean deleteMember(MemberData member) {
        try {
            member_stmt.executeUpdate("update tirupatichitfund.allmembers  SET StatusCode=1 where allmembers.UID='" + member.getUid() + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Occurred");
            return false;
        }
    }

    public MemberData getMember(int uid) throws SQLException {
        MemberData member = null;
        member_stmt.executeQuery("use tirupatichitfund");
        member_rs = member_stmt.executeQuery("select * from tirupatichitfund.allmembers where allmembers.UID=" + uid);
        while (member_rs.next()) {
            member = new MemberData(member_rs.getInt("UID"), member_rs.getString("KName"), member_rs.getString("Name"), member_rs.getString("PhoneNumber"), member_rs.getDouble("Credit"), member_rs.getDouble("Debit"));
        }
        if (member == null) {
            JOptionPane.showMessageDialog(null, "No member Found");
        }
        return member;
    }

}
