package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentSettingBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class SettingFragment extends BaseFragment {
    FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.accountBtn.setOnClickListener(v -> {
            showToast(mContext.getString(R.string.in_progress));
        });
        binding.notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(mContext.getString(R.string.in_progress));
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