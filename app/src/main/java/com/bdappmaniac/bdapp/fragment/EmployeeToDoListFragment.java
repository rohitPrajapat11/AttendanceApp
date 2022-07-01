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

import com.bdappmaniac.bdapp.Api.response.AllTaskItem;
import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.adminChildTaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeToDoListBinding;
import com.bdappmaniac.bdapp.databinding.TaskAddDialogBinding;
import com.bdappmaniac.bdapp.databinding.TaskBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.RequestBody;

public class EmployeeToDoListFragment extends BaseFragment {
    FragmentEmployeeToDoListBinding binding;
    ArrayList<TasksItem> list = new ArrayList<>();
    adminChildTaskAdapter adapter;
    AllTaskItem allTaskItem;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    Calendar to = Calendar.getInstance();
    Calendar from = Calendar.getInstance();
    String fromDates;
    String toDates;
    String dates;
    private int TYear, TMonth, TDay;
    private int FYear, FMonth, FDay;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_to_do_list, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            allTaskItem = (AllTaskItem) bundle.getSerializable("EmployeeTaskList");
            adapter = new adminChildTaskAdapter(getContext(), list);
        }
        binding.childRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.childRecycler.setAdapter(adapter);
        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
        binding.filterBtn.setOnClickListener(v -> {
            binding.toAndFromLayout.setVisibility(View.GONE);
            binding.monthLayout.setVisibility(View.GONE);
            TaskBottomSheetDialogBinding taskBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.task_bottom_sheet_dialog, null, false);
            Dialog dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(taskBinding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            taskBinding.addTaskBtn.setOnClickListener(view -> {
                addTaskDialogBox();
                dialog.dismiss();
            });
            taskBinding.dateBtn.setOnClickListener(view -> {
                binding.toAndFromLayout.setVisibility(View.VISIBLE);
                dialog.dismiss();
            });
            taskBinding.months.setOnClickListener(view -> {
                binding.monthLayout.setVisibility(View.VISIBLE);
                dialog.dismiss();
            });
            taskBinding.approvedBtn.setOnClickListener(view -> dialog.dismiss());
            taskBinding.pendingBtn.setOnClickListener(view -> dialog.dismiss());
        });
        binding.ivCalendarNext.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, 1);
            setUpCalendar();
        });

        binding.ivCalendarPrevious.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, -1);
            setUpCalendar();
        });

        binding.FromDate.setOnClickListener(v -> {
            FYear = from.get(Calendar.YEAR);
            FMonth = from.get(Calendar.MONTH);
            FDay = from.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        fromDates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        binding.FromDate.setText(DateUtils.getFormattedTime(fromDates, DateUtils.appDateFormat, DateUtils.appDateFormatTo));
                    }, FYear, FMonth, FDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });
        binding.ToDate.setOnClickListener(v -> {
            if (!binding.FromDate.getText().toString().isEmpty()) {
                TYear = to.get(Calendar.YEAR);
                TMonth = to.get(Calendar.MONTH);
                TDay = to.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            toDates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            binding.ToDate.setText(DateUtils.getFormattedTime(toDates, DateUtils.appDateFormat, DateUtils.appDateFormatTo));
                            binding.ToDate.setFocusable(false);
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
        binding.cancelBtn.setOnClickListener(view -> {
            binding.FromDate.setText("");
            binding.ToDate.setText("");
        });
        binding.findBtn.setOnClickListener(view -> {
            String fromDate = fromDates;
            String toDate = toDates;
            if (binding.FromDate.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "From Date field is empty");
            } else if (binding.ToDate.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "To Date field is empty");
            } else {
//                    getInAndOutsBetweenDatesApi(fromDate, toDate, IDE);
            }
        });
        return binding.getRoot();
    }

    private void setUpCalendar() {
        String currentString = sdf.format(cal.getTime());
        String[] separated = currentString.split(" ");
        binding.monthTxt.setText(separated[0]);
        binding.yearTxt.setText(separated[1]);
    }

    public void addTaskDialogBox() {
        TaskAddDialogBinding taskBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.task_add_dialog, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(taskBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        taskBinding.timeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                TYear = c.get(Calendar.YEAR);
                TMonth = c.get(Calendar.MONTH);
                TDay = c.get(Calendar.DAY_OF_MONTH);
                @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DatePicker,
                        (view1, year, monthOfYear, dayOfMonth) -> {
                            taskBinding.timeTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            dates = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }, TYear, TMonth, TDay);
                datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        taskBinding.submitBtn.setOnClickListener(view -> {
            SharedPref.init(mContext);
            String emp_id = String.valueOf(allTaskItem.getEmpId());
            String content = taskBinding.descriptionTxt.getText().toString();
            String deadline = dates;
            String title = taskBinding.reasonTxt.getText().toString();
            if (taskBinding.reasonTxt.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "Field cannot stay empty");
            } else if (taskBinding.descriptionTxt.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "Field cannot stay empty");
            } else if (taskBinding.timeTxt.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), "Field cannot stay empty");
            } else {
                createTaskApi(emp_id, content, title, deadline);
                dialog.dismiss();
            }
        });
        taskBinding.cancelBtn.setOnClickListener(view -> dialog.dismiss());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void createTaskApi(String emp_id, String content, String title, String deadline) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("emp_id", toRequestBody(emp_id));
        map.put("content", toRequestBody(content));
        map.put("title", toRequestBody(title));
        map.put("deadline", toRequestBody(deadline));
        MainService.createTask(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    //Object
                    TasksItem tasksItem = new Gson().fromJson(apiResponse.getData(), TasksItem.class);
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    adapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showToast(apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}