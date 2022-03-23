package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeHolidayList;
import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.EmployeeHolidayItemBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<HolidaysItem> list = new ArrayList<>();

    public EmployeeHolidayAdapter(Context context, List<HolidaysItem> list) {
        this.context = context;
        this.list = (List<HolidaysItem>) list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        EmployeeHolidayItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.employee_holiday_item, group, false);
        return new EmployeeHolidayAdapter.EmployeeHolidayHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeHolidayAdapter.EmployeeHolidayHolder vHolder = (EmployeeHolidayAdapter.EmployeeHolidayHolder) holder;
        vHolder.binding.theDate.setText(list.get(position).getDate());
        vHolder.binding.theReason.setText(list.get(position).getName());
        vHolder.binding.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmployeeHolidayHolder extends RecyclerView.ViewHolder {
        EmployeeHolidayItemBinding binding;

        public EmployeeHolidayHolder(EmployeeHolidayItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(context, v);
        popup.getMenu().add("Remove");
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
        popup.show();
    }
}
