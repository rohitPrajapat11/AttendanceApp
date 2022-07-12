package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeHolidayResponse{

	@SerializedName("data")
	private List<HolidaysItem> data;

	public void setData(List<HolidaysItem> data){
		this.data = data;
	}

	public List<HolidaysItem> getData(){
		return data;
	}
}