package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bdappmaniac.bdapp.model.CalendarDateModel;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.employee.fragment.HomeFragment;
import com.bdappmaniac.bdapp.databinding.RowCalendarDateBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<CalendarDateModel> list = new ArrayList<>();
    int currentPos;
    boolean checkLast = false;
    HomeFragment homeFragment;

    public CalendarAdapter(Context context, int TodayDate, HomeFragment homeFragment) {
        this.context = context;
        currentPos = TodayDate;
        this.homeFragment = homeFragment;
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
        RowCalendarDateBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_calendar_date, group, false);
        return new CalendarHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CalendarHolder calendarHolder = (CalendarHolder) holder;
        calendarHolder.binding.day.setText(String.valueOf(list.get(position).getDay()));
        calendarHolder.binding.date.setText(String.valueOf(list.get(position).getDates().getDate()));
        calendarHolder.setDataBind(position);
        if (!checkLast && currentPos == list.get(position).getDates().getDate()) {
            calendarHolder.binding.itemBg.setBackgroundColor(Color.parseColor("#152F50"));
            calendarHolder.binding.indicate.setImageResource(R.drawable.circle_orange_bg);
            calendarHolder.binding.date.setTextColor(Color.parseColor("#FFFFFF"));
            calendarHolder.binding.day.setTextColor(Color.parseColor("#FFFFFF"));
            checkLast = true;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {
        RowCalendarDateBinding binding;

        public CalendarHolder(RowCalendarDateBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
        @RequiresApi(api = Build.VERSION_CODES.M)
        void setDataBind(int position) {
            if (currentPos == position) {
                binding.itemBg.setBackgroundColor(Color.parseColor("#152F50"));
                binding.indicate.setImageResource(R.drawable.circle_orange_bg);
                binding.date.setTextColor(Color.parseColor("#FFFFFF"));
                binding.day.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                binding.itemBg.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.indicate.setImageResource(R.drawable.round_green_bg);
                binding.date.setTextColor(Color.parseColor("#ADADAD"));
                binding.day.setTextColor(Color.parseColor("#002460"));
            }
            binding.itemBg.setOnClickListener(view -> {
                currentPos = position;
                notifyDataSetChanged();
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(list.get(position).getDates());

                Toast.makeText(context, "Get Date : " + date, Toast.LENGTH_SHORT).show();
            });
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<CalendarDateModel> calendarList) {
        list.clear();
        list.addAll(calendarList);
        notifyDataSetChanged();
    }
}

