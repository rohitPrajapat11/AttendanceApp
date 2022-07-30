package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class CheckInResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isSuccess(){
		return success;
	}
}