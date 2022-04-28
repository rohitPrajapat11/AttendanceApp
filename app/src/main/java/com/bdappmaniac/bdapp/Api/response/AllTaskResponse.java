package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllTaskResponse{

	@SerializedName("data")
	private List<AllTaskItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public void setData(List<AllTaskItem> data){
		this.data = data;
	}

	public List<AllTaskItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}