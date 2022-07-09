package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.AdminChildTaskItemBinding;
import com.bdappmaniac.bdapp.databinding.AdminTaskAdapterBottomSheetBinding;
import com.bdappmaniac.bdapp.databinding.EditTaskDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class adminChildTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TasksItem> taskItem;
    private int TYear, TMonth, TDay;

    public adminChildTaskAdapter(Context context, List<TasksItem> taskItem) {
        this.context = context;
        this.taskItem = taskItem;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        AdminChildTaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.admin_child_task_item, group, false);
        return new adminChildTaskAdapter.TaskViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        adminChildTaskAdapter.TaskViewHolder vHolder = (adminChildTaskAdapter.TaskViewHolder) holder;
        vHolder.binding.taskHeading.setText(taskItem.get(position).getTitle());
        vHolder.binding.discription.setText(taskItem.get(position).getContent());
        vHolder.binding.completionDate.setText(taskItem.get(position).getDeadline());
        vHolder.binding.completeBtn.setText(taskItem.get(position).getStatus());

        vHolder.binding.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AdminTaskAdapterBottomSheetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.admin_task_adapter_bottom_sheet, null, false);
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(binding.getRoot());
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                binding.editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vHolder.updateTaskDialogBox();
                        dialog.dismiss();
                    }
                });
                binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        vHolder.removeTaskApi(taskItem.get(position).getId());
                    }
                });
                binding.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskItem == null ? 0 : taskItem.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        AdminChildTaskItemBinding binding;

        public TaskViewHolder(@NonNull AdminChildTaskItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updateTaskApi(int id, String title, String content, String deadline) {
            AppLoader.showLoaderDialog(context);
            Map<String, RequestBody> map = new HashMap<>();
            map.put("title", toRequestBody(title));
            map.put("content", toRequestBody(content));
            map.put("deadline", toRequestBody(deadline));
            MainService.updateTask(context, ((BaseActivity) context).getToken(), id, map).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        taskItem.set(getAdapterPosition(), new TasksItem(title, content, deadline));
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }

        public void updateTaskDialogBox() {
            EditTaskDialogBinding taskBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.edit_task_dialog, null, false);
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(taskBinding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            taskBinding.titleTxt.setText(binding.taskHeading.getText().toString());
            taskBinding.descriptionTxt.setText(binding.discription.getText().toString());
            taskBinding.completionDateTxt.setText(binding.completionDate.getText().toString());
            taskBinding.completionDateTxt.setOnClickListener(view -> {
                final Calendar c = Calendar.getInstance();
                TYear = c.get(Calendar.YEAR);
                TMonth = c.get(Calendar.MONTH);
                TDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DatePicker, (view1, year, monthOfYear, dayOfMonth) -> {
                    taskBinding.completionDateTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }, TYear, TMonth, TDay);
                datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            });
            taskBinding.updateBtn.setOnClickListener(view -> {
                updateTaskApi(taskItem.get(getAdapterPosition()).getId(), taskBinding.titleTxt.getText().toString(), taskBinding.descriptionTxt.getText().toString(), taskBinding.completionDateTxt.getText().toString());
                dialog.dismiss();
            });
            taskBinding.cancelBtn.setOnClickListener(view -> dialog.dismiss());
        }

        private void removeTaskApi(int id) {
            AppLoader.showLoaderDialog(context);
            MainService.removeTask(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showSnackBar(binding.getRoot(), context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        taskItem.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }

        public RequestBody toRequestBody(String val) {
            RequestBody requestBody = null;
            if (context != null) {
                requestBody = toRequestBodyPart(val);
            }
            return requestBody;
        }

        public RequestBody toRequestBodyPart(String value) {
            return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
        }
    }
}

