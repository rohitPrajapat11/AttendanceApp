package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeExpensesBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;


public class EmployeeExpensesFragment extends BaseFragment {
    FragmentEmployeeExpensesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_expenses, container, false);
        return binding.getRoot();
    }
}