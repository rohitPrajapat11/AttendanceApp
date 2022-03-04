package com.bdappmaniac.bdapp.Api.sevices;

import android.content.Context;
import android.media.session.MediaSession;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bdappmaniac.bdapp.Api.APIInterface;
import com.bdappmaniac.bdapp.Api.response.ApiResponse;
import com.bdappmaniac.bdapp.Api.response.RetrofitClient;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Map;
import java.util.logging.Logger;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService extends BaseFragment {

    private static final APIInterface apiService = RetrofitClient.getRetrofitInstance().create(APIInterface.class);

    public static LiveData<ApiResponse> userLogIn(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
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
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
               ((BaseActivity)context).showToast(context.getString(R.string.something_went_wrong));
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> employeeRegistration(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
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
               ((BaseActivity)context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> employeeList(Context context, String token) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
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
                ((BaseActivity)context).showToast(context.getString(R.string.something_went_wrong));
            }
        });
        return data;
    }

//    public static LiveData<ApiResponse> employId(Context context, int id) {
//        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
//        Call<ApiResponse> call = apiService.employId(id);
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                if (response.body() != null) {
//                    data.setValue(response.body());
//                } else {
//                    data.setValue(null);
//                }
//            }
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                ((BaseActivity)context).showToast(context.getString(R.string.something_went_wrong));
//            }
//        });
//        return data;
//    }

    public static LiveData<ApiResponse> getEmployeeById(String token, int id) {

        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        Call<ApiResponse> call = apiService.GetEmployeeById(token,id);
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
            }
        });
        return data;
    }
}
