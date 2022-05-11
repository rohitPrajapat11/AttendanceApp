package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bdappmaniac.bdapp.Api.response.EmployeeByIdResponse;
import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentContactInfoBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ContactInfoFragment extends BaseFragment {
    FragmentContactInfoBinding binding;
    ArrayList<EmployeeList> list = new ArrayList<>();
    int ID;

    public ContactInfoFragment(int id ) {
        ID = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_info, container, false);
            if (getArguments() != null) {
                ID = getArguments().getInt("id");
            }
            employeeDetails(ID);
        }
        return binding.getRoot();
    }

    public void employeeDetails(int id) {
        AppLoader.showLoaderDialog(mContext);
        MainService.getEmployeeById(mContext, getToken(), id).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    EmployeeByIdResponse employeeByIdResponse = new Gson().fromJson(apiResponse.getData(), EmployeeByIdResponse.class);
                    setUserData(employeeByIdResponse);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void setUserData(EmployeeByIdResponse employeeByIdResponse) {
        binding.emailTxt.setText(employeeByIdResponse.getEmail());
        binding.phoneTxt.setText(String.valueOf(employeeByIdResponse.getEmpMobileNo()));
        if (employeeByIdResponse.getEmployeeAddress().equals("")) {
            binding.addressTxt.setText("-");
        } else {
            binding.addressTxt.setText(employeeByIdResponse.getEmployeeAddress());
        }
        if (employeeByIdResponse.getEmgMoNo() == 0) {
            binding.emPhoneTxt.setText("-");
        } else {
            binding.emPhoneTxt.setText(String.valueOf(employeeByIdResponse.getEmgMoNo()));
        }
    }
}