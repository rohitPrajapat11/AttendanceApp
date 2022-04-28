package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TasksItem implements Serializable {

	@SerializedName("date")
	private String date;

	@SerializedName("tasks")
	private List<TasksItem> tasks;

	@SerializedName("id")
	private int id;

	@SerializedName("content")
	private String content;

	@SerializedName("status")
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTasks(List<TasksItem> tasks){
		this.tasks = tasks;
	}

	public List<TasksItem> getTasks(){
		return tasks;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}