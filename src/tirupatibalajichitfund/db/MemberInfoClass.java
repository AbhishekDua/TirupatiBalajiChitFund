/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author abhishek
 */
public class MemberInfoClass {

    private Connection con1, con;
    private Statement stmt, stmt1;
    private ResultSet rs, rs1;
    static MemberInfoClass meminfocls;

    public MemberInfoClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/tirupatichitfund", "root", "root");
            stmt = con.createStatement();
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/tirupatichitfund", "root", "root");
            stmt1 = con1.createStatement();
            stmt.executeQuery("use tirupatichitfund");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static MemberInfoClass getInstance() {
        if (meminfocls == null) {
            meminfocls = new MemberInfoClass();
        }
        return meminfocls;
    }

    public ArrayList<MemberInfoData> getActiveDataset() {
        ArrayList<MemberInfoData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from memberinfo where StatusCode=0 ORDER BY Sno ASC ");
            while (rs.next()) {
                MemberData member = new MemberData();
                CommitteeData committee = new CommitteeData();
                member.setkName(rs.getString("KName"));
                member.setName(rs.getString("Name"));
                member.setUid(rs.getInt("UID"));
                member.setCredit(rs.getDouble("Credit"));
                member.setDebit(rs.getDouble("Debit"));
                committee.setCfid(rs.getInt("CFID"));
                committee.setCname(rs.getString("CName"));
                committee.setstartdate(rs.getString("StartDate"));
                committee.setenddate(rs.getString("EndDate"));
                committee.setTurn(rs.getInt("Turn"));
                MemberInfoData mid = new MemberInfoData(committee, member, rs.getString("ReferenceKey"), rs.getInt("Enteries"));
                dataset.add(mid);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to fetch data");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;

    }

    public boolean changeMemberInfoName(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set Name='" + memberinfo.getMember().getName() + "' where StatusCode=0"
                    + "AND uid=" + memberinfo.getMember().getUid());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing name in memberinfo ");
            return false;
        }
    }

    public boolean changeMemberInfoTurn(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set Turn='" + memberinfo.getCommittee().getTurn() + "' where"
                    + "StatusCode=0 AND CFID=" + memberinfo.getCommittee().getCfid());
            return true;

        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing turn in memberinfo ");
            return false;
        }

    }

    public boolean changeMemberInfoStartDate(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set StartDate='" + memberinfo.getCommittee().getstartdate() + "' where"
                    + "StatusCode=0 AND CFID=" + memberinfo.getCommittee().getCfid());
            return true;

        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing start date in memberinfo ");
            return false;
        }

    }

    public boolean changeMemberInfoEndDate(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set EndDate='" + memberinfo.getCommittee().getenddate() + "' where"
                    + "StatusCode=0 AND CFID=" + memberinfo.getCommittee().getCfid());
            return true;

        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing end date in memberinfo ");
            return false;
        }

    }

    public boolean calculateMemberInfoCredit(MemberInfoData memberinfo, double credit) {
        try {
            //rs=stmt.executeQuery("Select * from memberinfo where ReferenceKey='"+refkey+"'");
            double newcredit = memberinfo.getMember().getCredit() - credit;
            stmt.executeUpdate("update memberinfo set Credit='" + newcredit + "' where ReferenceKey='" + memberinfo.getReferenceKey() + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing credit in memberinfo");
            return false;

        }
    }

    public boolean changeMemberInfoCredit(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set Credit='" + memberinfo.getMember().getCredit() + "' where "
                    + "StatusCode=0 AND ReferenceKey=" + memberinfo.getReferenceKey());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing credit in memberinfo");
            return false;
        }

    }

    public boolean changeMemberInfoCreditWithoutRefKey(int cfid, int uid, double credit) {
        try {
            String sql = "update memberinfo set Credit='" + credit + "' where "
                    + "StatusCode=0 AND CFID=" + cfid + " AND UID=" + uid;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing credit in memberinfo");
            return false;
        }

    }

    public boolean calculateMemberInfoDebit(MemberInfoData memberinfo, double debit) {
        try {
            //rs=stmt.executeQuery("Select * from memberinfo where ReferenceKey='"+refkey+"'");
            double newdebit = memberinfo.getMember().getDebit() - debit;
            stmt.executeUpdate("update memberinfo set Debit='" + newdebit + "' where ReferenceKey='" + memberinfo.getReferenceKey() + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing debit in memberinfo");
            return false;

        }

    }

    public boolean changeMemberInfoDebit(MemberInfoData memberinfo) {
        try {
            stmt.executeUpdate("update memberinfo set Debit='" + memberinfo.getMember().getDebit() + "' where "
                    + "StatusCode=0 AND ReferenceKey=" + memberinfo.getReferenceKey());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing debitt in memberinfo");
            return false;
        }
    }

    public boolean changeMemberInfoDebitWithoutRefKey(int cfid, int uid, double debit) {
        try {
            String sql = "update memberinfo set Debit='" + debit + "' where " + "StatusCode=0 AND CFID=" + cfid + " AND UID=" + uid;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AllCommitteeClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Some Error Occurred in storing debitt in memberinfo");
            return false;
        }
    }

    public ArrayList<MemberInfoData> getDatasetForUID(int uid) {
        ArrayList<MemberInfoData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from memberinfo where StatusCode=0 AND uid=" + uid);
            while (rs.next()) {
                MemberData member = new MemberData();
                CommitteeData committee = new CommitteeData();
                member.setkName(rs.getString("KName"));
                member.setName(rs.getString("Name"));
                member.setUid(rs.getInt("UID"));
                member.setCredit(rs.getDouble("Credit"));
                member.setDebit(rs.getDouble("Debit"));
                committee.setCfid(rs.getInt("CFID"));
                committee.setCname(rs.getString("CName"));
                committee.setstartdate(rs.getString("StartDate"));
                committee.setenddate(rs.getString("EndDate"));
                committee.setTurn(rs.getInt("Turn"));
                MemberInfoData mid = new MemberInfoData(committee, member, rs.getString("ReferenceKey"), rs.getInt("Enteries"));
                dataset.add(mid);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to fetch data");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;

    }

    public ArrayList<MemberInfoData> getDatasetForCfid(int cfid) {
        ArrayList<MemberInfoData> dataset = new ArrayList<>();

        try {
            rs = stmt.executeQuery("select * from memberinfo where StatusCode=0 AND CFID='" + cfid + "'");
            // System.out.println("\nselect * from memberinfo where CFID="+cfid);
            while (rs.next()) {
                MemberData member = new MemberData();
                CommitteeData committee = new CommitteeData();
                member.setkName(rs.getString("KName"));
                member.setName(rs.getString("Name"));
                member.setUid(rs.getInt("UID"));
                member.setCredit(rs.getDouble("Credit"));
                member.setDebit(rs.getDouble("Debit"));
                committee.setCfid(rs.getInt("CFID"));
                committee.setCname(rs.getString("CName"));
                committee.setstartdate(rs.getString("StartDate"));
                committee.setenddate(rs.getString("EndDate"));
                committee.setTurn(rs.getInt("Turn"));
                // System.out.println("Enteries--"+rs.getString("ReferenceKey")+rs.getInt("Enteries"));
                MemberInfoData mid = new MemberInfoData(committee, member, rs.getString("ReferenceKey"), rs.getInt("Enteries"));
                dataset.add(mid);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to fetch data");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        // JOptionPane.showMessageDialog(null,"Data--"+dataset.size());
        return dataset;

    }

    public boolean deleteDatasetForUid(int uid) {
        try {
            stmt.executeUpdate("Update memberinfo set StatusCode=1 where StatusCode=0 AND UID=" + uid);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to remove data from memberinfo");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteDatasetForCfid(int cfid) {
        try {
            stmt.executeUpdate("Update memberinfo set StatusCode=1 where StatusCode=0 AND CFID=" + cfid);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to remove data from memberinfo");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteDatasetForReferenceKey(String refkey) {
        try {
            stmt.executeUpdate("Update memberinfo set StatusCode=1 where StatusCode=0 AND ReferenceKey=" + refkey);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to remove data from memberinfo");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public MemberInfoData getDatasetForReferenceKey(String refkey) {
        //TODO possibiltity of null pointer exception
        MemberInfoData dataset = null;
        try {
            //System.out.println("select * from memberinfo where ReferenceKey='"+refkey+"'");
            rs = stmt1.executeQuery("select * from memberinfo where ReferenceKey='" + refkey + "'");
            rs.next();
            //String Kname=rs.getString("KName");
            //System.out.println("Kname--"+Kname);
            MemberData member = new MemberData();
            CommitteeData committee = new CommitteeData();
            member.setkName(rs.getString("KName"));
            member.setName(rs.getString("Name"));
            member.setUid(rs.getInt("UID"));
            member.setCredit(rs.getDouble("Credit"));
            member.setDebit(rs.getDouble("Debit"));
            committee.setCfid(rs.getInt("CFID"));
            committee.setCname(rs.getString("CName"));
            committee.setstartdate(rs.getString("StartDate"));
            committee.setenddate(rs.getString("EndDate"));
            committee.setTurn(rs.getInt("Turn"));

            dataset = new MemberInfoData(committee, member, rs.getString("ReferenceKey"), rs.getInt("Enteries"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to fetch data");
            dataset = null;
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;

    }

    public boolean createNewMemberToCommittee(MemberInfoData mid) {
        try {
            stmt.executeUpdate("insert into memberinfo(ReferenceKey,UID,KName,Name,CFID,CName,StartDate,EndDate,"
                    + "Enteries,Credit,Debit,Turn) values('" + mid.getReferenceKey() + "','" + mid.getMember().getUid() + "',"
                    + "'" + mid.getMember().getKName() + "','" + mid.getMember().getName() + "','" + mid.getCommittee().getCfid() + "',"
                    + "'" + mid.getCommittee().getCname() + "','" + mid.getCommittee().getstartdate() + "','" + mid.getCommittee().getenddate() + "',"
                    + "'" + mid.getEnteries() + "','" + mid.getMember().getCredit() + "','" + mid.getMember().getDebit() + "',"
                    + "'" + mid.getCommittee().getTurn() + "')");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to add member to committee");
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getEnteriesSumForCfid(int cfid) {
        int sum = 0;
        try {
            rs = stmt.executeQuery("Select sum(Enteries) from memberinfo where StatusCode=0 AND CFID=" + cfid);
            rs.next();
            sum = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(MemberInfoClass.class.getName()).log(Level.SEVERE, null, ex);
            sum = -1;
        }
        return sum;
    }

}
