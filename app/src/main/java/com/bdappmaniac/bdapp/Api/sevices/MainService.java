package com.bdappmaniac.bdapp.Api.sevices;

import android.content.Context;

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
        Call<ApiResponse> call = apiService.UserLogIn(map);
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

    public static LiveData<ApiResponse> EmployeeRegistration(Context context, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        Call<ApiResponse> call = apiService.EmployeeRegistration(map);
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

}
