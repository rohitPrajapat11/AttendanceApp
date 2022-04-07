package com.bdappmaniac.bdapp.admin.fragment;

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
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeTermsAndConditionBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class EmployeeTermsAndConditionFragment extends BaseFragment {
    FragmentEmployeeTermsAndConditionBinding binding;
    int IDE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_terms_and_condition, container, false);
        if (getArguments() != null) {
            IDE = getArguments().getInt("id");
        }
        try {
            specificEmployeeTermsAndConditionsApi(IDE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.cancelBtn.setOnClickListener(v -> {
            editCondition(false);
        });
        binding.editBtn.setOnClickListener(v -> {
            editCondition(true);
        });
        binding.saveBtn.setOnClickListener(v -> {
            if (StringHelper.isEmpty(binding.conditionsTxt.getText().toString())) {
                showSnackBar(v, mContext.getString(R.string.write_term_and_conditions));
            } else {
                editCondition(false);
                String setCondition = null;
                try {
                    setCondition = URLEncoder.encode(binding.conditionsTxt.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                addEmployeeTermAndConditionsApi(setCondition, String.valueOf(IDE));
            }
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

    public void specificEmployeeTermsAndConditionsApi(int empo_id) throws UnsupportedEncodingException{
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
                    binding.conditionsTxt.setText(getCondition);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void addEmployeeTermAndConditionsApi(String rules, String  emp_id) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("rules", toRequestBody(rules));
        map.put("emp_id", toRequestBody(emp_id));
        MainService.addEmployeeTermAndConditions(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    binding.conditionsLb.setText(binding.conditionsTxt.getText().toString());
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}