package com.bdappmaniac.bdapp.model;

public class ModelTaskList {
    private String issueDate;
    private String taskHeading;
    private String discription;
    private String complitionDate;
    private int imgtasktype;
    private String tasktype;

    public ModelTaskList(String issueDate, String taskHeading, String discription, String complitionDate, int imgtasktype, String tasktype) {
        this.issueDate = issueDate;
        this.taskHeading = taskHeading;
        this.discription = discription;
        this.complitionDate = complitionDate;
        this.imgtasktype = imgtasktype;
        this.tasktype = tasktype;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getTaskHeading() {
        return taskHeading;
    }

    public void setTaskHeading(String taskHeading) {
        this.taskHeading = taskHeading;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getComplitionDate() {
        return complitionDate;
    }

    public void setComplitionDate(String complitionDate) {
        this.complitionDate = complitionDate;
    }

    public int getImgtasktype() {
        return imgtasktype;
    }

    public void setImgtasktype(int imgtasktype) {
        this.imgtasktype = imgtasktype;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }
}
