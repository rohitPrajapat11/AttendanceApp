package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.MonthPickerAdapter;
import com.bdappmaniac.bdapp.adapter.MyAttendanceHistoryAdapter;

import com.bdappmaniac.bdapp.databinding.FragmentMyAttendanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.SmoothScrolllinearSmoothScroller;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;
import com.bdappmaniac.bdapp.model.Modelmonthpicker;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;



public class MyAttendance extends BaseFragment {
    public FragmentMyAttendanceBinding binding;
    MonthPickerAdapter monthPickerAdapter;


    MyAttendanceHistoryAdapter historyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_attendance, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.f1f3f5);


        ArrayList<Modelmonthpicker> dlist = new ArrayList<>();
        dlist.add(new Modelmonthpicker("January"));
        dlist.add(new Modelmonthpicker("February"));
        dlist.add(new Modelmonthpicker("March"));
        dlist.add(new Modelmonthpicker("April"));
        dlist.add(new Modelmonthpicker("May"));
        dlist.add(new Modelmonthpicker("June"));
        dlist.add(new Modelmonthpicker("July"));
        dlist.add(new Modelmonthpicker("August"));
        dlist.add(new Modelmonthpicker("September"));
        dlist.add(new Modelmonthpicker("October"));
        dlist.add(new Modelmonthpicker("November"));
        dlist.add(new Modelmonthpicker("December"));

        MonthPickerAdapter  monthPickerAdapter= new MonthPickerAdapter(dlist,mContext);
        binding.monthpicker.setAdapter(monthPickerAdapter);

        ArrayList<ModelMyAttendenceHistory> HistoryList = new ArrayList<>();
        HistoryList.add(new ModelMyAttendenceHistory("FRI","23"));
        HistoryList.add(new ModelMyAttendenceHistory("THU","15"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","16"));
        HistoryList.add(new ModelMyAttendenceHistory("SAT","17"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","23"));
        HistoryList.add(new ModelMyAttendenceHistory("THU","15"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","16"));
        HistoryList.add(new ModelMyAttendenceHistory("SAT","17"));
        HistoryList.add(new ModelMyAttendenceHistory("MON","19"));
        HistoryList.add(new ModelMyAttendenceHistory("TUE","20"));
        HistoryList.add(new ModelMyAttendenceHistory("WED","21"));
        HistoryList.add(new ModelMyAttendenceHistory("THU","22"));
        HistoryList.add(new ModelMyAttendenceHistory("FRI","23"));

        MyAttendanceHistoryAdapter   historyAdapter=new MyAttendanceHistoryAdapter(HistoryList,mContext);
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        binding.historyRecycler.setAdapter(historyAdapter);
        binding.historyRecycler.setNestedScrollingEnabled(true);

        Animation animation = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.itemviewanimation);
        binding.monthpicker.startAnimation(animation);


        binding.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.monthpicker.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.monthpicker.scrollToPosition(binding.monthpicker.getCurrentItem()-1);
                    }
                });
            }
        });
        binding.rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.monthpicker.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.monthpicker.scrollToPosition(binding.monthpicker.getCurrentItem()+1);
                    }
                });
            }
        });


  return binding.getRoot();


    }
}