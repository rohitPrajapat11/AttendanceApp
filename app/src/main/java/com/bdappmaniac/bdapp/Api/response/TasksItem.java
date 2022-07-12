package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TasksItem {

    @SerializedName("date")
    private String date;

    @SerializedName("tasks")
    private List<TasksItem> tasks;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("deadline")
    private String deadline;

    @SerializedName("content")
    private String content;

    @SerializedName("emp_id")
    private int empId;

    @SerializedName("status")
    private String status;

    public TasksItem(String title, String content, String deadline) {
        this.title = title;
        this.content = content;
        this.deadline = deadline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TasksItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksItem> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}