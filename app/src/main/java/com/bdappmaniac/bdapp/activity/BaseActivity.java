package com.bdappmaniac.bdapp.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.application.isradeleon.notify.Notify;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ExitDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.LogoutDialogboxBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void userLogout() {
        SharedPref.init(this);
        String getToken = SharedPref.getUserDetails().getAccessToken();
        AppLoader.showLoaderDialog(this);
        MainService.userLogout(this, "Bearer " + getToken).observe((LifecycleOwner) this, apiResponse -> {
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
        });
        AppLoader.hideLoaderDialog();
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
                showToast("LogOut");
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

    void exitDialog() {
        ExitDialogboxBinding exitDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.exit_dialogbox, null, false);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(exitDialogboxBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        exitDialogboxBinding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Exit");
                dialog.dismiss();
            }
        });
        exitDialogboxBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    void callNotification() {
        Notify.build(getApplicationContext())
                .setTitle("BD APP")
                .setContent("You Are LogeIn!")
                .setSmallIcon(R.drawable.app_logo)
                .setLargeIcon(R.drawable.app_logo)
                .setColor(R.color.green)
                .setAutoCancel(false)
                .show();
    }
}
