package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.response.EmployeeListResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeListBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;
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
    String getToken = SharedPref.getUserDetails().getAccessToken();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false);
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("My_Preference", MODE_PRIVATE);
//        String Token = sharedPreferences.getString("Token", "ok");
//        showToast(Token);
//        employeeListApi();
//        list.add(new EmployeeListResponse("Joe", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListResponse("Smith", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListResponse("Josh Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListResponse("Michel Zor", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListResponse("Alex War", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListResponse("Lyon Pine", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListResponse("Roy Meel", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListResponse("Carey C ", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListResponse("Mil Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListResponse("Veexo Zor", "Project Manager", R.drawable.sample_prohject));
//        list.add(new EmployeeListResponse("Alex War", "IOS Developer", R.drawable.sample_ios));
//        employeeListApi();
        EmAdapter = new EmployeeListAdapter(mContext, employeeList, "EmployeeList");
        binding.employeeRecycler.setHasFixedSize(false);
        binding.employeeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.employeeRecycler.setAdapter(EmAdapter);
        return binding.getRoot();
    }

    private void employeeListApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.employeeList(mContext, "Bearer " + getToken).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmployeeList>>() {}.getType();
                    List<EmployeeList> list = new Gson().fromJson(apiResponse.getData(), collectionType);
                    employeeList.addAll(list);
                    EmAdapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
                }
            }
        });
        AppLoader.hideLoaderDialog();
    }
}