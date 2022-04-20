package com.bdappmaniac.bdapp.admin.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.bdappmaniac.bdapp.Api.response.EmployeeDesItem;
import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.response.LoginResponse;
import com.bdappmaniac.bdapp.Api.response.lockUnlockItems;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.LockUnlockAdapter;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminLockUnlockBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.model.LockUnlockModel;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;


public class AdminLockUnlock extends BaseFragment {
    FragmentAdminLockUnlockBinding binding;
    ArrayList<lockUnlockItems> employeeList = new ArrayList<>();
    LockUnlockAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_lock_unlock, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        adapter = new LockUnlockAdapter(employeeList, getContext());
        binding.recyclerlockUnlock.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerlockUnlock.setAdapter(adapter);
        return binding.getRoot();
    }


    private void allInactiveEmployeeApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.allInactiveEmployee(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<lockUnlockItems>>() {
                    }.getType();
                    List<lockUnlockItems> List = new Gson().fromJson(apiResponse.getData(), collectionType);
                    employeeList.clear();
                    employeeList.addAll(List);
                    adapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        allInactiveEmployeeApi();
    }

}