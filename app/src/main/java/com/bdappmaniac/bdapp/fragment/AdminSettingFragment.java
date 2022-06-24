package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentHomeSettingBinding;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class AdminSettingFragment extends BaseFragment {
    FragmentHomeSettingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_setting, container, false);
        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
        binding.notificationBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.notificationFragment));
        binding.designationBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.designationFragment));
        binding.attendanceRulesBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.attendanceRulesFragment));
        binding.employmentTermsBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.adminTermFragment));
        binding.helpAndSupportBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.adminHelpAndSupportFragment));
        binding.logOutBtn.setOnClickListener(v -> ((BaseActivity) mContext).logoutDialog());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(requireActivity(), R.color.white);
    }
}