package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.lockUnlockItems;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.DesiginLockUnlockItemsBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.ArrayList;

public class LockUnlockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<lockUnlockItems> employeeList;
    Context context;

    public LockUnlockAdapter(ArrayList<lockUnlockItems> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesiginLockUnlockItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.desigin_lock_unlock_items, group, false);
        return new LockUnlockAdapter.ViewHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LockUnlockAdapter.ViewHolder vHolder = (LockUnlockAdapter.ViewHolder) holder;
        vHolder.binding.empName.setText(employeeList.get(position).getEmployeeName());
        if (employeeList.get(position).getDate() == null){
            vHolder.binding.time.setText("");
        }else {
            vHolder.binding.time.setText(DateUtils.getFormattedTime(employeeList.get(position).getDate(), DateUtils.appDateFormat, DateUtils.appDateFormatM));
        }
        vHolder.binding.switchbtn.setChecked(true);

        if (employeeList.get(position).getStatus().equals("active")) {
            vHolder.binding.switchbtn.setChecked(true);
        } else if (employeeList.get(position).getStatus().equals("inactive")) {
            vHolder.binding.switchbtn.setChecked(false);
        }
        vHolder.binding.switchbtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                vHolder.updateEmployeeStatusApi("active", employeeList.get(position).getId());
            } else {
                vHolder.updateEmployeeStatusApi("inactive", employeeList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DesiginLockUnlockItemsBinding binding;

        public ViewHolder(@NonNull DesiginLockUnlockItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        private void updateEmployeeStatusApi(String status, int id) {
            AppLoader.showLoaderDialog(context);
            MainService.updateProfileByAdmin(context, ((BaseActivity) context).getToken(), id, status).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showSnackBar(binding.getRoot(), context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        if (status.equals("active")) {
                            ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        } else if (status.equals("inactive")) {
                            ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        }
                    }
//                    else {
//                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
//                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }
    }
}