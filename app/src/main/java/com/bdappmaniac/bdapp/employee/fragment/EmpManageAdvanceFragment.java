package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmpManageAdvanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class EmpManageAdvanceFragment extends BaseFragment {
FragmentEmpManageAdvanceBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      binding = DataBindingUtil.inflate(inflater,R.layout.fragment_emp_manage_advance,container,false);

        binding.ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               takeMeHome();
            }
        });
      return binding.getRoot();
    }
}