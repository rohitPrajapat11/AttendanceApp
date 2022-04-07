package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentAttendanceRulesBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceRulesBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class EmployeeAttendanceRulesFragment extends BaseFragment {
    FragmentEmployeeAttendanceRulesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attendance_rules, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        return binding.getRoot();
    }
}