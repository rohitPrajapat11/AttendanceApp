package com.bdappmaniac.bdapp.Api.sevices;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bdappmaniac.bdapp.Api.APIInterface;
import com.bdappmaniac.bdapp.Api.response.ApiResponse;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.application.BdApp;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService {
    //    private static final APIInterface apiService = RetrofitClient.getRetrofitInstance().create(APIInterface.class);
    private static final APIInterface apiService = BaseService.getAPIClient("").create(APIInterface.class);

    @NonNull
    public static LiveData<ApiResponse> userLogIn(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.userLogIn(map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());

            }
        });
        return data;
    }

    public static LiveData<ApiResponse> employeeRegistration(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.employeeRegistration(map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> employeeList(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.employeeList(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> getEmployeeById(Context context, String token, int id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.getEmployeeById(token, id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> updateProfileByAdmin(Context context, String token, int id, String status) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.updateProfileByAdmin(token, id, status);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> userLogout(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.userLogout(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> updateEmployeeProfile(Context context, String token, Map<String, RequestBody> map, MultipartBody.Part fileToUpload) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.updateEmployeeProfile(token, map, fileToUpload);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> sendMail(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.sendMail(map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());

            }
        });
        return data;
    }

    public static LiveData<ApiResponse> checkInTime(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.checkInTime(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> checkOutTime(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.checkOutTime(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> addHolidays(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.addHolidays(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> markAttendanceByEmployee(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.markAttendanceByEmployee(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> holidaysOfCurrentYear(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.holidaysOfCurrentYear(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> workedHoursOnGivenDay(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.workedHoursOnGivenDay(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> removeHoliday(Context context, String token, int id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.removeHoliday(token, id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> inAndOutsBetweenDates(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.inAndOutsBetweenDates(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> resetPassword(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.resetPassword(map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());

            }
        });
        return data;
    }

    public static LiveData<ApiResponse> getInAndOutsBetweenDates(Context context, String token, Map<String, RequestBody> map, int emp_id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.getInAndOutsBetweenDates(token, map, emp_id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> addTermsAndConditions(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.addTermsAndConditions(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> allTermsAndConditions(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.allTermsAndConditions(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> addDailyRules(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.addDailyRules(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> allDailyRules(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.allDailyRules(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> addEmployeeTermAndConditions(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.addEmployeeTermAndConditions(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> specificEmployeeTermsAndConditions(Context context, String token, int empo_id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.specificEmployeeTermsAndConditions(token, empo_id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> updateHoliday(Context context, String token, int id, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.updateHoliday(token, id, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> addDesignation(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.addDesignation(token, map);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> allDesignation(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.allDesignation(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> removeDesignation(Context context, String token, int id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.removeDesignation(token, id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> allEmpAttendance(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.allEmpAttendance(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> allInactiveEmployee(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.allInactiveEmployee(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> employeeStatusbyId(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        if(!BdApp.getInstance().isInternetConnected(context)){
            return data;
        }
        Call<ApiResponse> call = apiService.employeeStatusbyId(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }
}
