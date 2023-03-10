package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllTaskItem implements Serializable {

    @SerializedName("employee")
    private String employee;

    @SerializedName("tasks")
    private List<AdminTasksItem> tasks;

    @SerializedName("emp_id")
    private int empId;

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEmployee() {
        return employee;
    }

    public void setTasks(List<AdminTasksItem> tasks) {
        this.tasks = tasks;
    }

    public List<AdminTasksItem> getTasks() {
        return tasks;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return empId;
    }


}
