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
import com.bdappmaniac.bdapp.databinding.FragmentAttendanceRulesBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class AttendanceRulesFragment extends BaseFragment {
    FragmentAttendanceRulesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_attendance_rules, container, false);
        binding.cancelBtn.setOnClickListener(v -> {
            editCondition(false);
        });
        binding.editBtn.setOnClickListener(v -> {
            editCondition(true);
        });
        binding.saveBtn.setOnClickListener(v -> {
            if (StringHelper.isEmpty(binding.rulesTxt.getText().toString())) {
                showSnackBar(v, mContext.getString(R.string.write_term_and_conditions));
            } else {
                editCondition(false);
                String setCondition = null;
                try {
                    setCondition = URLEncoder.encode(binding.rulesTxt.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                addDailyRulesApi(setCondition);
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
            binding.editLayout.setVisibility(View.VISIBLE);
            binding.rulesTxt.setVisibility(View.VISIBLE);
            binding.rulesLb.setVisibility(View.GONE);
        } else {
            binding.editBtn.setVisibility(View.VISIBLE);
            binding.editLayout.setVisibility(View.GONE);
            binding.rulesTxt.setVisibility(View.GONE);
            binding.rulesLb.setVisibility(View.VISIBLE);
        }
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
                        getCondition = URLDecoder.decode(apiResponse.getData().getAsJsonArray().get(0).getAsJsonObject().get("content").toString().replace("\"", ""), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    binding.rulesLb.setText(getCondition);
                    binding.rulesTxt.setText(getCondition);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void addDailyRulesApi(String content) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("content", toRequestBody(content));
        MainService.addDailyRules(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    binding.rulesLb.setText(binding.rulesTxt.getText().toString());
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
        try {
            allDailyRulesApi();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}