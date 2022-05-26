package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.EmployeeByIdResponse;
import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.ViewPagerAdapter;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeDetailsBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EmployeeDetailsFragment extends BaseFragment {
    FragmentEmployeeDetailsBinding binding;
    int ID;
    EmployeeListAdapter EmAdapter;
    ArrayList<EmployeeList> list = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_details, container, false);
            if (getArguments() != null) {
                ID = getArguments().getInt("id");
            }
            employeeDetails(ID);
            binding.backBtn.setOnClickListener(v -> {
                Navigation.findNavController(v).navigateUp();
            });

            Bundle bundle = new Bundle();
            bundle.putInt("id", ID);
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            viewPagerAdapter.add(new PersonalInfoFragment(ID), "Personal Info");
            viewPagerAdapter.add(new ContactInfoFragment(ID), "Contact");
            viewPagerAdapter.add(new ServiceInfoFragment(ID), "Services");
            binding.viewpager.setAdapter(viewPagerAdapter);
            binding.tabLayout.setViewPager(binding.viewpager);


        }
        return binding.getRoot();
    }

    public void employeeDetails(int id) {
        AppLoader.showLoaderDialog(mContext);
        MainService.getEmployeeById(mContext, getToken(), id).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    EmployeeByIdResponse employeeByIdResponse = new Gson().fromJson(apiResponse.getData(), EmployeeByIdResponse.class);
                    setUserData(employeeByIdResponse);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
    public void setUserData(EmployeeByIdResponse employeeByIdResponse) {
        binding.employeeName.setText(employeeByIdResponse.getEmployeeName());
        Glide.with(mContext)
                .load(employeeByIdResponse.getProfile())
                .error(R.drawable.user)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.profile);
    }
}