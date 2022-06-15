package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentApproveLeavesBinding;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class ApproveLeavesFragment extends Fragment {
    FragmentApproveLeavesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_approve_leaves, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
    }
}