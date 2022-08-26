package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceData;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceInOuts;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignAttendanceHistoryBinding;
import com.bdappmaniac.bdapp.model.ModelEntries;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class MyAttendanceHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    AtteEntriesAdapter adapter;
    List<MyAttendanceItem> elist;
    Context context;

    public MyAttendanceHistoryAdapter(List<MyAttendanceItem> elist, Context context) {
        this.elist = elist;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignAttendanceHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_attendance_history, group, false);
        return new ViewHolder(binding);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyAttendanceHistoryAdapter.ViewHolder vholder = (MyAttendanceHistoryAdapter.ViewHolder) holder;

//        vholder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (vholder.binding.recycleerEntries.getVisibility() == View.VISIBLE) {
//                    vholder.binding.recycleerEntries.setVisibility(View.GONE);
//                    vholder.binding.checkInOutTexts.setVisibility(View.VISIBLE);
//                } else {
//                    vholder.binding.recycleerEntries.setVisibility(View.VISIBLE);
//                    vholder.binding.checkInOutTexts.setVisibility(View.GONE);
//
//                }
//            }
//        });

        MyAttendanceItem data = elist.get(position);
        if (data != null) {
            vholder.binding.date.setText(DateUtils.getFormattedTime(data.getDate(), "yyyy-mm-dd", "dd"));
            vholder.binding.day.setText(DateUtils.getFormattedTime(data.getDay(), "EEEE", "EEE"));
        }
        if (elist.get(position).getInouts()!= null) {
            adapter = new AtteEntriesAdapter((List<MyAttendanceInOuts>) elist.get(position).getInouts(), context);
            vholder.binding.recycleerEntries.setLayoutManager(new LinearLayoutManager(context));
            vholder.binding.recycleerEntries.setAdapter(adapter);
        }

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignAttendanceHistoryBinding binding;

        public ViewHolder(@NonNull DesignAttendanceHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


}
