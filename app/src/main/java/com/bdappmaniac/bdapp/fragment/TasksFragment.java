package com.bdappmaniac.bdapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeDesItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeDesListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeDesListBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends BaseFragment {
    FragmentEmployeeDesListBinding binding;
    ArrayList<EmployeeDesItem> employeeList = new ArrayList<>();
    EmployeeDesListAdapter adapter;
    int selectedView = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_des_list, container, false);
            binding.backBtn.setOnClickListener(view -> Navigation.findNavController(view).navigateUp());
            adapter = new EmployeeDesListAdapter(employeeList, getContext(), selectedView);
            binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycleView.setAdapter(adapter);
            binding.grid.setOnClickListener(view -> {
                binding.grid.setVisibility(View.GONE);
                binding.list.setVisibility(View.VISIBLE);
                if (selectedView == 0) {
                    selectedView = 1;
                    adapter = new EmployeeDesListAdapter(employeeList, getContext(), 1);
                    binding.recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    binding.recycleView.setAdapter(adapter);
                }
            });

            binding.list.setOnClickListener(view -> {
                binding.grid.setVisibility(View.VISIBLE);
                binding.list.setVisibility(View.GONE);
                selectedView = 0;
                adapter = new EmployeeDesListAdapter(employeeList, getContext(), 0);
                binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recycleView.setAdapter(adapter);
            });
        }
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void allEmpAttendanceApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.allEmpAttendance(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmployeeDesItem>>() {
                    }.getType();
                    List<EmployeeDesItem> List = new Gson().fromJson(apiResponse.getData(), collectionType);
                    employeeList.clear();
                    employeeList.addAll(List);
                    adapter.notifyDataSetChanged();
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
        if (employeeList.isEmpty()) {
            allEmpAttendanceApi();
            AppLoader.hideLoaderDialog();
        }
    }
}