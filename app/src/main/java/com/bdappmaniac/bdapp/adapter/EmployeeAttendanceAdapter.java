package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeHistoryDataItem;
import com.bdappmaniac.bdapp.Api.response.InoutsItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.AdminHistoryItemBinding;
import com.bdappmaniac.bdapp.databinding.AdminHistoryListItemBinding;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmployeeHistoryDataItem> List;
    List<InoutsItem> historyList = new ArrayList<>();
    EmployeeAttendanceListAdapter adapter;
    String getDate, getInTime, getOutTime, getAbTxt;
    private int mYear, mMonth, mDay;

    public EmployeeAttendanceAdapter(Context context, List<EmployeeHistoryDataItem> List) {
        this.context = context;
        this.List = (List<EmployeeHistoryDataItem>) List;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        AdminHistoryItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.admin_history_item, group, false);
        return new EmployeeAttendanceAdapter.HistoryHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EmployeeAttendanceAdapter.HistoryHolder vHolder = (EmployeeAttendanceAdapter.HistoryHolder) holder;
        vHolder.binding.dateText.setText(DateUtils.getFormattedTime(List.get(position).getDate(), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        vHolder.binding.dayTxt.setText(DateUtils.getDayFromDate(List.get(position).getDate()));
        vHolder.binding.moreBtn.setOnClickListener(view -> workHistoryDialogBox(position));
        for (int x = 0; x < List.get(position).getInouts().size(); x++) {
            if (List.get(position).getInouts().get(x).getLogIn() == null && List.get(position).getInouts().get(x).getLogOut() == null) {
                vHolder.binding.absentTxt.setVisibility(View.VISIBLE);
                vHolder.binding.reasonTxt.setVisibility(View.VISIBLE);
                vHolder.binding.totalTxt.setVisibility(View.VISIBLE);
                vHolder.binding.breakTxt.setVisibility(View.VISIBLE);
                vHolder.binding.totalLb.setVisibility(View.VISIBLE);
                vHolder.binding.breakLb.setVisibility(View.VISIBLE);
            }
            if (List.get(position).getInouts().get(x).getAbsentReason() != null) {
                vHolder.binding.reasonTxt.setText(List.get(position).getInouts().get(x).getAbsentReason().toString());
            }
        }
//        vHolder.binding.dropDownBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vHolder.binding.constraintLayout2.setVisibility(View.VISIBLE);
//                vHolder.binding.dropDownBtn.setVisibility(View.GONE);
//                vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
//                if (List.get(position).getInouts() != null) {
//                    HistoryListAdapter historyListAdapter = new HistoryListAdapter(context, List.get(position).getInouts());
//                    vHolder.binding.historyListRecyclers.setLayoutManager(new LinearLayoutManager(context));
//                    vHolder.binding.historyListRecyclers.setAdapter(historyListAdapter);
//                } else {
//                    vHolder.binding.constraintLayout2.setVisibility(View.GONE);
//                    vHolder.binding.dropDownBtn.setVisibility(View.GONE);
//                    vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        vHolder.binding.DropUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vHolder.binding.constraintLayout2.setVisibility(View.GONE);
//                vHolder.binding.dropDownBtn.setVisibility(View.VISIBLE);
//                vHolder.binding.DropUpBtn.setVisibility(View.GONE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void workHistoryDialogBox(int pos) {
        List<InoutsItem> items = List.get(pos).getInouts();
        AdminHistoryListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.admin_history_list_item, null, false);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        adapter = new EmployeeAttendanceListAdapter(context, items);
        binding.recycleView.setHasFixedSize(false);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(context));
        binding.recycleView.setAdapter(adapter);
        for (int x = 0; x < items.size(); x++) {
            if (items.get(x).getLogOut() != null && items.get(x).getLogIn() != null) {
                binding.checkIn.setVisibility(View.VISIBLE);
                binding.checkOut.setVisibility(View.VISIBLE);
            }
        }
        dialog.show();
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {
        AdminHistoryItemBinding binding;

        public HistoryHolder(AdminHistoryItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

