package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeHoliday {

	@SerializedName("month")
	private String month;

	@SerializedName("holidays")
	private List<HolidaysItem> holidays;

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setHolidays(List<HolidaysItem> holidays){
		this.holidays = holidays;
	}

	public List<HolidaysItem> getHolidays(){
		return holidays;
	}
}