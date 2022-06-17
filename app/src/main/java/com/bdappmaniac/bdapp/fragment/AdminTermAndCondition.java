package com.bdappmaniac.bdapp.fragment;

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
import com.bdappmaniac.bdapp.databinding.FragmentAdminTermAndConditionBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class AdminTermAndCondition extends BaseFragment {
    FragmentAdminTermAndConditionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_term_and_condition, container, false);
        String content = binding.conditionsLb.getText().toString();
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
                addTermsAndConditionsApi(setCondition);
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
            binding.conditionsTxt.setVisibility(View.VISIBLE);
            binding.conditionsLb.setVisibility(View.GONE);
            binding.editLayout.setVisibility(View.VISIBLE);
        } else {
            binding.editBtn.setVisibility(View.VISIBLE);
            binding.conditionsTxt.setVisibility(View.GONE);
            binding.conditionsLb.setVisibility(View.VISIBLE);
            binding.editLayout.setVisibility(View.GONE);
        }
    }

    private void allTermsAndConditionsApi() throws UnsupportedEncodingException {
        AppLoader.showLoaderDialog(mContext);
        MainService.allTermsAndConditions(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((!apiResponse.getData().isJsonNull())) {
                    String getCondition = null;
                    try {
                        getCondition = URLDecoder.decode(apiResponse.getData().getAsJsonArray().get(0).getAsJsonObject().get("content1").toString().replace("\"", ""), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    binding.conditionsLb.setText(getCondition);
                    binding.conditionsTxt.setText(getCondition);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(),apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void addTermsAndConditionsApi(String content) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("content1", toRequestBody(content));
        MainService.addTermsAndConditions(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    binding.conditionsLb.setText(binding.conditionsTxt.getText().toString());
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(),apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        try {
            allTermsAndConditionsApi();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}