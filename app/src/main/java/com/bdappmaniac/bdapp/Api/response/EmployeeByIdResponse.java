package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class EmployeeByIdResponse {

    @SerializedName("pincode")
    private Object pincode;

    @SerializedName("employee_address")
    private String employeeAddress;

    @SerializedName("profile")
    private String profile;

    @SerializedName("employee_name")
    private String employeeName;

    @SerializedName("emg_mo_no")
    private int emgMoNo;

    @SerializedName("type")
    private String type;

    @SerializedName("dob")
    private Object dob;

    @SerializedName("resetTokens")
    private String resetTokens;

    @SerializedName("id")
    private int id;

    @SerializedName("designation")
    private String designation;

    @SerializedName("emp_mobile_no")
    private int empMobileNo;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private String status;

    @SerializedName("joining_date")
    private String joiningdate;

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmgMoNo() {
        return emgMoNo;
    }

    public void setEmgMoNo(int emgMoNo) {
        this.emgMoNo = emgMoNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public String getResetTokens() {
        return resetTokens;
    }

    public void setResetTokens(String resetTokens) {
        this.resetTokens = resetTokens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(int empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}