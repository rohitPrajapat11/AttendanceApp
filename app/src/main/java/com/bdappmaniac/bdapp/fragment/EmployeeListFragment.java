package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.response.EmployeeListResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeListBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListFragment extends BaseFragment {
    FragmentEmployeeListBinding binding;
    EmployeeListAdapter EmAdapter;
    List<EmployeeListResponse> list = new ArrayList<>();
    List<EmployeeList> employeeList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false);
            EmAdapter = new EmployeeListAdapter(mContext, employeeList, "EmployeeList");
            binding.employeeRecycler.setHasFixedSize(false);
            binding.employeeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.employeeRecycler.setAdapter(EmAdapter);
        }
        return binding.getRoot();
    }

    private void employeeListApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.employeeList(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmployeeList>>() {
                    }.getType();
                    List<EmployeeList> list = new Gson().fromJson(apiResponse.getData(), collectionType);
                    employeeList.clear();
                    employeeList.addAll(list);
                    EmAdapter.notifyDataSetChanged();
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
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        if (employeeList != null) {
            if (employeeList.size() == 0) {
                employeeListApi();
            }
        }

    }
}