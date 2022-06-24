package com.bdappmaniac.bdapp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.LogoutDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.NoInternetBinding;
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
    public static final String OTP_KEY = "Otp";
    public static final String EMAIL = "Email";

    public Dialog noInternetdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noInternetdialog = new Dialog(this);
    }

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
                    startActivity(new Intent(getBaseContext(), AuthActivity.class));
                    finish();
                } else {
                    ((BaseActivity) this).showToast(apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void logoutDialog() {
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
        logoutDialogboxBinding.okBtn.setOnClickListener((View.OnClickListener) view -> {
            userLogout();
            dialog.dismiss();
        });
        logoutDialogboxBinding.cancelBtn.setOnClickListener((View.OnClickListener) view -> dialog.dismiss());
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

    public String getToken() {
        SharedPref.init(this);
        return "Bearer " + SharedPref.getUserDetails().getAccessToken();
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        return toRequestBodyPart(val);
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }

    public void noInternetDialog() {
        if (!noInternetdialog.isShowing()) {
            NoInternetBinding sDialog = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.no_internet, null, false);
            noInternetdialog = new Dialog(this);
            noInternetdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            noInternetdialog.setContentView(sDialog.getRoot());
            noInternetdialog.setCancelable(false);
            noInternetdialog.setCanceledOnTouchOutside(false);
            noInternetdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            noInternetdialog.getWindow().setGravity(Gravity.CENTER);
            noInternetdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            sDialog.ok.setOnClickListener(v ->
            {
                noInternetdialog.dismiss();
                AppLoader.hideLoaderDialog();
            });
            sDialog.setting.setOnClickListener((View.OnClickListener) view -> {
                noInternetdialog.dismiss();
                AppLoader.hideLoaderDialog();
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
            });
            noInternetdialog.show();
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (this.getCurrentFocus() != null) {
//            KeyboardUtils.hideSoftKeyboard(this);
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
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

