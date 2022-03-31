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
import com.bdappmaniac.bdapp.databinding.HistoryListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<InoutsItem> historyList = new ArrayList<>();

    public HistoryListAdapter(Context context, List<InoutsItem> list) {
        this.context = context;
        this.historyList = (ArrayList<InoutsItem>) list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        HistoryListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.history_list_item, group, false);
        return new HistoryListAdapter.HistoryListHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryListAdapter.HistoryListHolder vHolder = (HistoryListAdapter.HistoryListHolder) holder;
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
        if (historyList.get(position).getLogIn() == null && historyList.get(position).getLogOut() == null) {
            vHolder.binding.absentTxt.setVisibility(View.VISIBLE);
            vHolder.binding.reasonTxt.setVisibility(View.VISIBLE);
            if (historyList.get(position).getAbsentReason() != null) {
                vHolder.binding.reasonTxt.setText(historyList.get(position).getAbsentReason().toString());
            }
            vHolder.binding.attendLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryListHolder extends RecyclerView.ViewHolder {
        HistoryListItemBinding binding;

        public HistoryListHolder(HistoryListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
