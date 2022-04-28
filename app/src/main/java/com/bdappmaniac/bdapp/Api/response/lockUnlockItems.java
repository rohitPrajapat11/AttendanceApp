package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class lockUnlockItems {

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("date")
	private String date;

	@SerializedName("status")
	private String status;

	@SerializedName("id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}