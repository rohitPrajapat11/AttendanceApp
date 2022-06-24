package com.bdappmaniac.bdapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.bdappmaniac.bdapp.R;

import com.bdappmaniac.bdapp.databinding.DesignLeaveItemListBinding;
import com.bdappmaniac.bdapp.databinding.DialogForLeaveItemsBinding;
import com.bdappmaniac.bdapp.model.ModelLeaves;

import java.util.ArrayList;

public class LeavesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelLeaves> leavelist ;
    Context context;

    public LeavesAdapter(ArrayList<ModelLeaves> leavelist, Context context) {
        this.leavelist = leavelist;
        this.context = context;
    }
    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignLeaveItemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_leave_item_list, group, false);
        return new LeavesAdapter.ViewHolder(binding);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);


    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LeavesAdapter.ViewHolder vholder =(LeavesAdapter.ViewHolder) holder;
        vholder.binding.leaveReason.setText(leavelist.get(position).getLeavereason());
        vholder.binding.leaveType.setText(leavelist.get(position).getLeavetype());
        vholder.binding.fromDate.setText(leavelist.get(position).getFromdate());
        vholder.binding.tillDate.setText(leavelist.get(position).getTilldate());
        vholder.binding.discription.setText(leavelist.get(position).getDiscription());
        vholder.binding.status.setText(leavelist.get(position).getStatus());

//
//        vholder.binding.leave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (vholder.binding.discription.getVisibility()==View.VISIBLE){
//                    vholder.binding.discription.setVisibility(View.GONE);
//
//                }
//                else {
//                    vholder.binding.discription.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });

        vholder.binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vholder.binding.dialog.getRoot().getVisibility()== View.VISIBLE) {
                    vholder.binding.dialog.getRoot().setVisibility(View.GONE);
                }
                else{
                    vholder.binding.dialog.getRoot().setVisibility(View.VISIBLE);
                }
                
            }
        });


    }
    @Override
    public int getItemCount() {
        return leavelist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignLeaveItemListBinding binding;
        public ViewHolder(@NonNull DesignLeaveItemListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }



}
