package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TestDesignHolidayItemBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;

import java.util.ArrayList;

public class TestHolidayAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelHolidayItems> months;
    Context context;

    public TestHolidayAdaptar(ArrayList<ModelHolidayItems> months, Context context) {
        this.months = months;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TestDesignHolidayItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.test_design_holiday_item, group, false);
        return new TestHolidayAdaptar.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestHolidayAdaptar.ViewHolder vHolder = (TestHolidayAdaptar.ViewHolder) holder;
        vHolder.binding.month.setText(months.get(position).getMonth());

        ArrayList<ModelChildHolidayItems> holidays = new ArrayList<>();
        holidays.add(new ModelChildHolidayItems("01", "Monday", "Hanuman Jayanti"));
        holidays.add(new ModelChildHolidayItems("05", "Tuesday", "Holika Dahan"));
        holidays.add(new ModelChildHolidayItems("10", "Wednesday", "Wasant Panchmi"));
//        holidays.add(new ModelChildHolidayItems("15", "Thursday", "Hanuman Jayanti"));
//        holidays.add(new ModelChildHolidayItems("19", "Friday", "Holika Dahan"));
//        holidays.add(new ModelChildHolidayItems("22", "Saturday", "Wasant Panchmi"));
//        holidays.add(new ModelChildHolidayItems("25", "Sunday", "Hanuman Jayanti"));
//        holidays.add(new ModelChildHolidayItems("27", "Wednesday", "Holika Dahan"));
//        holidays.add(new ModelChildHolidayItems("29", "Wednesday", "Wasant Panchmi"));

        TestChildHolidayeAdaptar adapter = new TestChildHolidayeAdaptar(holidays, context);
        vHolder.binding.recyclerviewHolidays.setLayoutManager(new LinearLayoutManager(context));
        vHolder.binding.recyclerviewHolidays.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TestDesignHolidayItemBinding binding;

        public ViewHolder(@NonNull TestDesignHolidayItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

