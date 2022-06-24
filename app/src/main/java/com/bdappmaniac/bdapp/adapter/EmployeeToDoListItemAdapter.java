package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.DesignTasksItemsBinding;
import com.bdappmaniac.bdapp.model.ModelChildTaskList;
import com.bdappmaniac.bdapp.model.ModelEmpTask;

import java.util.ArrayList;

public class EmployeeToDoListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    //    List<TasksItem> list = new ArrayList<>();
    ArrayList<ModelEmpTask> list;

    public EmployeeToDoListItemAdapter(Context context, ArrayList<ModelEmpTask> list) {
        this.context = context;
        this.list = list;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignTasksItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.design_tasks_items, group, false);
        return new EmployeeToDoListItemAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeToDoListItemAdapter.ViewHolder vHolder = (EmployeeToDoListItemAdapter.ViewHolder) holder;
        vHolder.binding.date.setText(list.get(position).getDate());
        ArrayList<ModelChildTaskList> childTaskList = new ArrayList<>();
        childTaskList.add(new ModelChildTaskList("20-03-2022", "Fix the bug ",
                "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        childTaskList.add(new ModelChildTaskList("21-03-2022", "Comple the remaining screens in  time",
                "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        childTaskList.add(new ModelChildTaskList("23-03-2022", "App  working flow is not correct ",
                "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        adminChildTaskAdapter adapter = new adminChildTaskAdapter(context, childTaskList);
        vHolder.binding.childRecycler.setLayoutManager(new LinearLayoutManager(context));
        vHolder.binding.childRecycler.setAdapter(adapter);
//        vHolder.binding.dateText.setText(DateUtils.getFormattedTime(list.get(position).getDate(), DateUtils.appDateFormat, DateUtils.appDateFormatM));
////        vHolder.binding.dateText.setText(DateUtils.getDateCurrentTimeZone(Long.parseLong(list.get(position).getCreated_at())));
////        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
////        cal.setTimeInMillis(Long.parseLong(list.get(position).getCreated_at()) * 1000L);
////        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
////        vHolder.binding.dateText.setText(date);
////
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, uuuu hh:mm:ss a", Locale.ENGLISH);
////        String aDate = list.get(position).getCreated_at();
////        String formattedDate = LocalDateTime.parse(aDate, formatter)
////                .atOffset(ZoneOffset.UTC)
////                .atZoneSameInstant(ZoneId.systemDefault())
////                .format(formatter);
////        vHolder.binding.dateText.setText(formattedDate);
//        vHolder.binding.dropDownBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vHolder.binding.constraintLayout2.setVisibility(View.VISIBLE);
//                vHolder.binding.historyItem.setBackground(context.getDrawable(R.drawable.auth_white_bg));
//                vHolder.binding.dropDownBtn.setVisibility(View.GONE);
//                vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
//            }
//        });
//        vHolder.binding.DropUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                vHolder.binding.historyItem.setBackground(context.getDrawable(R.drawable.edit_text_bg));
//                vHolder.binding.constraintLayout2.setVisibility(View.GONE);
//                vHolder.binding.dropDownBtn.setVisibility(View.VISIBLE);
//                vHolder.binding.DropUpBtn.setVisibility(View.GONE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DesignTasksItemsBinding binding;

        public ViewHolder(@NonNull DesignTasksItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
