package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class lockUnlockItems {

	@SerializedName("employee_name")
	private String employeeName;

	@SerializedName("time")
	private String time;

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

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}