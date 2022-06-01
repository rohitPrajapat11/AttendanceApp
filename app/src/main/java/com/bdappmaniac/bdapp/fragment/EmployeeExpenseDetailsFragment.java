package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeExpenseDetailsBinding;

public class EmployeeExpenseDetailsFragment extends Fragment {
    FragmentEmployeeExpenseDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_expense_details, container, false);
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
        });
        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.mainLayout.getVisibility() == View.VISIBLE) {
                    binding.mainLayout.setVisibility(View.GONE);
                } else {
                    binding.mainLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        return binding.getRoot();
    }
}