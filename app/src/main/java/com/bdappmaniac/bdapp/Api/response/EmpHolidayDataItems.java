package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmpHolidayDataItems {

	@SerializedName("month")
	private String month;

	@SerializedName("holidays")
	private List<EmpHolidaysItem> holidays;

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setHolidays(List<EmpHolidaysItem> holidays){
		this.holidays = holidays;
	}

	public List<EmpHolidaysItem> getHolidays(){
		return holidays;
	}
}