package com.bdappmaniac.bdapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.LogoutDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.PresentAndAbsentDialogboxBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.service.ForegroundService;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.google.android.material.snackbar.Snackbar;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseActivity extends AppCompatActivity {
    public static final String CURRENT_TIME = "current_time";
    public static final String CURRENT_DATE = "current_date";
    public static final String USER_WORK = "userWork";
    public static final String NOTIFICATION = "notification";
    public static final String MOBILE = "mobile";
    public static final String PIN_KEY = "key";

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void userLogout() {
        AppLoader.showLoaderDialog(this);
        MainService.userLogout(this, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showToast(this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    SharedPref.init(this);
                    SharedPref.putUserDetails(null);
                } else {
                    ((BaseActivity) this).showToast(this.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    void logoutDialog() {
        LogoutDialogboxBinding logoutDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.logout_dialogbox, null, false);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(logoutDialogboxBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        logoutDialogboxBinding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogout();
                startActivity(new Intent(getBaseContext(), AuthActivity.class));
                finish();
                showToast(getString(R.string.logout));
                dialog.dismiss();
            }
        });
        logoutDialogboxBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }



    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        if (SharedPref.read(NOTIFICATION, true) && SharedPref.read(USER_WORK, true)) {
            serviceIntent.putExtra("inputExtra", getString(R.string.time_started_at) + SharedPref.getStringValue(CURRENT_TIME));
            ContextCompat.startForegroundService(this, serviceIntent);
        }
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    public String getToken()
    {
        SharedPref.init(this);
        String getToken = "Bearer "+SharedPref.getUserDetails().getAccessToken();
        return getToken;
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        return toRequestBodyPart(val);
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }

//    void callNotification() {
//        Notify.build(getApplicationContext())
//                .setTitle("BD APP")
//                .setContent("You Are LogeIn!")
//                .setSmallIcon(R.drawable.app_logo)
//                .setLargeIcon(R.drawable.app_logo)
//                .setColor(R.color.green)
//                .setAutoCancel(true)
//                .show();
//    }
}

