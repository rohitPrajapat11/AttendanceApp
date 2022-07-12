package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TestDesignHolidayBinding;

import java.util.List;

public class TestChildHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HolidaysItem> holidays;

    public TestChildHolidayAdapter(Context context, List<HolidaysItem> holidays) {
        this.context = context;
        this.holidays = holidays;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TestDesignHolidayBinding binding = DataBindingUtil.inflate(inflater, R.layout.test_design_holiday, group, false);
        return new TestChildHolidayAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestChildHolidayAdapter.ViewHolder vHolder = (TestChildHolidayAdapter.ViewHolder) holder;
        vHolder.binding.date.setText("10");
        vHolder.binding.day.setText("Monday");
        vHolder.binding.holiday.setText(holidays.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return holidays == null ? 0 : holidays.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TestDesignHolidayBinding binding;

        public ViewHolder(@NonNull TestDesignHolidayBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

