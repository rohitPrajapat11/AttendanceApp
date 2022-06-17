package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLoanDetailsBinding;
import com.bdappmaniac.bdapp.helper.ProgressBarAnimation;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class EmployeeLoanDetails extends Fragment {
    FragmentEmployeeLoanDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_loan_details, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.f1f3f5);

        ProgressBarAnimation mProgressAnimation = new ProgressBarAnimation(binding.progressMain, 2500);
        mProgressAnimation.setProgress(85);

        mProgressAnimation = new ProgressBarAnimation(binding.progressTimeProgram, 3500);
        mProgressAnimation.setProgress(60);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
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
                Navigation.findNavController(view).navigate(R.id.EmpGetLoanFragment);
            }
        });


        return binding.getRoot();
    }
}