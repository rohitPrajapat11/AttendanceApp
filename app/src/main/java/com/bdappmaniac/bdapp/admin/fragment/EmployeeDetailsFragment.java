package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeDetailsBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

import java.util.ArrayList;

public class EmployeeDetailsFragment extends BaseFragment {
    FragmentEmployeeDetailsBinding binding;
    String empName, designation;
    int profile;
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
        }
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
}