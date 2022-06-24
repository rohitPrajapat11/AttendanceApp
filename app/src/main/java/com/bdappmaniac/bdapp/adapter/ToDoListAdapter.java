package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.AllTaskItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ToDoListItemBinding;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AllTaskItem> tasksList;

    public ToDoListAdapter(ArrayList<AllTaskItem> tasksList, Context context) {
        this.context = context;
        this.tasksList = (ArrayList<AllTaskItem>) tasksList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        ToDoListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.to_do_list_item, group, false);
        return new ToDoListAdapter.TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ToDoListAdapter.TaskViewHolder vHolder = (ToDoListAdapter.TaskViewHolder) holder;
        vHolder.binding.empName.setText(tasksList.get(position).getEmployee());
        vHolder.binding.item.setOnClickListener(view -> {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("EmployeeTaskList",(AllTaskItem) tasksList.get(position));
//                Navigation.findNavController(view).navigate(R.id.employeeToDoListFragment, bundle);
            Navigation.findNavController(view).navigate(R.id.employeeToDoListFragment);
        });
    }

    @Override
    public int getItemCount() { return tasksList.size(); }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        ToDoListItemBinding binding;

        public TaskViewHolder(@NonNull ToDoListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
