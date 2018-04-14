/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tirupatibalajichitfund.db;

/**
 *
 * @author ABHISHEK
 */
public class MemberData {

    int uid;
    String kName;
    String name;
    String phone;
    double credit;
    double debit;

    public MemberData(int id, String kname, String name, String phonenumb, double credit, double debit) {
        this.uid = id;
        this.name = name;
        this.kName = kname;
        this.phone = phonenumb;
        this.credit = credit;
        this.debit = debit;
    }

    public MemberData() {
        //To change body of generated methods, choose Tools | Templates.
        this.uid = 0;
        this.kName = null;
        this.name = null;
        this.phone = null;
        this.credit = 0.0;
        this.debit = 0.0;
    }

    public int getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public String getKName() {
        return this.kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getCredit() {
        return this.credit;
    }

    public double getDebit() {
        return this.debit;
    }

    public void setUid(int id) {
        this.uid = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }
}
