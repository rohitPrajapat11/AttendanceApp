package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeListResponse{
	@SerializedName("data")
	private List<EmpList> data;

	@SerializedName("message")
	private String message;

	@SerializedName("Success")
	private boolean success;

	public void setData(List<EmpList> data){
		this.data = data;
	}

	public List<EmpList> getData(){
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