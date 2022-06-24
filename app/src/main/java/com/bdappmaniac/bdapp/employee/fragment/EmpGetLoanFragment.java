package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmpGetLoanBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;


public class EmpGetLoanFragment extends BaseFragment {
FragmentEmpGetLoanBinding binding;
    int price = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater,R.layout.fragment_emp_get_loan,container,false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
     binding.backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) { takeMeHome();
         }
     });

     binding.applybtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Navigation.findNavController(v).navigate(R.id.FragmentGlige);
         }
     });
        binding.priceRange.setProgress(20000);
        binding.priceRange.setRange(1,50000);
        binding.priceRange.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                String[] lValue = String.valueOf(leftValue).split("\\.");
                price = Integer.parseInt(lValue[0]);
                binding.priceRange.getLeftSeekBar().setIndicatorText(lValue[0]+ " INR");

            }
            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
            }
        });


        binding.timeRange.setProgress(12);
        binding.timeRange.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                String[] tValue = String.valueOf(leftValue).split("\\.");
                price = Integer.parseInt(tValue[0]);
                binding.timeRange.getLeftSeekBar().setIndicatorText(tValue[0]+ " M");

            }
            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
            }
        });
    return binding.getRoot();
    }
}