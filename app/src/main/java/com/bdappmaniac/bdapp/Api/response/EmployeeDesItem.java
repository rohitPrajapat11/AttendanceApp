package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class EmployeeDesItem {

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("designation")
	private String designation;

	@SerializedName("attendance")
	private String attendance;

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setDesignation(String designation){
		this.designation = designation;
	}

	public String getDesignation(){
		return designation;
	}

	public void setAttendance(String attendance){
		this.attendance = attendance;
	}

	public String getAttendance(){
		return attendance;
	}
}