package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class AllTaskItem {

	@SerializedName("employee")
	private String employee;

	@SerializedName("tasks")
	private Object tasks;

	public String getEmployee(){
		return employee;
	}

	public Object getTasks(){
		return tasks;
	}
}