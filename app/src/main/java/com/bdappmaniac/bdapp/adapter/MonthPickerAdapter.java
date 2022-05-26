package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignCustomMonthpickerBinding;
import com.bdappmaniac.bdapp.model.Modelmonthpicker;

import java.util.ArrayList;

public class MonthPickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Modelmonthpicker> dList = new ArrayList<>();
    Context context;

    public MonthPickerAdapter(ArrayList<Modelmonthpicker> dList, Context context) {
        this.dList = dList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
       DesignCustomMonthpickerBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_custom_monthpicker, group, false);
        return new MonthPickerAdapter.ViewHolder(binding);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MonthPickerAdapter.ViewHolder vHolder = (MonthPickerAdapter.ViewHolder) holder;
        vHolder.binding.month.setText(dList.get(position).getMonth());
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.itemviewanimation);
        holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return dList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignCustomMonthpickerBinding binding;

        public ViewHolder(@NonNull DesignCustomMonthpickerBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
