package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.annotations.SerializedName;

public class HolidaysItem{

	@SerializedName("date")
	private String date;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}