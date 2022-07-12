package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.AllTaskItem;
import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignTasksItemsBinding;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class EmpTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AllTaskItem> tasklist;
    Context context;
    public EmpTaskAdapter(ArrayList<AllTaskItem> tasklist, Context context) {
        this.tasklist = tasklist;
        this.context = context;
    }


    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {

        DesignTasksItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_tasks_items, group, false);
        return new EmpTaskAdapter.EmpTaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmpTaskViewHolder vholder = (EmpTaskViewHolder) holder;

//        vholder.binding.date.setText(tasklist.get(position).getDate());
        EmpChildTaskAdapter adapter = new EmpChildTaskAdapter(context , (ArrayList<TasksItem>) tasklist.get(position).getTasks());
        vholder.binding.childRecycler.setLayoutManager(new LinearLayoutManager(context));
        vholder.binding.childRecycler.setAdapter(adapter);
    }
    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    public void setTasklist(ArrayList<AllTaskItem>list){
        this.tasklist = list;
    }

    public static class EmpTaskViewHolder extends RecyclerView.ViewHolder {
        DesignTasksItemsBinding binding;

        public EmpTaskViewHolder(@NonNull DesignTasksItemsBinding itemView) {
            super(itemView.getRoot());
            binding= itemView;
        }
    }
}
