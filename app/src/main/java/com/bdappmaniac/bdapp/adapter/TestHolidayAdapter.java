package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeHoliday;
import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TestDesignHolidayItemBinding;

import java.util.List;

public class TestHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmployeeHoliday> months;
    List<HolidaysItem> holidays;

    public TestHolidayAdapter(Context context, List<EmployeeHoliday> months) {
        this.context = context;
        this.months = months;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TestDesignHolidayItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.test_design_holiday_item, group, false);
        return new TestHolidayAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestHolidayAdapter.ViewHolder vHolder = (TestHolidayAdapter.ViewHolder) holder;
        vHolder.binding.month.setText(months.get(position).getMonth());

        TestChildHolidayAdapter childAdapter = new TestChildHolidayAdapter(context, holidays);
        vHolder.binding.holidayItemRecycler.setLayoutManager(new LinearLayoutManager(context));
        vHolder.binding.holidayItemRecycler.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public void setList(List<EmployeeHoliday> list) {
        this.months = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TestDesignHolidayItemBinding binding;

        public ViewHolder(@NonNull TestDesignHolidayItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

