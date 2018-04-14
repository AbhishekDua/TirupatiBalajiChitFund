/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

/**
 *
 * @author abhishek
 */
public class TransactionData {

    int transactionID;
    String referenceKey;
    int UID;
    String name;
    int CFID;
    String CName;
    int enteries;
    int transactionType;
    int forTurn;
    double credit;
    double debit;
//    double netCredit;
//    double netDebit;
    String date;

    public TransactionData() {
    }

    public TransactionData(int transactionID, String referenceKey, int UID, String name, int CFID, String CName, int enteries, int transactionType, int forTurn, double credit, double debit, String date) {
        this.transactionID = transactionID;
        this.referenceKey = referenceKey;
        this.UID = UID;
        this.name = name;
        this.CFID = CFID;
        this.CName = CName;
        this.enteries = enteries;
        this.transactionType = transactionType;
        this.forTurn = forTurn;
        this.credit = credit;
        this.debit = debit;
//        this.netCredit = netCredit;
//        this.netDebit = netDebit;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCName() {
        return CName;
    }

    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int tid)
    {
        this.transactionID=tid;
        
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public int getUID() {
        return UID;
    }

    public int getCFID() {
        return CFID;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public int getEnteries() {
        return enteries;
    }

    public void setEnteries(int enteries) {
        this.enteries = enteries;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getForTurn() {
        return forTurn;
    }

    public void setForTurn(int forTurn) {
        this.forTurn = forTurn;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void setCFID(int CFID) {
        this.CFID = CFID;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

//    public double getNetCredit() {
//        return netCredit;
//    }
//
//    public void setNetCredit(double netCredit) {
//        this.netCredit = netCredit;
//    }
//
//    public double getNetDebit() {
//        return netDebit;
//    }
//
//    public void setNetDebit(double netDebit) {
//        this.netDebit = netDebit;
//    }

}
