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
import com.bdappmaniac.bdapp.databinding.DesignCheckInOutEntriesBinding;
import com.bdappmaniac.bdapp.model.ModelEntries;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;

import java.util.ArrayList;

public class AtteEntriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelEntries> eList = new ArrayList<>();
    Context context;

    public AtteEntriesAdapter(ArrayList<ModelEntries> eList, Context context) {
        this.eList = eList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignCheckInOutEntriesBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_check_in_out_entries, group, false);
        return new ViewHolder(binding);

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AtteEntriesAdapter.ViewHolder vholder = (AtteEntriesAdapter.ViewHolder) holder;
        vholder.binding.checkInT.setText(eList.get(position).getCheck_in());
        vholder.binding.checkOutT.setText(eList.get(position).getCheck_out());


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.itemviewanimation);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return eList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignCheckInOutEntriesBinding binding;

        public ViewHolder(@NonNull DesignCheckInOutEntriesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


}