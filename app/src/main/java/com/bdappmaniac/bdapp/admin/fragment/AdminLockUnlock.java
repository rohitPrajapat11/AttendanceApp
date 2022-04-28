package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.lockUnlockItems;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.LockUnlockAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminLockUnlockBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


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
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
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