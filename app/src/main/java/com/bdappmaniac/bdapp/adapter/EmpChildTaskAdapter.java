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

public class EmpChildTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ModelChildTaskList> childTaskList = new ArrayList<>();


    public EmpChildTaskAdapter(Context context, ArrayList<ModelChildTaskList> taskList) {
        this.context = context;
        this.childTaskList = taskList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignChildTaskItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_child_task_items, group, false);
        return new TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmpChildTaskAdapter.TaskViewHolder vholder = (EmpChildTaskAdapter.TaskViewHolder) holder;

        vholder.binding.issueDate.setText(childTaskList.get(position).getIssueDate());
        vholder.binding.taskHeading.setText(childTaskList.get(position).getTaskHeading());
        vholder.binding.discription.setText(childTaskList.get(position).getDiscription());
        vholder.binding.completionDate.setText(childTaskList.get(position).getComplitionDate());
        vholder.binding.taskType.setText(childTaskList.get(position).getTasktype());
        vholder.binding.imgtasktype.setImageResource(childTaskList.get(position).getImgtasktype());

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
