package com.bdappmaniac.bdapp.adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.bdappmaniac.bdapp.databinding.CheckinOutDialogBinding;
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


        vholder.binding.leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vholder.binding.discription.getVisibility()==View.VISIBLE){
                    vholder.binding.discription.setVisibility(View.GONE);

                }
                else {
                    vholder.binding.discription.setVisibility(View.VISIBLE);

                }
            }
        });

        vholder.binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionDialog();
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

    public void actionDialog(){
         DialogForLeaveItemsBinding actiondialog = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_for_leave_items, null, false);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(actiondialog.getRoot());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

}