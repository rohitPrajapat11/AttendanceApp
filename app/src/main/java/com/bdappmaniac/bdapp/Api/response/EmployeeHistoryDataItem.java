package com.bdappmaniac.bdapp.Api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EmployeeHistoryDataItem {

	@SerializedName("date")
	private String date;

	@SerializedName("inouts")
	private List<InoutsItem> inouts;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setInouts(List<InoutsItem> inouts){
		this.inouts = inouts;
	}

	public List<InoutsItem> getInouts(){
		return inouts;
	}
}