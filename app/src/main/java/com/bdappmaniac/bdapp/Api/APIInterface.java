package com.bdappmaniac.bdapp.Api;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    Call<ApiResponse> getEmployeeById(@Header("Authorization") String token, @Path("id") int id);

    @POST("api/allemployees")
    Call<ApiResponse> employeeList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/updateProfileByAdmin/{id}")
    Call<ApiResponse> updateProfileByAdmin(@Header("Authorization") String token, @Path("id") int id, @Field("status") String status);

    @POST("api/logout")
    Call<ApiResponse> userLogout(@Header("Authorization") String token);

    @Multipart
    @POST("api/updateProfile")
    Call<ApiResponse> updateEmployeeProfile(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/sendMail")
    Call<ApiResponse> sendMail(@PartMap Map<String, RequestBody> map);

    @POST("api/checkIn")
    Call<ApiResponse> checkInTime(@Header("Authorization") String token);

    @POST("api/checkOut")
    Call<ApiResponse> checkOutTime(@Header("Authorization") String token);
    @Multipart
    @POST("api/Add-Holiday")
    Call<ApiResponse> addHolidays(@Header("Authorization") String token, @PartMap Map<String, RequestBody>map);

}
