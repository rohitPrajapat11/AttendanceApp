package com.bdappmaniac.bdapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.HistoryItemBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> taskList;

    public HistoryAdapter(Context context, List<String> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        HistoryItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.history_item, group, false);
        return new HistoryAdapterHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryAdapterHolder vHolder = (HistoryAdapterHolder) holder;
        if (position == 3) {
            vHolder.binding.attendLayout.setVisibility(View.GONE);
            vHolder.binding.absentTxt.setVisibility(View.VISIBLE);
            vHolder.binding.reasonTxt.setVisibility(View.VISIBLE);
        } else if (position == 8) {
            vHolder.binding.attendLayout.setVisibility(View.GONE);
            vHolder.binding.absentTxt.setVisibility(View.VISIBLE);
            vHolder.binding.reasonTxt.setVisibility(View.VISIBLE);
            vHolder.binding.reasonTxt.setText("I went out of town");

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class HistoryAdapterHolder extends RecyclerView.ViewHolder {
        HistoryItemBinding binding;

        public HistoryAdapterHolder(HistoryItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
