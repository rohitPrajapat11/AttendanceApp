package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MyAttendanceData {

	@SerializedName("month")
	private String month;

	@SerializedName("attendance")
	private List<MyAttendanceItem> attendance;

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return month;
	}

	public void setAttendance(List<MyAttendanceItem> attendance){
		this.attendance = attendance;
	}

	public List<MyAttendanceItem> getAttendance(){
		return attendance;
	}
}