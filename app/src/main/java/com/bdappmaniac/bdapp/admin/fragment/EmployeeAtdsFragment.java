package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttandenceListBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class EmployeeAtdsFragment extends BaseFragment {
    FragmentEmployeeAttandenceListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_atds, container, false);

        return binding.getRoot();
    }
}