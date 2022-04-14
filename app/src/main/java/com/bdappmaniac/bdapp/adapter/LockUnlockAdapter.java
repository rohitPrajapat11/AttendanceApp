package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesiginLockUnlockItemsBinding;
import com.bdappmaniac.bdapp.model.LockUnlockModel;

import java.util.ArrayList;

public class LockUnlockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<LockUnlockModel> employeList = new ArrayList<>();
    Context context;

    public LockUnlockAdapter(ArrayList<LockUnlockModel> employeList, Context context) {
        this.employeList = employeList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesiginLockUnlockItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.desigin_lock_unlock_items, group, false);
        return new LockUnlockAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder gholder = (ViewHolder) holder;
        gholder.binding.empName.setText(employeList.get(position).getName());
        gholder.binding.time.setText(employeList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return employeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesiginLockUnlockItemsBinding binding;

        public ViewHolder(@NonNull DesiginLockUnlockItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
