package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeListResponse{

	@SerializedName("data")
	public List<EmployeeList> data;

	public void setData(List<EmployeeList> data){
		this.data = data;
	}

	public List<EmployeeList> getData(){
		return data;
	}
}