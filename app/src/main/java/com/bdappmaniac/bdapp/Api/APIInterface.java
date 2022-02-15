package com.bdappmaniac.bdapp.Api;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface APIInterface {

    @Multipart
    @POST("api/login")
    Call<ApiResponse> UserLogIn(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/employeeRegistration")
    Call<ApiResponse> EmployeeRegistration(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/employee/90")
    Call<ApiResponse> EmployId(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/allemployees")
    Call<ApiResponse> EmployList(@PartMap Map<String, RequestBody> map);
}
