package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeHolidayResponse{

	@SerializedName("data")
	private List<EmployeeHolidayList> data;

	public void setData(List<EmployeeHolidayList> data){
		this.data = data;
	}

	public List<EmployeeHolidayList> getData(){
		return data;
	}
}