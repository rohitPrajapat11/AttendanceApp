package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentHomeTermsAndConditionsBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class HomeTermsAndConditionsFragment extends BaseFragment {
    FragmentHomeTermsAndConditionsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_terms_and_conditions, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        SharedPref.init(mContext);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        return binding.getRoot();
    }

    public void specificEmployeeTermsAndConditionsApi(int empo_id) throws UnsupportedEncodingException {
        AppLoader.showLoaderDialog(mContext);
        MainService.specificEmployeeTermsAndConditions(mContext, getToken(), empo_id).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((!apiResponse.getData().isJsonNull())) {
                    String getCondition = null;
                    try {
                        getCondition = URLDecoder.decode(apiResponse.getData().getAsJsonObject().get("rules").toString().replace("\"", ""), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    binding.conditionsLb.setText(getCondition);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            specificEmployeeTermsAndConditionsApi(SharedPref.getUserDetails().getId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}