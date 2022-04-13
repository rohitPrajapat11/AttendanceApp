package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DesignationResponse{

	@SerializedName("data")
	private List<DesignationItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public void setData(List<DesignationItem> data){
		this.data = data;
	}

	public List<DesignationItem> getData(){
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