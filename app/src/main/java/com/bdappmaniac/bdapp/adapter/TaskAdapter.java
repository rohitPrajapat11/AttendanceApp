package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TaskItemBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> TaskList = new ArrayList<>();

    public TaskAdapter(ArrayList<String> taskList, Context context) {
        this.context = context;
        this.TaskList = (ArrayList<String>) taskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_item, group, false);
        return new TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskAdapter.TaskViewHolder vHolder = (TaskAdapter.TaskViewHolder) holder;
        vHolder.binding.taskTxt.setText(TaskList.get(position));
        vHolder.binding.numberTxt.setText(String.valueOf(position + 1) + ".");
    }

    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;

        public TaskViewHolder(@NonNull TaskItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
