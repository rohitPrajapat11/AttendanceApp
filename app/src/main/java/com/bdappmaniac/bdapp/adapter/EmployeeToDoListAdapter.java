package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ToDoListEmployeeItemBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TasksItem> list = new ArrayList<>();

    public EmployeeToDoListAdapter(Context context, List<TasksItem> list) {
        this.context = context;
        this.list = list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        ToDoListEmployeeItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.to_do_list_employee_item, group, false);
        return new EmployeeToDoListAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeToDoListAdapter.ViewHolder vHolder = (EmployeeToDoListAdapter.ViewHolder) holder;
        vHolder.binding.taskTxt.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ToDoListEmployeeItemBinding binding;

        public ViewHolder(@NonNull ToDoListEmployeeItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
