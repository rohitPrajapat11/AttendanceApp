package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignChildTaskItemsBinding;
import com.bdappmaniac.bdapp.databinding.DesignTasksItemsBinding;
import com.bdappmaniac.bdapp.model.ModelChildTaskList;
import com.bdappmaniac.bdapp.model.ModelEmpTask;

import java.util.ArrayList;

public class EmpTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelEmpTask> tasklist = new ArrayList<>();
    Context context;

    public EmpTaskAdapter(ArrayList<ModelEmpTask> tasklist, Context context) {
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
        ArrayList<ModelChildTaskList> childTaskList = new ArrayList<>();
        childTaskList.add(new ModelChildTaskList("20-03-2022", "Fix the bug ",
                "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        childTaskList.add(new ModelChildTaskList("21-03-2022", "Comple the remaining screens in  time",
                "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        childTaskList.add(new ModelChildTaskList("23-03-2022", "App  working flow is not correct ",
                "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));

        EmpChildTaskAdapter adapter = new EmpChildTaskAdapter(context , childTaskList);
        vholder.binding.childRecycler.setLayoutManager(new LinearLayoutManager(context));
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
