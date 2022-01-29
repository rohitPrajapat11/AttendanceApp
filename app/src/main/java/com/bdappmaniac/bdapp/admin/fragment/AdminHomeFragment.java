package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHomeBinding;


public class AdminHomeFragment extends Fragment {
    FragmentAdminHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_home, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setProgress();
            }
        }, 1000);

        binding.exploreTxt.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.loanFragment);
        });
        return binding.getRoot();
    }

    public void setProgress() {
        binding.semiCircleArcProgressBar.setPercentWithAnimation(45);

    }

}