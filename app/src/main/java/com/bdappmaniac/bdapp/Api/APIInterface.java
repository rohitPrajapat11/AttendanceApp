package com.bdappmaniac.bdapp.Api;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIInterface {

    @Multipart
    @POST("api/login")
    Call<ApiResponse> userLogIn(@PartMap Map<String, RequestBody> map);

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
    Call<ApiResponse> updateEmployeeProfile(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part filePart);

    @Multipart
    @POST("api/sendMail")
    Call<ApiResponse> sendMail(@PartMap Map<String, RequestBody> map);

    @POST("api/checkIn")
    Call<ApiResponse> checkInTime(@Header("Authorization") String token);

    @POST("api/checkOut")
    Call<ApiResponse> checkOutTime(@Header("Authorization") String token);

    @Multipart
    @POST("api/mark-absent")
    Call<ApiResponse> markAttendanceByEmployee(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/workedHoursOnDate")
    Call<ApiResponse> workedHoursOnGivenDay(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/remove-holiday/{id}")
    Call<ApiResponse> removeHoliday(@Header("Authorization") String token, @Path("id") int id);

    @Multipart
    @POST("api/getInoutsOfLastGivenDays")
    Call<ApiResponse> inAndOutsBetweenDates(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/resetPassword")
    Call<ApiResponse> resetPassword(@PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/getInOutsBwDates/{emp_id}")
    Call<ApiResponse> getInAndOutsBetweenDates(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map, @Path("emp_id") int emp_id);

    @Multipart
    @POST("api/add-condition")
    Call<ApiResponse> addTermsAndConditions(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/all-terms-and-conditions")
    Call<ApiResponse> allTermsAndConditions(@Header("Authorization") String token);

    @Multipart
    @POST("api/add-daily-rules")
    Call<ApiResponse> addDailyRules(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/daily-rules")
    Call<ApiResponse> allDailyRules(@Header("Authorization") String token);

    @Multipart
    @POST("api/add-employee-term-and-conditions")
    Call<ApiResponse> addEmployeeTermAndConditions(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/employee/terms-and-conditions/{empo_id}")
    Call<ApiResponse> specificEmployeeTermsAndConditions(@Header("Authorization") String token, @Path("empo_id") int empo_id);

    @Multipart
    @POST("api/Add-designation")
    Call<ApiResponse> addDesignation(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/All-designation")
    Call<ApiResponse> allDesignation(@Header("Authorization") String token);

    @POST("api/remove-designation/{id}")
    Call<ApiResponse> removeDesignation(@Header("Authorization") String token, @Path("id") int id);

    @POST("api/all-emp-attend-of-today")
    Call<ApiResponse> allEmpAttendance(@Header("Authorization") String token);

    @POST("api/all-inactive-employee")
    Call<ApiResponse> allInactiveEmployee(@Header("Authorization") String token);

    @POST("api/employee-status-by-id/{id}")
    Call<ApiResponse> employeeStatusById(@Header("Authorization") String token, @Path("id") int id);

    @Multipart
    @POST("api/update-designation/{id}")
    Call<ApiResponse> updateDesignation(@Header("Authorization") String token, @Path("id") int id, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/employeeRegistration")
    Call<ApiResponse> employeeRegistration(@PartMap Map<String, RequestBody> map);

    //Holidays
    @Multipart
    @POST("api/Add-Holiday")
    Call<ApiResponse> addHolidays(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/All-holidays-of-current-year")
    Call<ApiResponse> holidaysOfCurrentYear(@Header("Authorization") String token);

    @Multipart
    @POST("api/Update-Holiday/{id}")
    Call<ApiResponse> updateHoliday(@Header("Authorization") String token, @Path("id") int id, @PartMap Map<String, RequestBody> map);

    //Task Api
    @POST("api/all-task-with-employee-name")
    Call<ApiResponse> allTaskWithEmployeeName(@Header("Authorization") String token);

    @POST("api/all-task-with-employee-name/{id}")
    Call<ApiResponse> getEmployeeAllTask(@Header("Authorization") String token, @Path("id") int id);

    @Multipart
    @POST("api/create-task")
    Call<ApiResponse> createTask(@Header("Authorization") String token, @PartMap Map<String, RequestBody> map);

    @POST("api/remove-task/{id}")
    Call<ApiResponse> removeTask(@Header("Authorization") String token, @Path("id") int id);

    @Multipart
    @POST("api/update-task/{id}")
    Call<ApiResponse> updateTask(@Header("Authorization") String token, @Path("id") int id, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("api/tasks-by-id-status/{id}")
    Call<ApiResponse> getTaskByStatus(@Header("Authorization") String token, @Path("id") int id, @PartMap Map<String, RequestBody> map);

    @POST("api/get-task-of-employee")
    Call<ApiResponse> employeeTasksApi(@Header("Authorization") String token);

    @POST("api/current-year-att-for-employee")
    Call<ApiResponse>myAttendance(@Header("Authorization")String token);

}
