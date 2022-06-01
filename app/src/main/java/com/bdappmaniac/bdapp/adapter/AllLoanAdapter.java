package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignCustomMonthpickerBinding;
import com.bdappmaniac.bdapp.databinding.DesignLeaveItemListBinding;
import com.bdappmaniac.bdapp.model.ModelAllLoans;
import com.bdappmaniac.bdapp.model.Modelmonthpicker;

import java.util.ArrayList;

public class AllLoanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelAllLoans> loanlist = new ArrayList<>();
    Context context;

    public AllLoanAdapter(ArrayList<ModelAllLoans> loanlist, Context context) {
        this.loanlist = loanlist;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
       DesignLeaveItemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_leave_item_list, group, false);
        return new AllLoanAdapter.ViewHolder(binding);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AllLoanAdapter.ViewHolder vHolder = (AllLoanAdapter.ViewHolder) holder;
        vHolder.binding.leaveReason.setText(loanlist.get(position).getLeaveReason());
        vHolder.binding.leaveType.setText(loanlist.get(position).getLeaveType());
        vHolder.binding.fromDate.setText(loanlist.get(position).getFromDate());
        vHolder.binding.tillDate.setText(loanlist.get(position).getTillDate());
        vHolder.binding.discription.setText(loanlist.get(position).getDiscription());

        vHolder.binding.dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vHolder.binding.discription.getVisibility()==View.VISIBLE){
                    vHolder.binding.discription.setVisibility(View.GONE);
                    vHolder.binding.dropdown.setImageResource(R.drawable.drop_down);
                }
                else {
                    vHolder.binding.discription.setVisibility(View.VISIBLE);
                    vHolder.binding.dropdown.setImageResource(R.drawable.drop_up);
                }
            }
        });
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.itemviewanimation);
//        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return loanlist.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignLeaveItemListBinding binding;

        public ViewHolder(@NonNull DesignLeaveItemListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
