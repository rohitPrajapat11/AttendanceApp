package com.bdappmaniac.bdapp.Api.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.annotations.SerializedName;

public class ApiResponse{

	@SerializedName("success")
	private boolean success;

	@SerializedName("data")
	private JsonElement data;

	@SerializedName("message")
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		if (!(data instanceof JsonNull))
			this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
