package com.bdappmaniac.bdapp.admin.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeListResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeListBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListFragment extends BaseFragment {
    FragmentEmployeeListBinding binding;
    EmployeeListAdapter EmAdapter;
    ArrayList<EmployeeListResponse> employeeLists = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("My_Preference", MODE_PRIVATE);
        String Token = sharedPreferences.getString("Token", "ok");
        showToast(Token);
        employeeListApi();
//        BdApplication.getPreferenceManger().getLoginDetails();
//        list.add(new EmployeeList("Joe", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeList("Smith", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeList("Josh Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeList("Michel Zor", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeList("Alex War", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeList("Lyon Pine", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeList("Roy Meel", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeList("Carey C ", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeList("Mil Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeList("Veexo Zor", "Project Manager", R.drawable.sample_prohject));
//        list.add(new EmployeeList("Alex War", "IOS Developer", R.drawable.sample_ios));
        EmAdapter = new EmployeeListAdapter(mContext, employeeLists, "EmployeeList");
        binding.employeeRecycler.setHasFixedSize(false);
        binding.employeeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.employeeRecycler.setAdapter(EmAdapter);
        return binding.getRoot();
    }

    private void employeeListApi() {
        MainService.employeeList(mContext, "Bearer 69|u9ooDRbOpcVZTYnGzQ1KsYrN0BQMN5jTJgUXIlPP").observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmployeeListResponse>>() {
                    }.getType();
                    List<EmployeeListResponse> list = new Gson().fromJson(apiResponse.getData(), collectionType);
                    employeeLists.addAll(list);
                    EmAdapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
                }
            }
        });
    }
}