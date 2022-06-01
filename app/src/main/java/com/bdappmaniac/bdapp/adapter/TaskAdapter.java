package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignTaskItemsBinding;
import com.bdappmaniac.bdapp.databinding.TaskItemBinding;
import com.bdappmaniac.bdapp.model.ModelTaskList;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ModelTaskList> taskList = new ArrayList<>();


    public TaskAdapter(Context context, ArrayList<ModelTaskList> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignTaskItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_task_items, group, false);
        return new TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaskAdapter.TaskViewHolder vholder = (TaskAdapter.TaskViewHolder) holder;

        vholder.binding.issueDate.setText(taskList.get(position).getIssueDate());
        vholder.binding.taskHeading.setText(taskList.get(position).getTaskHeading());
        vholder.binding.discription.setText(taskList.get(position).getDiscription());
        vholder.binding.complitionDate.setText(taskList.get(position).getComplitionDate());
        vholder.binding.taskType.setText(taskList.get(position).getTasktype());
        vholder.binding.imgtasktype.setImageResource(taskList.get(position).getImgtasktype());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        DesignTaskItemsBinding binding;

        public TaskViewHolder(@NonNull DesignTaskItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
