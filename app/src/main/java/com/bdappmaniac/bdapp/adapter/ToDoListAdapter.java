package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TaskItemBinding;
import com.bdappmaniac.bdapp.databinding.TaskItemBinding;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> TasksList = new ArrayList<>();

    public ToDoListAdapter(ArrayList<String> tasksList, Context context) {
        this.context = context;
        this.TasksList = (ArrayList<String>) tasksList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_item, group, false);
        return new TaskAdapter.TaskViewHolder(binding);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskAdapter.TaskViewHolder bHolder = (TaskAdapter.TaskViewHolder) holder;
        bHolder.binding.taskTxt.setText(TasksList.get(position));
        bHolder.binding.numberTxt.setText(String.valueOf(position + 1) + ".");
    }

    @Override
    public int getItemCount() { return TasksList.size(); }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;

        public TaskViewHolder(@NonNull TaskItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
