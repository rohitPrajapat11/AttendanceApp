package com.bdappmaniac.bdapp.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.NOTIFICATION;

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

        boolean notificationIsEnable = SharedPref.read(NOTIFICATION, false);
        binding.notifyBar.setChecked(notificationIsEnable);

        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.accountBtn.setOnClickListener(v -> {
            showToast(mContext.getString(R.string.in_progress));
        });
        binding.notifyBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if (b) {
                    SharedPref.write(NOTIFICATION, true);
                    binding.notifyBar.setChecked(true);
                    showSnackBar(binding.getRoot(), mContext.getString(R.string.notification_turned_on));
                    ((BaseActivity) mContext).startService();
                } else {
                    SharedPref.write(NOTIFICATION, false);
                    binding.notifyBar.setChecked(false);
                    showSnackBar(binding.getRoot(), mContext.getString(R.string.notification_turned_off));
                    ((BaseActivity) mContext).stopService();
                }
            }
        });

        binding.privacyBtn.setOnClickListener(v -> {
            showToast(mContext.getString(R.string.in_progress));
        });
        binding.helpBtn.setOnClickListener(v -> {
            showToast(mContext.getString(R.string.in_progress));
        });
        return binding.getRoot();
    }
}