package com.bdappmaniac.bdapp.model;

public class ManageApprovedModel {

    private String title;
    private String amount;
    private String time;
    private String emi;
    private String discription;

    public ManageApprovedModel(String title, String amount, String time, String emi, String discription) {
        this.title = title;
        this.amount = amount;
        this.time = time;
        this.emi = emi;
        this.discription = discription;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
