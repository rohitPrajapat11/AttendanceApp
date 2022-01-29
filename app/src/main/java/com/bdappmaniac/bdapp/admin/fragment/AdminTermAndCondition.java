package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentAdminTermAndConditionBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StringHelper;

public class AdminTermAndCondition extends BaseFragment {
    FragmentAdminTermAndConditionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_term_and_condition, container, false);
        binding.cancelBtn.setOnClickListener(v -> {
            editCondition(false);
        });
        binding.editBtn.setOnClickListener(v -> {
            editCondition(true);
        });
        binding.saveBtn.setOnClickListener(v -> {
            if (StringHelper.isEmpty(binding.conditionsTxt.getText().toString())) {
                showSnackBar(v, "Please Write Term and Condition");
            } else {
                editCondition(false);
            }
        });
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        return binding.getRoot();
    }

    void editCondition(boolean check) {
        if (check) {
            binding.editBtn.setVisibility(View.GONE);
            binding.cancelBtn.setVisibility(View.VISIBLE);
            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.conditionsTxt.setVisibility(View.VISIBLE);
            binding.conditionsLb.setVisibility(View.GONE);
        } else {
            binding.editBtn.setVisibility(View.VISIBLE);
            binding.cancelBtn.setVisibility(View.GONE);
            binding.saveBtn.setVisibility(View.GONE);
            binding.conditionsTxt.setVisibility(View.GONE);
            binding.conditionsLb.setVisibility(View.VISIBLE);
        }
    }
}