package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class EmpRegisterResponse {

    @SerializedName("employee_name")
    private String employeeName;

    @SerializedName("Token")
    private String token;

    @SerializedName("id")
    private int id;

    @SerializedName("emp_mobile_no")
    private String empMobileNo;

    @SerializedName("email")
    private String email;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}