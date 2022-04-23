package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class EmpStatusbyIdResponse{

	@SerializedName("data")
	private DataResponceStatus data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public void setData(DataResponceStatus data){
		this.data = data;
	}

	public DataResponceStatus getData(){
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