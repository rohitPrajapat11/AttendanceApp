package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeHoliday;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.EmployeeHolidayMonthItemBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayMonthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmployeeHoliday> monthList = new ArrayList<>();

    public EmployeeHolidayMonthAdapter(Context context, List<EmployeeHoliday> list) {
        this.context = context;
        this.monthList = (ArrayList<EmployeeHoliday>) list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        EmployeeHolidayMonthItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_holiday_month_item, group, false);
        return new EmployeeHolidayMonthAdapter.EmployeeHolidayMonthHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeHolidayMonthAdapter.EmployeeHolidayMonthHolder vHolder = (EmployeeHolidayMonthAdapter.EmployeeHolidayMonthHolder) holder;
        vHolder.binding.months.setText(monthList.get(position).getMonth());
        vHolder.binding.dropDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vHolder.binding.constraintLayout2.setVisibility(View.VISIBLE);
                vHolder.binding.dropDownBtn.setVisibility(View.GONE);
                vHolder.binding.constraintLayout.setBackgroundResource(R.drawable.bottom_cut_white_bg);
                vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
                EmployeeHolidayAdapter holidayAdapter = new EmployeeHolidayAdapter(context, monthList.get(position).getHolidays());
                vHolder.binding.employeeHolidayRecyclers.setLayoutManager(new LinearLayoutManager(context));
                vHolder.binding.employeeHolidayRecyclers.setAdapter(holidayAdapter);
            }
        });
        vHolder.binding.DropUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vHolder.binding.constraintLayout2.setVisibility(View.GONE);
                vHolder.binding.dropDownBtn.setVisibility(View.VISIBLE);
                vHolder.binding.constraintLayout.setBackgroundResource(R.drawable.white_5r_bg);
                vHolder.binding.DropUpBtn.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return monthList.size();
    }

    public class EmployeeHolidayMonthHolder extends RecyclerView.ViewHolder {
        EmployeeHolidayMonthItemBinding binding;

        public EmployeeHolidayMonthHolder(EmployeeHolidayMonthItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
