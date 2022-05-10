package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.AdminHistoryItemBinding;
import com.bdappmaniac.bdapp.databinding.DesignHolidayItemsBinding;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;

import java.util.ArrayList;

public class EmpHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelHolidayItems> itemsArrayList = new ArrayList<>();
    Context context;

    public EmpHolidayAdapter(ArrayList<ModelHolidayItems> itemsArrayList, Context context) {
        this.itemsArrayList = itemsArrayList;
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
        vholder.binding.occasion.setText(itemsArrayList.get(position).getOccasion());
        vholder.binding.date.setText(itemsArrayList.get(position).getDate());
        vholder.binding.day.setText(itemsArrayList.get(position).getDay());

    }
    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignHolidayItemsBinding binding;

        public ViewHolder(@NonNull DesignHolidayItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
