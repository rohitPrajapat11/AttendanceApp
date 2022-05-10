package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bdappmaniac.bdapp.Api.response.EmployeeByIdResponse;
import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentPersonalInfoBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PersonalInfoFragment extends BaseFragment {
    FragmentPersonalInfoBinding binding;
    ArrayList<EmployeeList> list = new ArrayList<>();
    int ID;

    public PersonalInfoFragment(int id) {
        ID = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_info, container, false);
        if (getArguments() != null) {
            ID = getArguments().getInt("id");
        }
        employeeDetails(ID);
//        binding.empStatus.setOnClickListener(v -> {
//            AcviteDeactiveDialogBinding activeBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.acvite_deactive_dialog, null, false);
//            Dialog dialog = new Dialog(mContext);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(activeBinding.getRoot());
//            dialog.setCancelable(true);
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.getWindow().setGravity(Gravity.BOTTOM);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            if (binding.empStatus.getText().toString().equals("Activate")) {
//                activeBinding.message.setText("Deactivate " + empName);
//            } else {
//                activeBinding.message.setText("Activate " + empName);
//            }
//            activeBinding.okBtn.setOnClickListener(v1 ->
//            {
//                if (binding.empStatus.getText().toString().equals("Activate")) {
//                    binding.empStatus.setText("Deactivate");
//                    binding.empStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icn_deactive, 0, 0, 0);
//                } else {
//                    binding.empStatus.setText("Activate");
//                    binding.empStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icn_live, 0, 0, 0);
//                }
//                dialog.dismiss();
//            });
//            activeBinding.cancelBtn.setOnClickListener(v1 ->
//            {
//                dialog.dismiss();
//            });
//            dialog.show();
//        });
        return binding.getRoot();
    }

    public void setUserData(EmployeeByIdResponse employeeByIdResponse) {
        binding.designationTxt.setText(employeeByIdResponse.getDesignation());
        binding.joiningDateTxt.setText(DateUtils.getFormattedTime(String.valueOf(employeeByIdResponse.getJoiningdate()), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        switch (employeeByIdResponse.getStatus()) {
            case "active":
                binding.switch1.setChecked(true);
                binding.activeBtn.setText("Active");
                break;
            case "inactive":
                binding.switch1.setChecked(false);
                binding.activeBtn.setText("Inactive");
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
                    binding.activeBtn.setText("Active");
                    updateEmployeeStatus("active");
                } else {
                    binding.switch1.setChecked(false);
                    binding.activeBtn.setText("Inactive");
                    updateEmployeeStatus("inactive");
                }
            }
        });
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
}