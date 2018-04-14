/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABHISHEK
 */
public class AllCommitteeClass {

    static Connection committee_con;
    static Statement committee_stmt;
    static ResultSet committee_rs;
    public static AllCommitteeClass allcommittee;

    public AllCommitteeClass() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            committee_con = DriverManager.getConnection("jdbc:mysql://localhost/tirupatichitfund", "root", "root");
            committee_stmt = committee_con.createStatement();
            committee_stmt.executeQuery("use tirupatichitfund");
        } catch (ClassNotFoundException | SQLException any) {
            System.out.println("" + any);
            JOptionPane.showMessageDialog(null, "Cannot connect to committee dataset");
        }
    }

    public static AllCommitteeClass getInstance() throws Exception {
        if (allcommittee == null) {
            allcommittee = new AllCommitteeClass();
        }
        return allcommittee;
    }

    public ArrayList<CommitteeData> getAllCommitteesActive() {
        ArrayList<CommitteeData> committees = new ArrayList<>();
        try {

            committee_rs = committee_stmt.executeQuery("select * from committelist where StatusCode=0");
            while (committee_rs.next()) {
                committees.add(new CommitteeData(committee_rs.getInt("CFID"), committee_rs.getString("Cname"),
                        committee_rs.getString("BeginDate"), committee_rs.getString("EndDate"), committee_rs.getInt("Amount"), committee_rs.getInt("Turn")));
            }
        } catch (Exception ret) {
            JOptionPane.showMessageDialog(null, ret + "\nUnable to retrieve data");
        }
        if (committees == null) {
            JOptionPane.showMessageDialog(null, "Unable to retrieve data");
        }
        return committees;
    }

    public ArrayList<CommitteeData> getAllCommitteesCompleted() {
        ArrayList<CommitteeData> committees = new ArrayList<>();
        try {

            committee_rs = committee_stmt.executeQuery("select * from committelist where StatusCode=2");
            while (committee_rs.next()) {
                committees.add(new CommitteeData(committee_rs.getInt("CFID"), committee_rs.getString("Cname"),
                        committee_rs.getString("BeginDate"), committee_rs.getString("EndDate"), committee_rs.getInt("Amount"), committee_rs.getInt("Turn")));
            }
        } catch (Exception ret) {
            JOptionPane.showMessageDialog(null, ret + "\nUnable to retrieve data");
        }
        if (committees == null) {
            JOptionPane.showMessageDialog(null, "Unable to retrieve data");
        }
        return committees;
    }

    public ArrayList<CommitteeData> getAllCommitteesDeleted() {
        ArrayList<CommitteeData> committees = new ArrayList<>();
        try {

            committee_rs = committee_stmt.executeQuery("select * from committelist where StatusCode=1");
            while (committee_rs.next()) {
                committees.add(new CommitteeData(committee_rs.getInt("CFID"), committee_rs.getString("Cname"),
                        committee_rs.getString("BeginDate"), committee_rs.getString("EndDate"), committee_rs.getInt("Amount"), committee_rs.getInt("Turn")));
            }
        } catch (Exception ret) {
            JOptionPane.showMessageDialog(null, ret + "\nUnable to retrieve data");
        }
        if (committees == null) {
            JOptionPane.showMessageDialog(null, "Unable to retrieve data");
        }
        return committees;
    }
//    public boolean changeCommitteeName(CommitteeData committee)
//    {
//          try {
//              committee_stmt.executeUpdate("UPDATE tirupatichitfund.committelist SET CName = '"+committee.getCname()+"' "
//                      + "WHERE committelist.CFID = '"+committee.getCfid()+"'");
//              JOptionPane.showMessageDialog(null,"Name of committee changed to  "+committee.getCname());
//              return true;
//          } catch (Exception ex) {
//              Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
//              JOptionPane.showMessageDialog(null,"Some Error Occurred ");
//              return false;
//          }
//          
//    }

    public boolean changeCommitteeBeginDate(CommitteeData committee) {
        try {
            committee_stmt.executeUpdate("UPDATE tirupatichitfund.committelist SET BeginDate = '" + committee.getstartdate() + "' "
                    + "WHERE committelist.CFID = " + committee.getCfid());
            JOptionPane.showMessageDialog(null, "Start Date of committee changed to  " + committee.getenddate());
            return true;
        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }

    }

    public boolean changeCommitteeEndDate(CommitteeData committee) {
        try {
            committee_stmt.executeUpdate("UPDATE tirupatichitfund.committelist SET EndDate = '" + committee.getenddate() + "' "
                    + "WHERE committelist.CFID = " + committee.getCfid());
            JOptionPane.showMessageDialog(null, "End Date of committee changed to  " + committee.getenddate());
            return true;
        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }

    }

    public boolean changeCommitteeAmount(CommitteeData committee) {
        try {
            committee_stmt.executeUpdate("UPDATE tirupatichitfund.committelist SET Amount = '" + committee.getAmount() + "' "
                    + "WHERE committelist.CFID =" + committee.getCfid());
            JOptionPane.showMessageDialog(null, "Amount of committee changed to  " + committee.getAmount());
            return true;
        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }

    }

    public boolean changeCommitteeTurn(CommitteeData committee) {
        try {

            committee_stmt.executeUpdate("UPDATE tirupatichitfund.committelist SET Turn = '" + committee.getTurn() + "' "
                    + "WHERE committelist.CFID =" + committee.getCfid());
            JOptionPane.showMessageDialog(null, "Turn of committee changed to  " + committee.getTurn());
            return true;
        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred ");
            return false;
        }

    }

    public boolean createNewCommittee(CommitteeData committee) {
        try {
            committee_stmt.executeUpdate("insert into tirupatichitfund.committelist(CName,BeginDate,EndDate,Amount,Turn)"
                    + " values('" + committee.getCname() + "','" + committee.getstartdate() + "','" + committee.getenddate() + "','" + committee.getAmount() + "','" + committee.getTurn() + "')");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Occurred");
            return false;
        }
    }

    public boolean deleteCommittee(CommitteeData committee) {
        try {
            committee_stmt.executeUpdate("update tirupatichitfund.committelist SET StatusCode=1 where committelist.CFID='" + committee.getCfid() + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Occurred");
            return false;
        }
    }

    public CommitteeData getCommittee(int cfid) throws SQLException {
        CommitteeData committee = null;
        committee_stmt.executeQuery("use tirupatichitfund");
        committee_rs = committee_stmt.executeQuery("select * from tirupatichitfund.committelist where CFID=" + cfid);
        while (committee_rs.next()) {
            committee = new CommitteeData(committee_rs.getInt("CFID"), committee_rs.getString("CName"), committee_rs.getString("BeginDate"), committee_rs.getString("EndDate"), committee_rs.getInt("Amount"), committee_rs.getInt("Turn"));
        }
        if (committee == null) {
            JOptionPane.showMessageDialog(null, "Committee Not Found");
        }
        return committee;
    }
}
