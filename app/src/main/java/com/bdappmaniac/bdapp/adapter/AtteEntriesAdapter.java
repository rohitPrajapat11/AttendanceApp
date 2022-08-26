package com.bdappmaniac.bdapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.MyAttendanceInOuts;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignCheckInOutEntriesBinding;
import com.bdappmaniac.bdapp.model.ModelEntries;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;

import java.util.ArrayList;
import java.util.List;

public class AtteEntriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  List<MyAttendanceInOuts> inoutList = new ArrayList<>();
    Context context;

    public AtteEntriesAdapter(List<MyAttendanceInOuts> inoutList, Context context) {
        this.inoutList = inoutList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignCheckInOutEntriesBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_check_in_out_entries, group, false);
        return new ViewHolder(binding);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AtteEntriesAdapter.ViewHolder vholder = (AtteEntriesAdapter.ViewHolder) holder;
        MyAttendanceInOuts data = inoutList.get(position);
        if (data == null) {
            vholder.binding.checkInT.setText("--/--/--");
            vholder.binding.checkOutT.setText("--/--/--");
        } else {
            vholder.binding.checkInT.setText(data.getLogIn());
            vholder.binding.checkOutT.setText(data.getLogOut());
        }

//        vholder.binding.workingH.setText(data.get());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.itemviewanimation);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return inoutList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignCheckInOutEntriesBinding binding;

        public ViewHolder(@NonNull DesignCheckInOutEntriesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


}