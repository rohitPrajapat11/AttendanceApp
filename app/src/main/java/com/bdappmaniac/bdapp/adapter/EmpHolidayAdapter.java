package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.AdminHistoryItemBinding;
import com.bdappmaniac.bdapp.databinding.DesignHolidayItemsBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;

import java.util.ArrayList;

public class EmpHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelHolidayItems> monthlist = new ArrayList<>();
    Context context;


    public EmpHolidayAdapter(ArrayList<ModelHolidayItems> monthlist, Context context) {
        this.monthlist = monthlist;
        this.context = context;
    }


    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignHolidayItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_holiday_items, group, false);
        return new EmpHolidayAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vholder = (ViewHolder) holder;
        vholder.binding.month.setText(monthlist.get(position).getMonth());



        ArrayList<ModelChildHolidayItems> holidaylist = new ArrayList<>();
        holidaylist.add(new ModelChildHolidayItems("22","Friday","Hanuman Jayanti"));
        holidaylist.add(new ModelChildHolidayItems("27","Friday","Holika Dahan"));
        holidaylist.add(new ModelChildHolidayItems("29","Friday","Wasant Panchmi"));
        holidaylist.add(new ModelChildHolidayItems("22","Friday","Hanuman Jayanti"));
        holidaylist.add(new ModelChildHolidayItems("27","Friday","Holika Dahan"));
        holidaylist.add(new ModelChildHolidayItems("29","Friday","Wasant Panchmi"));
        holidaylist.add(new ModelChildHolidayItems("22","Friday","Hanuman Jayanti"));
        holidaylist.add(new ModelChildHolidayItems("27","Friday","Holika Dahan"));
        holidaylist.add(new ModelChildHolidayItems("29","Friday","Wasant Panchmi"));




        childHolidayAdapter adapter = new childHolidayAdapter(holidaylist,context);
        vholder.binding.recyclerviewHolidays.setLayoutManager(new LinearLayoutManager(context));
        vholder.binding.recyclerviewHolidays.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return monthlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignHolidayItemsBinding binding;

        public ViewHolder(@NonNull DesignHolidayItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
