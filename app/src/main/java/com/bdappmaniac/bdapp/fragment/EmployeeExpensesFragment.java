package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeExpensesBinding;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;


public class EmployeeExpensesFragment extends BaseFragment {
    FragmentEmployeeExpensesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_expenses, container, false);
        binding.backBtn.setOnClickListener(view -> Navigation.findNavController(view).navigateUp());
        binding.item.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.employeeExpenseDetailsFragment));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(requireActivity(), R.color.white);
    }
}