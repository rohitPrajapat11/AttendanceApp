package com.bdappmaniac.bdapp.admin.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.admin.adapter.AllLoanAdapter;
import com.bdappmaniac.bdapp.admin.adapter.EmployeeListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminLoanBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.model.AllLoanModel;
import com.bdappmaniac.bdapp.utils.Constant;

import java.util.ArrayList;

public class AdminLoanFragment extends BaseFragment implements CalendarCallBack {
    FragmentAdminLoanBinding binding;
    ArrayList<AllLoanModel> loanStatus = new ArrayList<>();
    EmployeeListAdapter EmAdapter;
    AllLoanAdapter allLoanAdapter;
    Dialog addEmployeeDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_loan, container, false);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        Constant.calendarCallBack = this;
        binding.totalTxt.setText(getResources().getString(R.string.inr) + " " + "50,000");
        //  binding.getAmountTxt.setText(getResources().getString(R.string.inr) + " " + "20,000");
        binding.pendingAmountTxt.setText(getResources().getString(R.string.inr) + " " + "30,000");
        binding.returnAmountTxt.setText(getResources().getString(R.string.inr) + " " + "20,000");
        loanStatus.add(new AllLoanModel("Joe", "12-01-2020", "10,000"));
        loanStatus.add(new AllLoanModel("Alex War", "01-07-2019", "5,000"));
        loanStatus.add(new AllLoanModel("Michel Zor", "11-06-2021", "15,000"));
        loanStatus.add(new AllLoanModel("Lyon Pine", "10-10-2021", "5,000"));
        loanStatus.add(new AllLoanModel("Smith", "18-07-2020", "8,000"));
        loanStatus.add(new AllLoanModel("Mil Dev", "06-05-2021", "7,000"));
        allLoanAdapter = new AllLoanAdapter(getContext(), loanStatus);
        binding.historyRecycler.setAdapter(allLoanAdapter);
        return binding.getRoot();
    }

//    public void addNewLoadDialog() {
//        SingleEmployeeListItemBinding employeeBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.single_employee_list_item, null, false);
//        addEmployeeDialog = new Dialog(mContext);
//        addEmployeeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        addEmployeeDialog.setContentView(employeeBinding.getRoot());
//        addEmployeeDialog.setCancelable(true);
//        addEmployeeDialog.setCanceledOnTouchOutside(false);
//        addEmployeeDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        addEmployeeDialog.getWindow().setGravity(Gravity.CENTER);
//        addEmployeeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        ArrayList<EmployeeListModel> list = new ArrayList<>();
//        list.add(new EmployeeListModel("Joe", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListModel("Smith", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListModel("Josh Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListModel("Michel Zor", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListModel("Alex War", "Android Developer", R.drawable.sample_android));
//        list.add(new EmployeeListModel("Lyon Pine", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListModel("Roy Meel", "IOS Developer", R.drawable.sample_ios));
//        list.add(new EmployeeListModel("Carey C ", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListModel("Mil Dev", "Web Developer", R.drawable.sample_web));
//        list.add(new EmployeeListModel("Veexo Zor", "Project Manager", R.drawable.sample_prohject));
//        list.add(new EmployeeListModel("Alex War", "IOS Developer", R.drawable.sample_ios));
//        SingleListEmployeeAdapter adapter = new SingleListEmployeeAdapter(mContext, list);
//        employeeBinding.employeeList.setLayoutManager(new LinearLayoutManager(getContext()));
//        employeeBinding.employeeList.setAdapter(adapter);
//        addEmployeeDialog.show();
//        employeeBinding.backBtn.setOnClickListener(v -> {
//            addEmployeeDialog.dismiss();
//        });
//    }

    @Override
    public void openEmployeeList() {
//        addNewLoadDialog();
        Navigation.findNavController(binding.getRoot()).navigate(R.id.employeeListForLoanFragment);

    }
}