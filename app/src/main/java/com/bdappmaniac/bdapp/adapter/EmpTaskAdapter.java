package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeTaskDataItem;
import com.bdappmaniac.bdapp.Api.response.EmployeeTasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignTasksItemsBinding;

import java.util.List;

public class EmpTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   List<EmployeeTaskDataItem> tasklist;
    Context context;
    public EmpTaskAdapter(List<EmployeeTaskDataItem> tasklist, Context context) {
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

        vholder.binding.date.setText(tasklist.get(position).getDate());

        EmpTaskChildAdapter adapter = new EmpTaskChildAdapter(context , (List<EmployeeTasksItem>) tasklist.get(position).getTasks());
        vholder.binding.childRecycler.setAdapter(adapter);
    }
    @Override
    public int getItemCount() {
        return tasklist.size();
    }



    public static class EmpTaskViewHolder extends RecyclerView.ViewHolder {
        DesignTasksItemsBinding binding;

        public EmpTaskViewHolder(@NonNull DesignTasksItemsBinding itemView) {
            super(itemView.getRoot());
            binding= itemView;
        }
    }
}
