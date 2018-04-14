/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author abhishek
 */
public class TransactionTableClass {

    private Connection con1, con;
    private Statement stmt, stmt1;
    private ResultSet rs, rs1;
    static TransactionTableClass transactionTable;

    public TransactionTableClass() {
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

    public static TransactionTableClass getInstance() {
        if (transactionTable == null) {
            transactionTable = new TransactionTableClass();
        }
        return transactionTable;
    }

    public ArrayList<TransactionData> getAllTransactions() {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable");
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }

        return dataset;

    }

    public ArrayList<TransactionData> getAllTransactionsForRefID(String refid) {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable where ReferenceKey='" + refid + "'");
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }

        return dataset;

    }

    public ArrayList<TransactionData> getAllTransactionsForCFID(int cfid) {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable where CFID=" + cfid);
            System.out.println("select * from TransactionTable where CFID=" + cfid);
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }
        System.out.println("\nDataset size--" + dataset.size());
        return dataset;

    }

    public ArrayList<TransactionData> getAllTransactionsForUID(int uid) {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable where uid=" + uid);
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }

        return dataset;

    }

    public ArrayList<TransactionData> getAllTransactionsForDate(String date) {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable where date='" + date + "'");
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }

        return dataset;

    }

    public boolean deleteTransactionsForUid(int uid) {
        try {
            stmt.executeUpdate("update TransactionTable set TransactionType=1 where TransactionType=0 AND UID='" + uid+"'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionTableClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<TransactionData> getAllTransactionsForDeleted() {
        ArrayList<TransactionData> dataset = new ArrayList<>();
        try {
            rs = stmt.executeQuery("select * from TransactionTable where TransactionType=1");
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Deleted Records");
            sqle.printStackTrace();
        }

        return dataset;
    }

     public boolean addNewTransaction(TransactionData transaction) {
        try {
            String sql="insert into TransactionTable(ReferenceKey,UID,Name,CFID,CName,Enteries,TransactionType,"
                    + "ForTurn,Credit,Debit,Date) values('" + transaction.getReferenceKey() + "','" + transaction.getUID() + "',"
                    + "'" + transaction.getName() + "','" + transaction.getCFID() + "','" + transaction.getCName() + "',"
                    + "'" + transaction.getEnteries() + "','" + transaction.getTransactionType() + "','" + transaction.getForTurn() + "'"
                    + ",'" + transaction.getCredit() + "','" + transaction.getDebit() + "','" + transaction.getDate() + "')";
            System.out.println("insert into TransactionTable(ReferenceKey,UID,Name,CFID,CName,Enteries,TransactionType,"
                    + "ForTurn,Credit,Debit,Date) values('" + transaction.getReferenceKey() + "','" + transaction.getUID() + "',"
                    + "'" + transaction.getName() + "','" + transaction.getCFID() + "','" + transaction.getCName() + "',"
                    + "'" + transaction.getEnteries() + "','" + transaction.getTransactionType() + "','" + transaction.getForTurn() + "'"
                    + ",'" + transaction.getCredit() + "','" + transaction.getDebit() + "','" + transaction.getDate() + "')");
//            stmt.executeUpdate("insert into TransactionTable(ReferenceKey,UID,Name,CFID,CName,Enteries,TransactionType,"
//                    + "ForTurn,Credit,Debit,Date) values('" + transaction.getReferenceKey() + "','" + transaction.getUID() + "',"
//                    + "'" + transaction.getName() + "','" + transaction.getCFID() + "','" + transaction.getCName() + "',"
//                    + "'" + transaction.getEnteries() + "','" + transaction.getTransactionType() + "','" + transaction.getForTurn() + "'"
//                    + "'" + transaction.getCredit() + "','" + transaction.getDebit() + "','" + transaction.getDate() + "')");
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to add  Record");
            sqle.printStackTrace();
            return false;
        }
    }
    
     public boolean deleteTransactionForTid(int tid) {
        try {
            stmt.executeUpdate("update TransactionTable set TransactionType=1 where TransactionType=0 AND TransactionId=" + tid);
          
            return true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(TransactionTableClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /*public ArrayList<TransactionData> getTransactionForTransactionId(int tid)
    {
        ArrayList <TransactionData> dataset=new ArrayList<>();
        try
        {
            stmt.executeUpdate("select * from TransactionTable where TransactionId='"+tid+"'");
            while (rs.next()) {

                dataset.add(new TransactionData(rs.getInt("TransactionId"), rs.getString("ReferenceKey"), rs.getInt("UID"),
                        rs.getString("Name"), rs.getInt("CFID"), rs.getString("CName"), rs.getInt("Enteries"), rs.getInt("TransactionType"),
                        rs.getInt("ForTurn"), rs.getDouble("Credit"), rs.getDouble("Debit"),
                        rs.getString("Date")));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }

        return dataset;
        
    }*/
     
     public TransactionData getTransactionForTransactionId(int tid)
    {
        TransactionData dataset=new TransactionData();
        try
        {
            rs=stmt.executeQuery("select * from TransactionTable where TransactionId="+tid);
            while (rs.next()) {

                dataset.setTransactionID(rs.getInt("TransactionId"));
                dataset.setReferenceKey(rs.getString("ReferenceKey"));
                dataset.setUID(rs.getInt("UID"));
                dataset.setCName(rs.getString("CName"));                
                dataset.setCFID(rs.getInt("CFID"));
                dataset.setName(rs.getString("Name"));
                dataset.setEnteries(rs.getInt("Enteries"));
                dataset.setTransactionType(rs.getInt("TransactionType"));
                dataset.setForTurn(rs.getInt("ForTurn"));
                dataset.setCredit(rs.getDouble("Credit"));
                dataset.setDebit(rs.getDouble("Debit"));
                dataset.setDate(rs.getString("Date"));
//                 JOptionPane.showMessageDialog(null,dataset.getTransactionID()+""+dataset.getCFID()+""+dataset.getName()+dataset.getDebit()+"-"+dataset.getCredit());
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Unable to fetch Transaction Records");
            sqle.printStackTrace();
        }
        if(dataset!=null)
            return dataset;
        else {
            JOptionPane.showMessageDialog(null,dataset.getTransactionID()+""+dataset.getCFID()+""+dataset.getName()+dataset.getDebit()+"-"+dataset.getCredit());
            return null;
        }
        
    }

}
