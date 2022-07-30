package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("time")
	private String time;

	public String getTime(){
		return time;
	}
}