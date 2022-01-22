package com.bdappmaniac.bdapp.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.adapter.HistoryAdapter;
import com.bdappmaniac.bdapp.adapter.MonthCalendarAdapter;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentHistoryBinding;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryFragment extends BaseFragment implements CalendarCallBack {
    FragmentHistoryBinding binding;
    MonthCalendarAdapter adapter;
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    ArrayList<Date> dates = new ArrayList<Date>();
    String selectedDateByCalendar = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    HomeFragment homeFragment;
    String selectedMonth = "Jan";
    ArrayList<String> calendarList2 = new ArrayList<>();
    int todayDate = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        Constant.calendarCallBack = this;
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        calendarList2.addAll(Arrays.asList(shortMonths));
        String[] category = new String[]{"History Task 1", "History Task 2", "History Task 3",
                "History Task 4", "History Task 5", "History Task 5"};
        List<String> taskList = new ArrayList<>(Arrays.asList(category));
        HistoryAdapter notificationAdapter = new HistoryAdapter(getContext(), taskList);
        binding.historyRecycler.setAdapter(notificationAdapter);
        adapter = new MonthCalendarAdapter(mContext, (ArrayList<String>) calendarList2, this);
        binding.calendarRecycler.setAdapter(adapter);
        binding.cancelCalendarBtn.setOnClickListener(v -> {
            binding.calendarLayout.setVisibility(View.GONE);
        });
        binding.okCalendarBtn.setOnClickListener(v -> {
            binding.calendarLayout.setVisibility(View.GONE);
        });
        binding.calendarNextBtn.setOnClickListener(v -> {
            Toast.makeText(mContext, "On Working", Toast.LENGTH_SHORT).show();
//            String currentString = binding.calTitle.getText().toString();
//            String[] separated = currentString.split(" ");
//            String year = separated[1];
//            if (year.equals("2022")) {
//                binding.calendarNextBtn.setVisibility(View.GONE);
//            } else {
//                int y = Integer.parseInt(year) + 1;
//                binding.calTitle.setText(String.valueOf(separated[0] + " " + y));
//            }
        });
        binding.calendarPreBtn.setOnClickListener(v -> {
            Toast.makeText(mContext, "On Working", Toast.LENGTH_SHORT).show();
//            String currentString = binding.calTitle.getText().toString();
//            String[] separated = currentString.split(" ");
//            String year = separated[1];
//            int y = Integer.parseInt(year) - 1;
//            v.setVisibility(View.VISIBLE);
//            binding.calTitle.setText(String.valueOf(separated[0] + " " + y));
//            if (year.equals("2022")) {
//                binding.calendarNextBtn.setVisibility(View.GONE);
//            } else {
//                binding.calendarNextBtn.setVisibility(View.VISIBLE);
//
//            }
        });
        return binding.getRoot();
    }

    public void selectedMonth(String date) {
        selectedMonth = date;
        binding.calTitle.setText(date + " " + "2022");
    }

    @Override
    public void openCalendar() {

        binding.calendarLayout.setVisibility(View.VISIBLE);
    }
}