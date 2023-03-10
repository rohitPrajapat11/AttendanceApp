package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentAttendanceRulesBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceRulesBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class EmployeeAttendanceRulesFragment extends BaseFragment {
    FragmentEmployeeAttendanceRulesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attendance_rules, container, false);
        try {
            allDailyRulesApi();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        return binding.getRoot();
    }
    private void allDailyRulesApi() throws UnsupportedEncodingException {
        AppLoader.showLoaderDialog(mContext);
        MainService.allDailyRules(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((!apiResponse.getData().isJsonNull())) {
                    String getCondition = null;
                    try {
                        getCondition = URLDecoder.decode(apiResponse.getData().getAsJsonArray().get(0).getAsJsonObject().get("content").toString().replace("\"", ""), "UTf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    binding.rulesLb.setText(getCondition);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}