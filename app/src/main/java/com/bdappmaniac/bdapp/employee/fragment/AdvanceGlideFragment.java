package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmpManageAdvanceBinding;

import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class AdvanceGlideFragment extends BaseFragment {
FragmentEmpManageAdvanceBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.frgment_advance_glide,container,false);



        return binding.getRoot();
    }
}