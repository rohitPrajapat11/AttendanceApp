package com.bdappmaniac.bdapp.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.AuthActivity;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentHomeSettingBinding;
import com.bdappmaniac.bdapp.databinding.LogoutDialogboxBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class AdminSettingFragment extends BaseFragment {
    FragmentHomeSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_setting, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.notificationBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.notificationFragment);
        });
        binding.designationBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.designationFragment);
        });
        binding.attendanceRulesBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.attendanceRulesFragment);
        });
        binding.employmentTermsBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.adminTermFragment);
        });
        binding.helpAndSupportBtn.setOnClickListener(v -> {
            showToast(mContext.getString(R.string.in_progress));
        });
        binding.logOutBtn.setOnClickListener(v -> {
            logoutDialog();
        });
        return binding.getRoot();
    }

    void logoutDialog() {
        LogoutDialogboxBinding logoutDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.logout_dialogbox, null, false);
        Dialog dialog = new Dialog(mContext);
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
                startActivity(new Intent(mContext, AuthActivity.class));
                getActivity().finish();
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

    void userLogout() {
        AppLoader.showLoaderDialog(mContext);
        MainService.userLogout(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    SharedPref.init(mContext);
                    SharedPref.putUserDetails(null);
                } else {
                    ((BaseActivity) mContext).showToast(apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
    }
}