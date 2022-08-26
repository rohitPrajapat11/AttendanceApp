package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MyAttendanceItem {

	@SerializedName("month")
	private String month;

	@SerializedName("Worked_hours")
	private Object workedHours;

	@SerializedName("isHoliday")
	private boolean isHoliday;

	@SerializedName("Day")
	private String day;

	@SerializedName("Date")
	private String date;

	@SerializedName("isAbsent")
	private boolean isAbsent;

	@SerializedName("Inouts")
	private List<MyAttendanceInOuts> inouts;

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setWorkedHours(Object workedHours){
		this.workedHours = workedHours;
	}

	public Object getWorkedHours(){
		return workedHours;
	}

	public void setIsHoliday(boolean isHoliday){
		this.isHoliday = isHoliday;
	}

	public boolean isIsHoliday(){
		return isHoliday;
	}

	public void setDay(String day){
		this.day = day;
	}

	public String getDay(){
		return day;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setIsAbsent(boolean isAbsent){
		this.isAbsent = isAbsent;
	}

	public boolean isIsAbsent(){
		return isAbsent;
	}

	public void setInouts(List<MyAttendanceInOuts> inouts){
		this.inouts = inouts;
	}

	public List<MyAttendanceInOuts> getInouts() {
		return inouts;
	}

}