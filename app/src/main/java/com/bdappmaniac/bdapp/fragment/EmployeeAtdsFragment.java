package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttandenceListBinding;

public class EmployeeAtdsFragment extends BaseFragment {
    FragmentEmployeeAttandenceListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_atds, container, false);
        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).popBackStack());
        return binding.getRoot();
    }
}