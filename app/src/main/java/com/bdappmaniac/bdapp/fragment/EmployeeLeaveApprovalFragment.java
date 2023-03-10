package com.bdappmaniac.bdapp.fragment;

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
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeLeaveApprovalBinding;
import com.bdappmaniac.bdapp.databinding.LeavesBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EmployeeLeaveApprovalFragment extends BaseFragment {
    FragmentEmployeeLeaveApprovalBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    Calendar to = Calendar.getInstance();
    Calendar from = Calendar.getInstance();
    String fromDates;
    String toDates;
    private int TYear, TMonth, TDay;
    private int FYear, FMonth, FDay;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_leave_approval, container, false);
        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).popBackStack());
        binding.ivCalendarNext.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, 1);
            setUpCalendar();
        });

        binding.ivCalendarPrevious.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, -1);
            setUpCalendar();
        });
        binding.filterBtn.setOnClickListener(view -> {
            binding.monthLayout.setVisibility(View.GONE);
            binding.toAndFromLayout.setVisibility(View.GONE);
            LeavesBottomSheetDialogBinding levBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.leaves_bottom_sheet_dialog, null, false);
            Dialog dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(levBinding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            levBinding.approvedBtn.setOnClickListener(v -> dialog.dismiss());
            levBinding.pendingBtn.setOnClickListener(v -> dialog.dismiss());
            levBinding.months.setOnClickListener(v -> {
                binding.monthLayout.setVisibility(View.VISIBLE);
                dialog.dismiss();
            });
            levBinding.dateBtn.setOnClickListener(v -> {
                binding.toAndFromLayout.setVisibility(View.VISIBLE);
                dialog.dismiss();
            });
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
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(requireActivity(), R.color.white);
    }
}