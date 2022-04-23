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

	public List<AllTaskItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isSuccess(){
		return success;
	}
}