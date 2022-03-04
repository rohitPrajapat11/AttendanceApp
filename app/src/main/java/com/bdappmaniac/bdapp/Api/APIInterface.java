package com.bdappmaniac.bdapp.Api;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIInterface {

    @Multipart
    @POST("api/login")
    Call<ApiResponse> userLogIn(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/employeeRegistration")
    Call<ApiResponse> employeeRegistration(@PartMap Map<String, RequestBody> map);

    @POST("api/employee/{id}")
    Call<ApiResponse> GetEmployeeById(@Header("Authorization") String token, @Path("id") int id);

    @POST("api/allemployees")
    Call<ApiResponse> employeeList(@Header("Authorization") String token);
}
