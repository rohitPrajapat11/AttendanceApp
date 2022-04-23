package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class DataResponceStatus {

	@SerializedName("status")
	private String status;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}