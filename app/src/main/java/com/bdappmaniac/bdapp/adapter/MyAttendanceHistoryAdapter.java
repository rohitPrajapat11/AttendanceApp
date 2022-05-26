package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignAttendanceHistoryBinding;
import com.bdappmaniac.bdapp.model.ModelEntries;
import com.bdappmaniac.bdapp.model.ModelMyAttendenceHistory;

import java.util.ArrayList;

public class MyAttendanceHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    AtteEntriesAdapter adapter;
    ArrayList<ModelMyAttendenceHistory> HistoryList = new ArrayList<>();
    Context context;


    public MyAttendanceHistoryAdapter(ArrayList<ModelMyAttendenceHistory> historyList, Context context) {
        HistoryList = historyList;
        this.context = context;
    }
    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group){
        DesignAttendanceHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_attendance_history,group,false);
        return new ViewHolder(binding);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyAttendanceHistoryAdapter.ViewHolder vholder = (MyAttendanceHistoryAdapter.ViewHolder)holder;
        vholder.binding.date.setText(HistoryList.get(position).getDate());
        vholder.binding.day.setText(HistoryList.get(position).getDay());

        ArrayList<ModelEntries> eList = new ArrayList<>();
        eList.add(new ModelEntries("09:38 AM","06:40 PM","09:08 Hr"));
        eList.add(new ModelEntries("09:38 AM","06:40 PM","09:08 Hr"));
        eList.add(new ModelEntries("09:38 AM","06:40 PM","09:08 Hr"));
        eList.add(new ModelEntries("09:38 AM","06:40 PM","09:08 Hr"));


        AtteEntriesAdapter adapter = new AtteEntriesAdapter(eList,context);
        vholder.binding.recycleerEntries.setLayoutManager(new LinearLayoutManager(context));
        vholder.binding.recycleerEntries.setAdapter(adapter);



        vholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vholder.binding.recycleerEntries.getVisibility()==View.VISIBLE)
                {
                    vholder.binding.recycleerEntries.setVisibility(View.GONE);
                    vholder.binding.checkInOutTexts.setVisibility(View.VISIBLE);
                }
                else {
                    vholder.binding.recycleerEntries.setVisibility(View.VISIBLE);
                    vholder.binding.checkInOutTexts.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return HistoryList.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        DesignAttendanceHistoryBinding binding;
        public ViewHolder(@NonNull DesignAttendanceHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }



}
