package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmpManageAdvanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class EmpManageAdvanceFragment extends BaseFragment {
FragmentEmpManageAdvanceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      binding = DataBindingUtil.inflate(inflater,R.layout.fragment_emp_manage_advance,container,false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Navigation.findNavController(view).popBackStack();
            }
        });
      return binding.getRoot();
    }
}