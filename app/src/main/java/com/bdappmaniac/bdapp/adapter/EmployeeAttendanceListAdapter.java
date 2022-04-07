package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.InoutsItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.HistoryListItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<InoutsItem> historyList = new ArrayList<>();

    public EmployeeAttendanceListAdapter(Context context, List<InoutsItem> list) {
        this.context = context;
        this.historyList = (ArrayList<InoutsItem>) list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        HistoryListItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.history_list_items, group, false);
        return new EmployeeAttendanceListAdapter.HistoryListHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeAttendanceListAdapter.HistoryListHolder vHolder = (EmployeeAttendanceListAdapter.HistoryListHolder) holder;
        if (historyList.get(position).getLogIn() != null) {
            vHolder.binding.checkInTimes.setText(historyList.get(position).getLogIn().toString());
        }
        if (historyList.get(position).getLogOut() != null) {
            vHolder.binding.checkOutTimes.setText(historyList.get(position).getLogOut().toString());
        }
        if (historyList.get(position).getLogOut() == null) {
            vHolder.binding.checkOutTimes.setText("-");
            vHolder.binding.checkOutTimes.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryListHolder extends RecyclerView.ViewHolder {
        HistoryListItemsBinding binding;

        public HistoryListHolder(HistoryListItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

