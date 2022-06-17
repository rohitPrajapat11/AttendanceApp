package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignChildTaskItemsBinding;
import com.bdappmaniac.bdapp.model.ModelChildTaskList;

import java.util.ArrayList;

public class adminChildTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ModelChildTaskList> childTaskList = new ArrayList<>();

    public adminChildTaskAdapter(Context context, ArrayList<ModelChildTaskList> taskList) {
        this.context = context;
        this.childTaskList = taskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignChildTaskItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_child_task_items, group, false);
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
        vHolder.binding.issueDate.setText(childTaskList.get(position).getIssueDate());
        vHolder.binding.taskHeading.setText(childTaskList.get(position).getTaskHeading());
        vHolder.binding.discription.setText(childTaskList.get(position).getDiscription());
        vHolder.binding.complitionDate.setText(childTaskList.get(position).getComplitionDate());
        vHolder.binding.taskType.setText(childTaskList.get(position).getTasktype());
        vHolder.binding.imgtasktype.setImageResource(childTaskList.get(position).getImgtasktype());
    }

    @Override
    public int getItemCount() {
        return childTaskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        DesignChildTaskItemsBinding binding;

        public TaskViewHolder(@NonNull DesignChildTaskItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

