package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.AttToDoListBinding;
import com.bdappmaniac.bdapp.databinding.DesignHolidayItemsBinding;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;
import com.bdappmaniac.bdapp.model.ModelHomeTodoList;

import java.util.ArrayList;

public class EmpHomeTodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelHomeTodoList> dList = new ArrayList<>();
    Context context;

    public EmpHomeTodoListAdapter(ArrayList<ModelHomeTodoList> dList, Context context) {
        this.dList = dList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        AttToDoListBinding binding = DataBindingUtil.inflate(inflater, R.layout.att_to_do_list, group, false);
        return new EmpHomeTodoListAdapter.ViewHolder(binding);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        AttToDoListBinding binding;

        public ViewHolder(@NonNull AttToDoListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
