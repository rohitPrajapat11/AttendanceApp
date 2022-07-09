package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.ManageApprovedAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmpManageAdvanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ManageApprovedModel;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;

public class EmpManageAdvanceFragment extends BaseFragment {
    FragmentEmpManageAdvanceBinding binding;
    ManageApprovedAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_manage_advance, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        ArrayList<ManageApprovedModel> advancelist = new ArrayList<>();
        advancelist.add(new ManageApprovedModel("Salary Advance", "10000", "10", "1000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Personal Loan", "80000", "2", "40000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Advance ", "50000", "5", "10000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Others", "30000", "9", "3100", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));

        adapter = new ManageApprovedAdapter(advancelist, mContext);
        binding.approvedRecycler.setAdapter(adapter);

        adapter = new ManageApprovedAdapter(advancelist, mContext);
        binding.pendingRecycler.setAdapter(adapter);

        adapter = new ManageApprovedAdapter(advancelist, mContext);
        binding.rejectedRecycler.setAdapter(adapter);

        return binding.getRoot();
    }
}