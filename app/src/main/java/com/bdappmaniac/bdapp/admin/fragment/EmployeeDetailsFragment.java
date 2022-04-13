package com.bdappmaniac.bdapp.admin.fragment;

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
            binding.attendanceLoanBtn.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("id", ID);
                Navigation.findNavController(v).navigate(R.id.employeeAttendanceFragment,bundle);
            });
            binding.provideLoanBtn.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.provideLoanFragment);
            });
            binding.tmcBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", ID);
                    Navigation.findNavController(v).navigate(R.id.employeeTermsAndConditionFragment, bundle);
                }
            });
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
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void setUserData(EmployeeByIdResponse employeeByIdResponse) {
        binding.designationTxt.setText(employeeByIdResponse.getDesignation());
        binding.emailTxt.setText(employeeByIdResponse.getEmail());
        binding.phoneTxt.setText(String.valueOf(employeeByIdResponse.getEmpMobileNo()));
        binding.emPhoneTxt.setText(String.valueOf(employeeByIdResponse.getEmgMoNo()));
        binding.addressTxt.setText(employeeByIdResponse.getEmployeeAddress());
        binding.employeeName.setText(employeeByIdResponse.getEmployeeName());
        binding.joiningDateTxt.setText(String.valueOf(employeeByIdResponse.getJoiningdate()));

        Glide.with(mContext)
                .load(employeeByIdResponse.getProfile())
                .error(R.drawable.user)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.profile);

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
            binding.dobTxt.setText(String.valueOf(employeeByIdResponse.getDob()));
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
                        showSnackBar(binding.getRoot(), mContext.getString(R.string.you_are_active));
                    } else if (status.equals("inactive")) {
                        showSnackBar(binding.getRoot(), mContext.getString(R.string.you_are_inactive));
                    }
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}