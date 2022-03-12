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

public class SettingFragment extends BaseFragment {
    FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.accountBtn.setOnClickListener(v -> {
            showToast("In Progress");
        });
        binding.notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("In Progress");
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