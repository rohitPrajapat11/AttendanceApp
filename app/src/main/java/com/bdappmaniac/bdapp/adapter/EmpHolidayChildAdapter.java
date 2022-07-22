package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmpHolidaysItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignHolidaysBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class EmpHolidayChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<EmpHolidaysItem> holidaylist ;
    Context context;


    public EmpHolidayChildAdapter(List<EmpHolidaysItem> holidaylist, Context context) {
        this.holidaylist = holidaylist;
        this.context = context;
    }


    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignHolidaysBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_holidays, group, false);
        return new EmpHolidayChildAdapter.ViewHolder(binding);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmpHolidayChildAdapter.ViewHolder vholder = (EmpHolidayChildAdapter.ViewHolder) holder;
        EmpHolidaysItem data =  holidaylist.get(position);
        vholder.binding.date.setText(DateUtils.getFormattedTime(data.getDate(),"yyyy-MM-dd","dd"));
        vholder.binding.day.setText(String.valueOf(data.getId()));
        vholder.binding.holiday.setText(data.getName());
        holder.getAdapterPosition();
        holder.setIsRecyclable(false);
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
