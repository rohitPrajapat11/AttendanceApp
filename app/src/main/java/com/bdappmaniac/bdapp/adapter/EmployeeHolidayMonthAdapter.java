package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmpHolidayDataItems;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.EmployeeHolidayMonthItemBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayMonthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmpHolidayDataItems> monthList = new ArrayList<>();

    public EmployeeHolidayMonthAdapter(Context context, List<EmpHolidayDataItems> list) {
        this.context = context;
        this.monthList = (ArrayList<EmpHolidayDataItems>) list;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EmployeeHolidayMonthAdapter.EmployeeHolidayMonthHolder vHolder = (EmployeeHolidayMonthAdapter.EmployeeHolidayMonthHolder) holder;
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
