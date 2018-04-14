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
public class MemberInfoData {

    CommitteeData committee;
    MemberData member;
    String referenceKey;
    int enteries;

    public MemberInfoData(CommitteeData com, MemberData mem, String refKey, int enteries) {
        this.committee = com;
        this.member = mem;
        this.referenceKey = refKey;
        this.enteries = enteries;
    }

    public MemberData getMember() {
        return this.member;
    }

    public CommitteeData getCommittee() {
        return this.committee;
    }

    public String getReferenceKey() {
        return this.referenceKey;
    }

    public int getEnteries() {
        return this.enteries;
    }
}
