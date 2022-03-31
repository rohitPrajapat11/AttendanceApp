package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeHistoryResponse{

	@SerializedName("data")
	private List<EmployeeHistoryDataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public void setData(List<EmployeeHistoryDataItem> data){
		this.data = data;
	}

	public List<EmployeeHistoryDataItem> getData(){
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