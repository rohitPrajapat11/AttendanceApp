package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeDesItem;
import com.bdappmaniac.bdapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeDesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<EmployeeDesItem> employeeList = new ArrayList<>();
    Context context;
    int selectedView;

    public EmployeeDesListAdapter(ArrayList<EmployeeDesItem> employeeList, Context context, int selectedView) {
        this.employeeList = employeeList;
        this.context = context;
        this.selectedView = selectedView;
    }

//    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
//        EmployeeDayAttendanceListBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_day_attendance_list, group, false);
//        return new EmployeeDesListAdapter.GridViewHolder(binding);
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        switch (selectedView) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return getViewHolder(LayoutInflater.from(context), parent);
        switch (selectedView) {
            case 0:
                View layoutOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_day_attendance_list, parent, false);
                return new EmployeeDesListAdapter.GridViewHolder(layoutOne);
            case 1:
                View layoutTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item, parent, false);
                return new EmployeeDesListAdapter.EmployeeListHolder(layoutTwo);
            default:
                return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        switch (selectedView) {
            case 0:
                EmployeeDesListAdapter.GridViewHolder vHolder = (EmployeeDesListAdapter.GridViewHolder) holder;
                vHolder.empName.setText(employeeList.get(position).getEmployeeName());
                vHolder.designation.setText(employeeList.get(position).getDesignation());
                Glide.with(context).load(employeeList.get(position).getProfile()).placeholder(R.drawable.user).into(vHolder.profile);
                if (employeeList.get(position).getAttendance().equals("A")) {
                    vHolder.status.setTextColor(context.getColor(R.color.secondary_color));
                }
                if (employeeList.get(position).getAttendance().equals("P")) {
                    vHolder.status.setTextColor(context.getColor(R.color.green));
                }
                if (employeeList.get(position).getAttendance().equals("O")) {
                    vHolder.status.setTextColor(context.getColor(R.color.orange));
                }
                if (employeeList.get(position).getAttendance().equals("L")) {
                    vHolder.status.setTextColor(context.getColor(R.color.primary_color));
                }
                if (employeeList.get(position).getAttendance().equals("H")) {
                    vHolder.status.setTextColor(context.getColor(R.color.yellow));
                }
                vHolder.status.setText(employeeList.get(position).getAttendance());
                vHolder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", employeeList.get(position).getId());
                        Navigation.findNavController(view).navigate(R.id.employeeDetailFragment, bundle);
                    }
                });
                break;

            case 1:
                EmployeeDesListAdapter.EmployeeListHolder vHolders = (EmployeeDesListAdapter.EmployeeListHolder) holder;
                vHolders.empName.setText(employeeList.get(position).getEmployeeName());
                vHolders.designation.setText(employeeList.get(position).getDesignation());
                Glide.with(context).load(employeeList.get(position).getProfile()).placeholder(R.drawable.user).into(vHolders.profile);
                vHolders.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", employeeList.get(position).getId());
                        Navigation.findNavController(view).navigate(R.id.employeeDetailFragment, bundle);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        View binding;
        TextView empName, designation, status;
        CircleImageView profile;
        ConstraintLayout item;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = itemView;
            empName = binding.findViewById(R.id.empName);
            designation = binding.findViewById(R.id.designation);
            status = binding.findViewById(R.id.status);
            profile = binding.findViewById(R.id.profile);
            item = binding.findViewById(R.id.item);
        }
    }

    public class EmployeeListHolder extends RecyclerView.ViewHolder {
        View binding;
        TextView empName, designation;
        CircleImageView profile;
        ConstraintLayout item;

        public EmployeeListHolder(View itemView) {
            super(itemView);
            binding = itemView;
            empName = binding.findViewById(R.id.employeeName);
            designation = binding.findViewById(R.id.designationTxt);
            profile = binding.findViewById(R.id.profile);
            item = binding.findViewById(R.id.item);
        }
    }
}
