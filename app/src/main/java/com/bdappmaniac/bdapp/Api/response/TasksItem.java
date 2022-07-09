package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TasksItem{

	@SerializedName("date")
	private String date;

	@SerializedName("tasks")
	private List<TasksItem> tasks;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("deadline")
	private String deadline;

	@SerializedName("content")
	private String content;

	@SerializedName("emp_id")
	private int empId;

	@SerializedName("status")
	private String status;

	public TasksItem(String title, String deadline, String content) {
		this.title = title;
		this.deadline = deadline;
		this.content = content;
	}

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

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDeadline(String deadline){
		this.deadline = deadline;
	}

	public String getDeadline(){
		return deadline;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setEmpId(int empId){
		this.empId = empId;
	}

	public int getEmpId(){
		return empId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}