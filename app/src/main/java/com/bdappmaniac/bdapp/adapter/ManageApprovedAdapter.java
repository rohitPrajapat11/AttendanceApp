package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignCustomMonthpickerBinding;
import com.bdappmaniac.bdapp.databinding.DesignLoanItemsBinding;
import com.bdappmaniac.bdapp.model.ManageApprovedModel;
import com.bdappmaniac.bdapp.model.ModelEmpTask;

import java.util.ArrayList;

public class ManageApprovedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ManageApprovedModel> advancelist = new ArrayList<>();
    Context context;

    public ManageApprovedAdapter(ArrayList<ManageApprovedModel> advancelist, Context context) {
        this.advancelist = advancelist;
        this.context = context;
    }
    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignLoanItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_loan_items, group, false);
        return new ManageApprovedAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ManageApprovedAdapter.ViewHolder vholder = (ManageApprovedAdapter.ViewHolder) holder;
        vholder.binding.title.setText(advancelist.get(position).getTitle());
        vholder.binding.totalAmount.setText(advancelist.get(position).getAmount());
        vholder.binding.totalTime.setText(advancelist.get(position).getTime());
        vholder.binding.instalment.setText(advancelist.get(position).getEmi());
        vholder.binding.discription.setText(advancelist.get(position).getDiscription());

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
        return advancelist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignLoanItemsBinding binding;

        public ViewHolder(@NonNull DesignLoanItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
