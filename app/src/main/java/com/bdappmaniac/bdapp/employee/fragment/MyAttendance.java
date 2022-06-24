package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.MyAttendanceHistoryAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentMyAttendanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class MyAttendance extends BaseFragment {
    public FragmentMyAttendanceBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    MyAttendanceHistoryAdapter historyAdapter;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_attendance, container, false);
            StatusBarUtils.statusBarColor(getActivity(), R.color.white);

            binding.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).popBackStack();
                }
            });
        ArrayList<ModelMyAttendenceHistory> HistoryList = new ArrayList<>();
        HistoryList.add(new ModelMyAttendenceHistory("FRI", "23"));
        HistoryList.add(new ModelMyAttendenceHistory("THU", "15"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","16"));
        HistoryList.add(new ModelMyAttendenceHistory("SAT","17"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","23"));
        HistoryList.add(new ModelMyAttendenceHistory("THU","15"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","16"));
        HistoryList.add(new ModelMyAttendenceHistory("SAT","17"));
        HistoryList.add(new ModelMyAttendenceHistory("MON","19"));
        HistoryList.add(new ModelMyAttendenceHistory("TUE", "20"));
        HistoryList.add(new ModelMyAttendenceHistory("WED", "21"));
        HistoryList.add(new ModelMyAttendenceHistory("THU", "22"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI", "23"));

        MyAttendanceHistoryAdapter historyAdapter = new MyAttendanceHistoryAdapter(HistoryList, mContext);
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        binding.historyRecycler.setAdapter(historyAdapter);
        binding.historyRecycler.setNestedScrollingEnabled(true);


        binding.leftBtn.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, 1);
            setUpCalendar();
        });
        binding.rightBtn.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, -1);
            setUpCalendar();
        });

        return binding.getRoot();
    }

    private void setUpCalendar() {
        String currentString = sdf.format(cal.getTime());
        String[] separated = currentString.split(" ");
        binding.monthTxt.setText(separated[0]);
        binding.yearTxt.setText(separated[1]);
    }
}