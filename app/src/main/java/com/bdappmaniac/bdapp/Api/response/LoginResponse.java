package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

	@SerializedName("pincode")
	private Object pincode;

	@SerializedName("employee_address")
	private String employeeAddress;

	@SerializedName("profile")
	private String profile;

	@SerializedName("token")
	private String accessToken;

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("emg_mo_no")
	private long emgMoNo;

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
	private long empMobileNo;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	@SerializedName("GSTID")
	private String GSTID;

	@SerializedName("GSTIN")
	private String GSTIN;

	@SerializedName("incorDate")
	private String incorDate;

	@SerializedName("otherNumber")
	private String otherNumber;

	@SerializedName("joining_date")
	private String joiningdate;

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getGSTID() {
		return GSTID;
	}

	public void setGSTID(String GSTID) {
		this.GSTID = GSTID;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String GSTIN) {
		this.GSTIN = GSTIN;
	}

	public String getIncorDate() {
		return incorDate;
	}

	public void setIncorDate(String incorDate) {
		this.incorDate = incorDate;
	}

	public String getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}

	public void setPincode(Object pincode){
		this.pincode = pincode;
	}

	public Object getPincode(){
		return pincode;
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

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
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

	public void setDob(Object dob){
		this.dob = dob;
	}

	public Object getDob(){
		return dob;
	}

	public void setResetTokens(String resetTokens){
		this.resetTokens = resetTokens;
	}

	public String getResetTokens(){
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
}