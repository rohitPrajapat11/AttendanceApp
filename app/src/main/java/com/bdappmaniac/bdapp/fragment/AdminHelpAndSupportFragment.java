package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHelpAndSupportBinding;

public class AdminHelpAndSupportFragment extends Fragment {
    FragmentAdminHelpAndSupportBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_help_and_support, container, false);
        binding.backBtn.setOnClickListener(view -> Navigation.findNavController(view).navigateUp());
        return binding.getRoot();
    }
}