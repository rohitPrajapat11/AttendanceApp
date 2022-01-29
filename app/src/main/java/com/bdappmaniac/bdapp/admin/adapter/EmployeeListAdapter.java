package com.bdappmaniac.bdapp.admin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.EmployeeListItemBinding;
import com.bdappmaniac.bdapp.model.EmployeeListModel;

import java.util.ArrayList;

public class EmployeeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<EmployeeListModel> list = new ArrayList<>();
    String from;


    public EmployeeListAdapter(Context context, ArrayList<EmployeeListModel> list, String from) {
        this.context = context;
        this.list = list;
        this.from = from;
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
        EmployeeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_list_item, group, false);
        return new EmployeeListAdapter.EmployeeListHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EmployeeListAdapter.EmployeeListHolder vHolder = (EmployeeListAdapter.EmployeeListHolder) holder;
        vHolder.binding.employeeName.setText(list.get(position).getName());
        vHolder.binding.designationTxt.setText(list.get(position).getDesignation());
        vHolder.binding.profile.setImageResource(list.get(position).getImage());
        vHolder.itemView.setOnClickListener(v -> {
            if (from.equals("LoanFragment")) {
                Toast.makeText(context, "L", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("image", list.get(position).getImage());
                bundle.putString("name", list.get(position).getName());
                Navigation.findNavController(v).navigate(R.id.provideLoanFragment, bundle);
            } else if (from.equals("EmployeeList")) {
                Toast.makeText(context, "E", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("image", list.get(position).getImage());
                bundle.putString("name", list.get(position).getName());
                bundle.putString("designation", list.get(position).getDesignation());
                Navigation.findNavController(v).navigate(R.id.employeeDetailFragment, bundle);
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmployeeListHolder extends RecyclerView.ViewHolder {
        EmployeeListItemBinding binding;

        public EmployeeListHolder(EmployeeListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

