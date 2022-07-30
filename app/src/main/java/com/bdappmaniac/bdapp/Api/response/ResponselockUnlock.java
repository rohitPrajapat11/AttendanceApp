package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class ResponselockUnlock{

	@SerializedName("pincode")
	private int pincode;

	@SerializedName("joining_date")
	private String joiningDate;

	@SerializedName("employee_address")
	private String employeeAddress;

	@SerializedName("profile")
	private String profile;

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("lockedDate")
	private String lockedDate;

	@SerializedName("emg_mo_no")
	private long emgMoNo;

	@SerializedName("type")
	private String type;

	@SerializedName("dob")
	private String dob;

	@SerializedName("resetTokens")
	private Object resetTokens;

	@SerializedName("id")
	private int id;

	@SerializedName("designation")
	private String designation;

	@SerializedName("emp_mobile_no")
	private long empMobileNo;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	@SerializedName("inOvertime")
	private String inOvertime;

	public void setPincode(int pincode){
		this.pincode = pincode;
	}

	public int getPincode(){
		return pincode;
	}

	public void setJoiningDate(String joiningDate){
		this.joiningDate = joiningDate;
	}

	public String getJoiningDate(){
		return joiningDate;
	}

	public void setEmployeeAddress(String employeeAddress){
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeAddress(){
		return employeeAddress;
	}

	public void setProfile(String profile){
		this.profile = profile;
	}

	public String getProfile(){
		return profile;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setLockedDate(String lockedDate){
		this.lockedDate = lockedDate;
	}

	public String getLockedDate(){
		return lockedDate;
	}

	public void setEmgMoNo(long emgMoNo){
		this.emgMoNo = emgMoNo;
	}

	public long getEmgMoNo(){
		return emgMoNo;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setResetTokens(Object resetTokens){
		this.resetTokens = resetTokens;
	}

	public Object getResetTokens(){
		return resetTokens;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDesignation(String designation){
		this.designation = designation;
	}

	public String getDesignation(){
		return designation;
	}

	public void setEmpMobileNo(long empMobileNo){
		this.empMobileNo = empMobileNo;
	}

	public long getEmpMobileNo(){
		return empMobileNo;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setInOvertime(String inOvertime){
		this.inOvertime = inOvertime;
	}

	public String getInOvertime(){
		return inOvertime;
	}
}