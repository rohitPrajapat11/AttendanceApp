package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeHolidayResponse;
import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeHolidayAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeHolidayBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayFragment extends BaseFragment {
    FragmentEmployeeHolidayBinding binding;
    EmployeeHolidayAdapter monthAdapter;
    List<HolidaysItem> list = new ArrayList<>();
    List<EmployeeHolidayResponse> monthList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_holiday, container, false);
            monthAdapter = new EmployeeHolidayAdapter(mContext, list);
            binding.employeeHolidayRecyclers.setHasFixedSize(false);
            binding.employeeHolidayRecyclers.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.employeeHolidayRecyclers.setAdapter(monthAdapter);

            holidaysOfCurrentYearApi();
        }
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
        binding.addBtn.setVisibility(View.GONE);
        return binding.getRoot();
    }

    public void holidaysOfCurrentYearApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.holidaysOfCurrentYear(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<HolidaysItem>>() {}.getType();
                    List<HolidaysItem> monthList = new Gson().fromJson(apiResponse.getData(), collectionType);
                    list.clear();
                    list.addAll(monthList);
                    monthAdapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}