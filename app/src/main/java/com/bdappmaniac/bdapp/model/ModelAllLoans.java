package com.bdappmaniac.bdapp.model;

public class ModelAllLoans {
    private String leaveReason;
    private  String leaveType;
    private String fromDate;
    private String tillDate;
    private String discription;

    public ModelAllLoans(String leaveReason, String leaveType, String fromDate, String tillDate, String discription) {
        this.leaveReason = leaveReason;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
        this.discription = discription;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getTillDate() {
        return tillDate;
    }

    public void setTillDate(String tillDate) {
        this.tillDate = tillDate;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
