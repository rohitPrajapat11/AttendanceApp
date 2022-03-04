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
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeDetailsBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EmployeeDetailsFragment extends BaseFragment {
    FragmentEmployeeDetailsBinding binding;
    String empName, designation;
    int profile, ID;
    EmployeeListAdapter EmAdapter;
    ArrayList<EmployeeList> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_details, container, false);
        if (getArguments() != null) {
            profile = getArguments().getInt("image");
            empName = getArguments().getString("name");
            designation = getArguments().getString("designation");
            ID = getArguments().getInt("employeeid");
        }
        ID = 15;
        employeeDetails(ID);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.profile.setImageResource(profile);
        binding.employeeName.setText(empName);
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
            Navigation.findNavController(v).navigate(R.id.employeeAttendanceFragment);
        });

        return binding.getRoot();
    }

    public void employeeDetails(int id) {
        AppLoader.showLoaderDialog(mContext);
        MainService.getEmployeeById("Bearer 76|j734FAPUvCKnGajnPFUwOkZW9GdKfhoxf6gZS0t8", id).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    EmployeeByIdResponse employeeByIdResponse = new Gson().fromJson(apiResponse.getData(), EmployeeByIdResponse.class);
                    setUserData(employeeByIdResponse);
                } else {
                    ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
                }
            }
        });
        AppLoader.hideLoaderDialog();
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        if (getActivity() != null) {
            requestBody = toRequestBodyPart(val);
        }
        return requestBody;
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }

    public void setUserData(EmployeeByIdResponse employeeByIdResponse) {

        binding.designationTxt.setText(employeeByIdResponse.getDesignation());
        binding.emailTxt.setText(employeeByIdResponse.getEmail());
        binding.phoneTxt.setText(String.valueOf(employeeByIdResponse.getEmpMobileNo()));
        binding.emPhoneTxt.setText(String.valueOf(employeeByIdResponse.getEmgMoNo()));
        binding.addressTxt.setText(employeeByIdResponse.getEmployeeAddress());
        if (employeeByIdResponse.getStatus().equals("active")) {
            binding.switch1.setChecked(true);
            binding.activeBtn.setText("Active");
        }
        else if (employeeByIdResponse.getStatus()!="inactive") {
            binding.switch1.setChecked(false);
            binding.activeBtn.setText("Inactive");
        }

        if (employeeByIdResponse.getDob()==null){
            binding.dobTxt.setText("Null");
        }
        else {
            binding.dobTxt.setText(String.valueOf(employeeByIdResponse.getDob()));
        }
    }
}