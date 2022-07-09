package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.TaskItemBinding;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.List;

public class AdminTaskItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TasksItem> taskItem;

    public AdminTaskItemAdapter(Context context, List<TasksItem> taskItem) {
        this.context = context;
        this.taskItem = taskItem;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        TaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_item, group, false);
        return new AdminTaskItemAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdminTaskItemAdapter.ViewHolder vHolder = (AdminTaskItemAdapter.ViewHolder) holder;
        vHolder.binding.date.setText(DateUtils.getFormattedTime(taskItem.get(position).getDate(), DateUtils.appDateFormat, DateUtils.appDateFormatTos));

        adminChildTaskAdapter adapter = new adminChildTaskAdapter(context, taskItem.get(position).getTasks());
        vHolder.binding.allTaskChildRecycler.setLayoutManager(new LinearLayoutManager(context));
        vHolder.binding.allTaskChildRecycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return taskItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;

        public ViewHolder(@NonNull TaskItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
