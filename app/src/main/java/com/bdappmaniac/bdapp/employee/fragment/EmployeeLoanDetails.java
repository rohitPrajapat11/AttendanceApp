package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.ManageApprovedAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLoanDetailsBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.ProgressBarAnimation;
import com.bdappmaniac.bdapp.model.ManageApprovedModel;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;

public class EmployeeLoanDetails extends BaseFragment {
    FragmentEmployeeLoanDetailsBinding binding;
    ManageApprovedAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
              takeMeHome();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_loan_details, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);

        ProgressBarAnimation mProgressAnimation = new ProgressBarAnimation(binding.progressMain, 2500);
        mProgressAnimation.setProgress(85);

        mProgressAnimation = new ProgressBarAnimation(binding.progressTimeProgram, 3500);
        mProgressAnimation.setProgress(60);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeMeHome();
            }
        });

        binding.getLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.EmpGetLoanFragment);
            }
        });
        binding.manageAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.EmpManageLoansFragment);
            }
        });

        ArrayList<ManageApprovedModel> advancelist = new ArrayList<>();
        advancelist.add(new ManageApprovedModel("Salary", "1000", "10", "5000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Personal ", "80000", "2", "4000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Advance ", "5000", "5", "1000", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));
        advancelist.add(new ManageApprovedModel("Others", "300", "9", "3100", " public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState"));

        adapter = new ManageApprovedAdapter(advancelist, mContext);
        binding.recyclerAllLoans.setAdapter(adapter);
        return binding.getRoot();
    }
}