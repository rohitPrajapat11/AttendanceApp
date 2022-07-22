package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeTaskDataItem {

	@SerializedName("date")
	private String date;

	@SerializedName("tasks")
	private List<EmployeeTasksItem> tasks;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTasks(List<EmployeeTasksItem> tasks){
		this.tasks = tasks;
	}

	public List<EmployeeTasksItem> getTasks(){
		return tasks;
	}
}