package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeTasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignChildTaskItemsBinding;

import java.util.List;

public class EmpTaskChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmployeeTasksItem> childTaskList;


    public EmpTaskChildAdapter(Context context, List<EmployeeTasksItem> childTaskList) {
        this.context = context;
        this.childTaskList = childTaskList;
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
        EmpTaskChildAdapter.TaskViewHolder vholder = (EmpTaskChildAdapter.TaskViewHolder) holder;
        EmployeeTasksItem data = childTaskList.get(position);
        vholder.binding.taskHeading.setText(data.getTitle());
        vholder.binding.complitionDate.setText(data.getDeadline());
        vholder.binding.discription.setText(data.getContent());

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
