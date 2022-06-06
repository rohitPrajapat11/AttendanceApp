package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TestDesignHolidayBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;

import java.util.ArrayList;

public class TestChildHolidayeAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelChildHolidayItems> holidays = new ArrayList<>();
    Context context;

    public TestChildHolidayeAdaptar(ArrayList<ModelChildHolidayItems> holidays, Context context) {
        this.holidays = holidays;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TestDesignHolidayBinding binding = DataBindingUtil.inflate(inflater, R.layout.test_design_holiday, group, false);
        return new TestChildHolidayeAdaptar.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestChildHolidayeAdaptar.ViewHolder vHolder = (TestChildHolidayeAdaptar.ViewHolder) holder;
        vHolder.binding.date.setText(holidays.get(position).getDate());
        vHolder.binding.day.setText(holidays.get(position).getDay());
        vHolder.binding.holiday.setText(holidays.get(position).getHoliday());
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TestDesignHolidayBinding binding;

        public ViewHolder(@NonNull TestDesignHolidayBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

