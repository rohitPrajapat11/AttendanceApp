package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class InoutsItem{

	@SerializedName("date")
	private String date;

	@SerializedName("absent_reason")
	private Object absentReason;

	@SerializedName("logIn")
	private Object logIn;

	@SerializedName("logOut")
	private Object logOut;

	@SerializedName("emp_id")
	private int empId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setAbsentReason(Object absentReason){
		this.absentReason = absentReason;
	}

	public Object getAbsentReason(){
		return absentReason;
	}

	public void setLogIn(Object logIn){
		this.logIn = logIn;
	}

	public Object getLogIn(){
		return logIn;
	}

	public void setLogOut(Object logOut){
		this.logOut = logOut;
	}

	public Object getLogOut(){
		return logOut;
	}

	public void setEmpId(int empId){
		this.empId = empId;
	}

	public int getEmpId(){
		return empId;
	}
}