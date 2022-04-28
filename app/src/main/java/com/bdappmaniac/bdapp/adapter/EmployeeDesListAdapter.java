package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeDesItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.EmployeeDayAttendanceListBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EmployeeDesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<EmployeeDesItem> employeeList = new ArrayList<>();
    Context context;

    public EmployeeDesListAdapter(ArrayList<EmployeeDesItem> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        EmployeeDayAttendanceListBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_day_attendance_list, group, false);
        return new EmployeeDesListAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EmployeeDesListAdapter.ViewHolder vHolder = (EmployeeDesListAdapter.ViewHolder) holder;
        vHolder.binding.empName.setText(employeeList.get(position).getEmployeeName());
        vHolder.binding.designation.setText(employeeList.get(position).getDesignation());
        Glide.with(context).load(employeeList.get(position).getProfile()).placeholder(R.drawable.user).into(vHolder.binding.profile);
        if (employeeList.get(position).getAttendance().equals("A")) {
            vHolder.binding.status.setTextColor(context.getColor(R.color.secondary_color));
        }
        if (employeeList.get(position).getAttendance().equals("P")) {
            vHolder.binding.status.setTextColor(context.getColor(R.color.green));
        }
        if (employeeList.get(position).getAttendance().equals("O")) {
            vHolder.binding.status.setTextColor(context.getColor(R.color.orange));
        }
        if (employeeList.get(position).getAttendance().equals("L")) {
            vHolder.binding.status.setTextColor(context.getColor(R.color.primary_color));
        }
        if (employeeList.get(position).getAttendance().equals("H")) {
            vHolder.binding.status.setTextColor(context.getColor(R.color.yellow));
        }
        vHolder.binding.status.setText(employeeList.get(position).getAttendance());
        vHolder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", employeeList.get(position).getId());
                Navigation.findNavController(view).navigate(R.id.employeeDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EmployeeDayAttendanceListBinding binding;

        public ViewHolder(@NonNull EmployeeDayAttendanceListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
