package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bdappmaniac.bdapp.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public ArrayList<String> TaskList;
    Context context;


    public TaskAdapter(ArrayList<String> taskList, Context context) {
        TaskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        //TaskItem currentItem = TaskList.get(position);
        //  holder.numberTxt.setText(currentItem.getNumberTxt());

        holder.taskTxt.setText(TaskList.get(position));
        holder.numberTxt.setText(String.valueOf(position + 1)+".");


    }

    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView numberTxt;
        public TextView taskTxt;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            numberTxt = itemView.findViewById(R.id.numberTxt);
            taskTxt = itemView.findViewById(R.id.taskTxt);
        }
    }
}
