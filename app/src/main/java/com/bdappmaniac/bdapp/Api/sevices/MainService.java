package com.bdappmaniac.bdapp.Api.sevices;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.bdappmaniac.bdapp.Api.APIInterface;
import com.bdappmaniac.bdapp.Api.response.ApiResponse;
import com.bdappmaniac.bdapp.Api.response.RetrofitClient;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.SharedPref;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainService extends BaseFragment {

    private static final APIInterface apiService = RetrofitClient.getRetrofitInstance().create(APIInterface.class);

    @NonNull
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
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());

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
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
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
                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                //Log.e("LOGIN API FAILED",t.getLocalizedMessage());
            }
        });
        return data;
    }

    public static LiveData<ApiResponse> getEmployeeById(Context context, String token, int id) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
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

    public static LiveData<ApiResponse> updateEmployeeProfile(Context context, String token, Map<String, RequestBody> map) {
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        Call<ApiResponse> call = apiService.updateEmployeeProfile(token, map);
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
}
