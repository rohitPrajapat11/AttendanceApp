package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("absent")
	private boolean absent;

	@SerializedName("worked_hours")
	private String workedHours;

	public void setAbsent(boolean absent){
		this.absent = absent;
	}

	public boolean isAbsent(){
		return absent;
	}

	public void setWorkedHours(String workedHours){
		this.workedHours = workedHours;
	}

	public String getWorkedHours(){
		return workedHours;
	}
}