package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class EmployeeHoliday {

	@SerializedName("month")
	private String month;

	@SerializedName("holidays")
	private Object holidays;

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setHolidays(Object holidays){
		this.holidays = holidays;
	}

	public Object getHolidays(){
		return holidays;
	}
}