/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

/**
 *
 * @author ABHISHEK
 */
public class CommitteeData {

    int Cfid;
    String Cname;
    String startdate;
    String enddate;
    int amount;
    int turn;

    public CommitteeData(int id, String name, String sdate, String edate, int amount, int turn) {
        this.Cfid = id;
        this.Cname = name;
        this.startdate = sdate;
        this.enddate = edate;
        this.amount = amount;
        this.turn = turn;

    }

    public CommitteeData() {
        //To change body of generated methods, choose Tools | Templates.
        this.Cfid = 0;
        this.Cname = null;
        this.startdate = null;
        this.enddate = null;
        this.amount = 0;
        this.turn = 0;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getCfid() {
        return this.Cfid;
    }

    public String getCname() {
        return this.Cname;
    }

    public String getstartdate() {
        return this.startdate;
    }

    public String getenddate() {
        return this.enddate;
    }

    public void setCfid(int id) {
        this.Cfid = id;
    }

    public void setCname(String name) {
        this.Cname = name;
    }

    public void setstartdate(String sdate) {
        this.startdate = sdate;
    }

    public void setenddate(String enddate) {
        this.enddate = enddate;
    }
}
