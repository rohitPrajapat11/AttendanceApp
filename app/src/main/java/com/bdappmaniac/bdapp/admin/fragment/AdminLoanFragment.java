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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AdminLoanFragment extends BaseFragment implements CalendarCallBack {
    FragmentAdminLoanBinding binding;
    ArrayList<AllLoanModel> loanStatus = new ArrayList<>();
    EmployeeListAdapter EmAdapter;
    AllLoanAdapter allLoanAdapter;
    Dialog addEmployeeDialog;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_loan, container, false);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        binding.ivCalendarNext.setOnClickListener(v -> {
            cal.add(Calendar.YEAR, 1);
            setUpCalendar();
        });

        binding.ivCalendarPrevious.setOnClickListener(v -> {
            cal.add(Calendar.YEAR, -1);
            setUpCalendar();
        });
        Constant.calendarCallBack = this;
        binding.totalTxt.setText(getResources().getString(R.string.inr) + " " + "50,000");
//          binding.getAmountTxt.setText(getResources().getString(R.string.inr) + " " + "20,000");
        binding.pendingAmountTxt.setText(getResources().getString(R.string.inr)+"30,000");
        binding.returnAmountTxt.setText(getResources().getString(R.string.inr)+"20,000");
        loanStatus.add(new AllLoanModel("Jan", "10-01-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Feb", "10-02-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Mar", "10-03-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Apr", "10-04-2021", "2,000"));
        loanStatus.add(new AllLoanModel("May", "10-05-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Jun", "10-06-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Jul", "10-07-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Aug", "10-08-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Sep", "10-09-2021", "2,000"));
        loanStatus.add(new AllLoanModel("Oct", "10-10-2021", "2,000"));
        allLoanAdapter = new AllLoanAdapter(getContext(), loanStatus);
        binding.historyRecycler.setAdapter(allLoanAdapter);
        return binding.getRoot();
    }

    private void setUpCalendar() {
        String currentString = sdf.format(cal.getTime());
        String[] separated = currentString.split(" ");
        binding.yearTxt.setText(separated[1]);
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