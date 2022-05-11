package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentServiceInfoBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class ServiceInfoFragment extends BaseFragment {
    FragmentServiceInfoBinding binding;
    int ID;

//    public ServiceInfoFragment(int id) {
//        ID = id;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_info, container, false);
            if (getArguments() != null) {
                ID = getArguments().getInt("id");
            }
            binding.backBtn.setOnClickListener(v -> {
                Navigation.findNavController(v).navigateUp();
            });
            binding.salaryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.salaryFragment);
                }
            });
            binding.attendanceLoanBtn.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("id", ID);
                Navigation.findNavController(v).navigate(R.id.employeeAttendanceFragment, bundle);
            });
            binding.provideLoanBtn.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.provideLoanFragment);
            });
            binding.tmcBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", ID);
                    Navigation.findNavController(v).navigate(R.id.employeeTermsAndConditionFragment, bundle);
                }
            });
        }
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.f1f3f5);
    }
}