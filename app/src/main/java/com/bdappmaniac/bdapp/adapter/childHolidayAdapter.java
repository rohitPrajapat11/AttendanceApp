package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignHolidayItemsBinding;
import com.bdappmaniac.bdapp.databinding.DesignHolidaysBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;

import java.util.ArrayList;

public class childHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelChildHolidayItems> holidaylist = new ArrayList<>();
    Context context;


    public childHolidayAdapter(ArrayList<ModelChildHolidayItems> holidaylist, Context context) {
        this.holidaylist = holidaylist;
        this.context = context;
    }


    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignHolidaysBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_holidays, group, false);
        return new childHolidayAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vholder = (ViewHolder) holder;
        vholder.binding.date.setText(holidaylist.get(position).getDate());
        vholder.binding.day.setText(holidaylist.get(position).getDay());
        vholder.binding.holiday.setText(holidaylist.get(position).getHoliday());


    }

    @Override
    public int getItemCount() {
        return holidaylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignHolidaysBinding binding;

        public ViewHolder(@NonNull DesignHolidaysBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
