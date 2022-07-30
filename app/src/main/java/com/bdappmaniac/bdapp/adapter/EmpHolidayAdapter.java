package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmpHolidayDataItems;
import com.bdappmaniac.bdapp.Api.response.EmpHolidaysItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignHolidayItemsBinding;
import com.bdappmaniac.bdapp.model.ModelChildHolidayItems;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;

import java.util.ArrayList;
import java.util.List;

public class EmpHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<EmpHolidayDataItems> monthlist ;
    Context context;


    public EmpHolidayAdapter(ArrayList<EmpHolidayDataItems> monthlist, Context context) {
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
        EmpHolidayDataItems data = monthlist.get(position);
        vholder.binding.month.setText(data.getMonth());


        if (monthlist.get(position).getHolidays() != null){
        EmpHolidayChildAdapter adapter = new EmpHolidayChildAdapter((List<EmpHolidaysItem>) monthlist.get(position).getHolidays(), context);
        vholder.binding.recyclerviewHolidays.setAdapter(adapter);
        }
        else {

            vholder.binding.recyclerviewHolidays.setVisibility(View.GONE);
            vholder.binding.noHolidaysTv.setVisibility(View.VISIBLE);
        }

        holder.getAdapterPosition();
        holder.setIsRecyclable(false);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
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
