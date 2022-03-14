package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLoanDetailsBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLoanDetailsBindingImpl;

public class EmployeeLoanDetails extends Fragment {
    FragmentEmployeeLoanDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_loan_details, container, false);

        return binding.getRoot();
    }
}