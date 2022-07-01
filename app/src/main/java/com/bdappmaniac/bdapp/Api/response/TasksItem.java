package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class TasksItem{

	@SerializedName("created_at")
	private String createdAt;

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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
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