package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.CalendarMonthItemBinding;
import com.bdappmaniac.bdapp.fragment.HistoryFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> month = new ArrayList<>();
    Calendar c = Calendar.getInstance();
    int currentMonth = c.get(Calendar.MONTH);
    HistoryFragment historyFragment;

    public MonthCalendarAdapter(Context context, ArrayList<String> month, HistoryFragment historyFragment) {
        this.context = context;
        this.month = month;
        this.historyFragment = historyFragment;
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
        CalendarMonthItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.calendar_month_item, group, false);
        return new MonthCalendarAdapterHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MonthCalendarAdapter.MonthCalendarAdapterHolder calendarHolder = (MonthCalendarAdapter.MonthCalendarAdapterHolder) holder;
        calendarHolder.binding.date.setText(month.get(position));
        calendarHolder.setDataBind(position);
    }

    @Override
    public int getItemCount() {
        return month.size();
    }

    public class MonthCalendarAdapterHolder extends RecyclerView.ViewHolder {
        CalendarMonthItemBinding binding;

        public MonthCalendarAdapterHolder(CalendarMonthItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
        @RequiresApi(api = Build.VERSION_CODES.M)
        void setDataBind(int position) {
            if (currentMonth == position) {
                binding.itemBg.setBackgroundColor(Color.parseColor("#152F50"));
                binding.indicate.setImageResource(R.drawable.circle_orange_bg);
                binding.date.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                binding.itemBg.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.indicate.setImageResource(R.drawable.round_green_bg);
                binding.date.setTextColor(Color.parseColor("#ADADAD"));
            }
            binding.itemBg.setOnClickListener(view -> {
                currentMonth = position;
                notifyDataSetChanged();
                historyFragment.selectedMonth(month.get(position));
            });
        }
    }

    void dataSelect() {

    }


}

