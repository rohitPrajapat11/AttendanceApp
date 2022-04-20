package com.bdappmaniac.bdapp.admin.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmployeeHistoryDataItem;
import com.bdappmaniac.bdapp.Api.response.InoutsItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeAttendanceAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;


public class EmployeeAttendanceFragment extends BaseFragment implements CalendarCallBack {
    FragmentEmployeeAttendanceBinding binding;
    Calendar from = Calendar.getInstance();
    String selectedMonth = "Jan";
    EmployeeAttendanceAdapter adapter;
    List<InoutsItem> historyList = new ArrayList<>();
    List<EmployeeHistoryDataItem> List = new ArrayList<>();
    String fromDates;
    String toDates;
    int IDE;
    Calendar to = Calendar.getInstance();
    private int FYear, FMonth, FDay;
    private int TYear, TMonth, TDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attendance, container, false);
            if (getArguments() != null) {
                IDE = getArguments().getInt("id");
            }
            adapter = new EmployeeAttendanceAdapter(mContext, List);
            binding.historyRecycler.setHasFixedSize(false);
            binding.historyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.historyRecycler.setAdapter(adapter);
            Constant.calendarCallBack = this;
            binding.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });
            binding.fromDateLayout.setOnClickListener(v -> {
                FYear = from.get(Calendar.YEAR);
                FMonth = from.get(Calendar.MONTH);
                FDay = from.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromDates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                binding.FromDate.setText(DateUtils.getFormattedTime(fromDates, DateUtils.appDateFormat, DateUtils.appDateFormatTo));
                            }
                        }, FYear, FMonth, FDay);
                datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                datePickerDialog.show();
            });
            binding.toDateLayout.setOnClickListener(v -> {
                if (!binding.FromDate.getText().toString().isEmpty()) {
                    TYear = to.get(Calendar.YEAR);
                    TMonth = to.get(Calendar.MONTH);
                    TDay = to.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    toDates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    binding.ToDate.setText(DateUtils.getFormattedTime(toDates, DateUtils.appDateFormat, DateUtils.appDateFormatTo));
                                    binding.ToDate.setFocusable(false);
                                }
                            }, TYear, TMonth, TDay);
                    datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    long getFromDate = DateUtils.getTimeInMillis(DateUtils.parseStringDate(binding.FromDate.getText().toString(), "dd-MM-yyyy", "dd-MM-yyyy"), "dd-MM-yyyy");
                    if (!binding.FromDate.getText().toString().isEmpty()) {
                        datePickerDialog.getDatePicker().setMinDate(getFromDate);
                    }
                    datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                    datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                    datePickerDialog.show();
                }
            });
            binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.FromDate.setText("");
                    binding.ToDate.setText("");
                }
            });
            binding.findBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fromDate = fromDates;
                    String toDate = toDates;
                    if (binding.FromDate.getText().toString().isEmpty()) {
                        showSnackBar(binding.getRoot(), "From Date field is empty");
                    } else if (binding.ToDate.getText().toString().isEmpty()) {
                        showSnackBar(binding.getRoot(), "To Date field is empty");
                    } else {
                        getInAndOutsBetweenDatesApi(fromDate, toDate, IDE);
                    }
                }
            });
        }
        return binding.getRoot();
    }

    public void selectedMonth(String date) {
        selectedMonth = date;
//        binding.calTitle.setText(date + " " + "2022");
    }

    @Override
    public void openCalendar() {
//        binding.calendarLayout.setVisibility(View.VISIBLE);
    }

    private void getInAndOutsBetweenDatesApi(String fromDate, String toDate, int emp_id) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("fromDate", toRequestBody(fromDate));
        map.put("toDate", toRequestBody(toDate));
        MainService.getInAndOutsBetweenDates(mContext, getToken(), map, emp_id).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmployeeHistoryDataItem>>() {
                    }.getType();
                    List<EmployeeHistoryDataItem> workingTimeList = new Gson().fromJson(apiResponse.getData(), collectionType);
                    List.clear();
                    List.addAll(workingTimeList);
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    adapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(),apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

}