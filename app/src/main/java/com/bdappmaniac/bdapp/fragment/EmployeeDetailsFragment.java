package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.EmployeeByIdResponse;
import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.adapter.ViewPagerAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeDetailsBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EmployeeDetailsFragment extends BaseFragment {
    FragmentEmployeeDetailsBinding binding;
    int ID;
    EmployeeListAdapter EmAdapter;
    ArrayList<EmployeeList> list = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;

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
            binding.serviceInfoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.serviceInfoFragment);
                }
            });
            Bundle bundle = new Bundle();
            bundle.putInt("id", ID);
//            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
//            viewPagerAdapter.add(new PersonalInfoFragment(ID), "Personal Info");
//            viewPagerAdapter.add(new ContactInfoFragment(ID), "Contact");
//            viewPagerAdapter.add(new ServiceInfoFragment(ID), "Services");
//            binding.viewpager.setAdapter(viewPagerAdapter);
//            binding.tabLayout.setViewPager(binding.viewpager);
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
        binding.nameTxt.setText(employeeByIdResponse.getEmployeeName());
        Glide.with(mContext)
                .load(employeeByIdResponse.getProfile())
                .error(R.drawable.user)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.profile);
        binding.designationTxt.setText(employeeByIdResponse.getDesignation());
        binding.joiningDateTxt.setText(DateUtils.getFormattedTime(String.valueOf(employeeByIdResponse.getJoiningdate()), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        switch (employeeByIdResponse.getStatus()) {
            case "active":
                binding.switch1.setChecked(true);
                binding.statusTxt.setText("Active");
                break;
            case "inactive":
                binding.switch1.setChecked(false);
                binding.statusTxt.setText("Inactive");
                break;
        }
        if (employeeByIdResponse.getDob() == null) {
            binding.dobTxt.setText("-");
        } else {
            binding.dobTxt.setText(DateUtils.getFormattedTime(String.valueOf(employeeByIdResponse.getDob()), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        }
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.switch1.setChecked(true);
                    binding.statusTxt.setText("Active");
                    updateEmployeeStatus("active");
                } else {
                    binding.switch1.setChecked(false);
                    binding.statusTxt.setText("Inactive");
                    updateEmployeeStatus("inactive");
                }
            }
        });
        binding.emailTxt.setText(employeeByIdResponse.getEmail());
        binding.phoneTxt.setText(String.valueOf(employeeByIdResponse.getEmpMobileNo()));
        if (employeeByIdResponse.getEmployeeAddress().equals("")) {
            binding.addressTxt.setText("-");
        } else {
            binding.addressTxt.setText(employeeByIdResponse.getEmployeeAddress());
        }
        if (employeeByIdResponse.getEmgMoNo() == 0) {
            binding.otherNumTxt.setText("-");
        } else {
            binding.otherNumTxt.setText(String.valueOf(employeeByIdResponse.getEmgMoNo()));
        }
    }

    private void updateEmployeeStatus(String status) {
        AppLoader.showLoaderDialog(mContext);
        MainService.updateProfileByAdmin(mContext, getToken(), ID, status).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    if (status.equals("active")) {
                        showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    } else if (status.equals("inactive")) {
                        showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    }
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
        StatusBarUtils.statusBarColor(getActivity(), R.color.prime);
    }
}