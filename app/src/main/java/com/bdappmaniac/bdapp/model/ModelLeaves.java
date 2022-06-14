package com.bdappmaniac.bdapp.model;

public class ModelLeaves {
    private String leavereason;
    private String leavetype;
    private String fromdate;
    private String tilldate;
    private String discription;
    private String status;

    public ModelLeaves(String leavereason, String leavetype, String fromdate, String tilldate, String discription, String status) {
        this.leavereason = leavereason;
        this.leavetype = leavetype;
        this.fromdate = fromdate;
        this.tilldate = tilldate;
        this.discription = discription;
        this.status = status;
    }

    public String getLeavereason() {
        return leavereason;
    }

    public void setLeavereason(String leavereason) {
        this.leavereason = leavereason;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTilldate() {
        return tilldate;
    }

    public void setTilldate(String tilldate) {
        this.tilldate = tilldate;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
