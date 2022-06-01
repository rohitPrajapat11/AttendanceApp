package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmpLeavesBinding;


public class EmpLeavesFragment extends Fragment {
FragmentEmpLeavesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     FragmentEmpLeavesBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_emp_leaves,container,false);

     return binding.getRoot();

    }
}