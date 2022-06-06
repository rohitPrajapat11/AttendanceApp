package com.bdappmaniac.bdapp.employee.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.AdminActivity;
import com.bdappmaniac.bdapp.activity.AuthActivity;
import com.bdappmaniac.bdapp.activity.HomeActivity;
import com.bdappmaniac.bdapp.activity.SplashActivity;
import com.bdappmaniac.bdapp.adapter.AllLoanAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLoanDetailsBinding;
import com.bdappmaniac.bdapp.helper.ProgressBarAnimation;
import com.bdappmaniac.bdapp.model.ModelAllLoans;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;

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


        ArrayList<ModelAllLoans> loanlist = new ArrayList<>();
        loanlist.add(new ModelAllLoans("Going on a World Tour","Annual Leave","20-04-22",
                "20-05-22","i will be absent today due to some personal urgent work poped up all of a sudden"));
        loanlist.add(new ModelAllLoans("Not Feeling well Today","Sick Leave","20-04-22",
                "20-05-22","i will be absent today due to some personal urgent work poped up all of a sudden"));
        loanlist.add(new ModelAllLoans("Urgent pice of Work","Casual Leave","20-04-22",
                "20-05-22","i will be absent today due to some personal urgent work poped up all of a sudden"));
        loanlist.add(new ModelAllLoans("Going on a World Tour","Annual Leave","20-04-22",
                "20-05-22","i will be absent today due to some personal urgent work poped up all of a sudden"));
        loanlist.add(new ModelAllLoans("Tour","casual Leave","20-04-22",
                "20-05-22","i will be absent today due to some personal urgent work poped up all of a sudden"));


        binding.getLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.EmpGetLoanFragment);
            }
        });

        AllLoanAdapter adapter = new AllLoanAdapter(loanlist,getContext());
        binding.recyclerAllLoans.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAllLoans.setAdapter(adapter);
        return binding.getRoot();
    }
}