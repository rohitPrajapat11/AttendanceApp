package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignRecyclerMenuBinding;
import com.bdappmaniac.bdapp.model.AdminHomeModel;

import java.util.ArrayList;

public class AdminHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AdminHomeModel> itemList = new ArrayList<>();
    Context context;

    public AdminHomeAdapter(ArrayList<AdminHomeModel> itemList, Context context) {
        this.context = context;
        this.itemList = itemList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignRecyclerMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_recycler_menu, group, false);
        return new AdminHomeAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ViewHolder vHolder = (ViewHolder) holder;
        vHolder.binding.icon.setImageResource(itemList.get(position).getImageitem());
        vHolder.binding.title.setText(itemList.get(position).getTitle());
        vHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    Navigation.findNavController(view).navigate(R.id.loanFragment);
                }
                if (position == 1) {
                    Navigation.findNavController(view).navigate(R.id.employeeAttandenceListFragment);
                }
//                if (position == 2) {
//                    Navigation.findNavController(view).navigate(R.id.employeeDesListFragment);
//                }
                if (position == 2) {
                    Navigation.findNavController(view).navigate(R.id.employeeExpensesFragment);
                }
                if (position == 3) {
                    Navigation.findNavController(view).navigate(R.id.adminHolidayFragment);
                }
                if (position == 4) {
                    Navigation.findNavController(view).navigate(R.id.lockUnlockFragment);
                }
//                if (position == 6) {
//                    Navigation.findNavController(view).navigate(R.id.overTimeFragment);
//                }
//                if (position == 7) {
//                    Navigation.findNavController(view).navigate(R.id.toDoListFragment);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignRecyclerMenuBinding binding;

        public ViewHolder(@NonNull DesignRecyclerMenuBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
