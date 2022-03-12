package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentHomeSettingBinding;
import com.bdappmaniac.bdapp.utils.SharedPref;

public class HomeSettingFragment extends BaseFragment {
    FragmentHomeSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_setting, container, false);

        boolean notificationIsEnable = SharedPref.read("notification", false);
        binding.notifyBar.setChecked(notificationIsEnable);

        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.accountBtn.setOnClickListener(v -> {
            showToast("In Progress");
        });
        binding.notifyBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if (b) {
                    SharedPref.write("notification", true);
                    binding.notifyBar.setChecked(true);
                    showSnackBar(binding.getRoot(), "Your Notification Is Turned On");
                    ((BaseActivity) mContext).startService();
                } else {
                    SharedPref.write("notification", false);
                    binding.notifyBar.setChecked(false);
                    showSnackBar(binding.getRoot(), "Your Notification Is Turned Off");
                    ((BaseActivity) mContext).stopService();
                }
            }
        });

        binding.privacyBtn.setOnClickListener(v -> {
            showToast("In Progress");
        });
        binding.helpBtn.setOnClickListener(v -> {
            showToast("In Progress");
        });
        return binding.getRoot();
    }
}