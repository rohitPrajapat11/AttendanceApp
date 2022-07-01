package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.AdminChildTaskItemBinding;

import java.util.List;

public class adminChildTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TasksItem> childTaskList;

    public adminChildTaskAdapter(Context context, List<TasksItem> childTaskList) {
        this.context = context;
        this.childTaskList = childTaskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        AdminChildTaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.admin_child_task_item, group, false);
        return new adminChildTaskAdapter.TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        adminChildTaskAdapter.TaskViewHolder vHolder = (adminChildTaskAdapter.TaskViewHolder) holder;
        vHolder.binding.taskHeading.setText(childTaskList.get(position).getTitle());
        vHolder.binding.discription.setText(childTaskList.get(position).getContent());
        vHolder.binding.completionDate.setText(childTaskList.get(position).getDeadline());
        vHolder.binding.completeBtn.setText(childTaskList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return childTaskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        AdminChildTaskItemBinding binding;

        public TaskViewHolder(@NonNull AdminChildTaskItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

