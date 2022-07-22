package com.bdappmaniac.bdapp.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmpHolidaysItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeHolidayAdapter;
import com.bdappmaniac.bdapp.databinding.AdminAddholidayDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHolidayBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class AdminHolidayFragment extends BaseFragment {
    public String monthName;
    FragmentAdminHolidayBinding binding;
    String dates;
    EmployeeHolidayAdapter monthAdapter;
    List<EmpHolidaysItem> list = new ArrayList<>();

    private int TYear, TMonth, TDay;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_holiday, container, false);
            monthAdapter = new EmployeeHolidayAdapter(mContext, list);
            binding.employeeHolidayRecyclers.setHasFixedSize(false);
            binding.employeeHolidayRecyclers.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.employeeHolidayRecyclers.setAdapter(monthAdapter);
        }
        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).popBackStack());
        binding.addBtn.setOnClickListener(view -> addHolidayDialogBox());
        return binding.getRoot();
    }

    private void addHolidaysApi(String name, String date) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("name", toRequestBody(name));
        map.put("date", toRequestBody(date));
        map.put("month", toRequestBody(monthName));
        MainService.addHolidays(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    holidaysOfCurrentYearApi();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void addHolidayDialogBox() {
        AdminAddholidayDialogboxBinding adminAddholidayDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.admin_addholiday_dialogbox, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(adminAddholidayDialogboxBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        adminAddholidayDialogboxBinding.dateTxt.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            TYear = c.get(Calendar.YEAR);
            TMonth = c.get(Calendar.MONTH);
            TDay = c.get(Calendar.DAY_OF_MONTH);
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePicker,
                    (view1, year, monthOfYear, dayOfMonth) -> {
                        adminAddholidayDialogboxBinding.dateTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        dates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        monthName = DateUtils.getMonthNames(monthOfYear);
//                                binding.dateTxt.setText(new StringBuilder().append(TDay).append("-")
//                                        .append((TMonth + 1)).append("-").append(year)
//                                        .append(" "));
                    }, TYear, TMonth, TDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
        });
        adminAddholidayDialogboxBinding.submitBtn.setOnClickListener(view -> {
            String name = adminAddholidayDialogboxBinding.reasonTxt.getText().toString();
            String date = dates;
            if (adminAddholidayDialogboxBinding.dateTxt.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "Field cannot stay empty");
            } else if (adminAddholidayDialogboxBinding.reasonTxt.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "Field cannot stay empty");
            } else {
                addHolidaysApi(name, date);
                dialog.dismiss();
            }
        });
        adminAddholidayDialogboxBinding.cancelBtn.setOnClickListener(view -> dialog.dismiss());
    }

    public void holidaysOfCurrentYearApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.holidaysOfCurrentYear(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmpHolidaysItem>>() {
                    }.getType();
                    List<EmpHolidaysItem> monthList = new Gson().fromJson(apiResponse.getData(), collectionType);
                    list.clear();
//                    list.add(new EmpHolidaysItem("Saturday Holiday", "Every 1st and 3rd of the Month", -1));
                    list.addAll(monthList);
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    monthAdapter.setList(list);
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
        holidaysOfCurrentYearApi();
    }
}