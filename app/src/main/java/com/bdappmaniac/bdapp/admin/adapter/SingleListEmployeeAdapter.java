package com.bdappmaniac.bdapp.admin.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.SingleItemOfEmployeeBinding;

import java.util.ArrayList;
public class SingleListEmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<EmployeeList> list = new ArrayList<>();
    NavController navController;
    public SingleListEmployeeAdapter(Context context, ArrayList<EmployeeList> list) {
        this.context = context;
        this.list = list;
        navController = Navigation.findNavController((Activity) context, R.id.adminNav);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        SingleItemOfEmployeeBinding binding = DataBindingUtil.inflate(inflater, R.layout.single_item_of_employee, group, false);
        return new SingleListEmployeeAdapter.SingleListEmployeeHolder(binding);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SingleListEmployeeAdapter.SingleListEmployeeHolder vHolder = (SingleListEmployeeAdapter.SingleListEmployeeHolder) holder;
        vHolder.binding.employeeName.setOnClickListener(v -> {
            navController.navigate(R.id.loanFragment);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SingleListEmployeeHolder extends RecyclerView.ViewHolder {
        SingleItemOfEmployeeBinding binding;

        public SingleListEmployeeHolder(SingleItemOfEmployeeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

