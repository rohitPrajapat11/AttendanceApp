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
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.HistoryAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttendanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

import java.util.Arrays;
import java.util.Calendar;


public class EmployeeAttendanceFragment extends BaseFragment {
    FragmentEmployeeAttendanceBinding binding;
    private int FYear, FMonth, FDay;
    private int TYear, TMonth, TDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attendance, container, false);
        String[] taskList = new String[]{"History Task 1", "History Task 2", "History Task 3",
                "History Task 4", "History Task 5", "History Task 5"};
        HistoryAdapter adapter = new HistoryAdapter(getContext(), Arrays.asList(taskList));
        binding.attRecycler.setAdapter(adapter);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        binding.fromDateLayout.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            FYear = c.get(Calendar.YEAR);
            FMonth = c.get(Calendar.MONTH);
            FDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            binding.fromDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, FYear, FMonth, FDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });
        binding.toDateLayout.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            TYear = c.get(Calendar.YEAR);
            TMonth = c.get(Calendar.MONTH);
            TDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            binding.toDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, TYear, TMonth, TDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });
        return binding.getRoot();
    }
}