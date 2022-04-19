package com.bdappmaniac.bdapp.adapter;

import static android.os.Build.ID;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeList;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.DesiginLockUnlockItemsBinding;
import com.bdappmaniac.bdapp.databinding.DesignRecyclerMenuBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.model.LockUnlockModel;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class LockUnlockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<EmployeeList> employeeList = new ArrayList<>();
    Context context;

    public LockUnlockAdapter(ArrayList<EmployeeList> employeeList, Context context) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder gholder = (ViewHolder) holder;
        gholder.binding.empName.setText(employeeList.get(position).getEmployeeName());
        gholder.binding.switchbtn.setChecked(true);

        if (employeeList.get(position).getStatus().equals("active")) {
            gholder.binding.switchbtn.setChecked(true);
//            gholder.binding.time.setTextColor(context.getColor(R.color.light_primary_color));
//
        } else if (employeeList.get(position).getStatus().equals("inactive")) {
            gholder.binding.switchbtn.setChecked(false);
//            gholder.binding.time.setTextColor(context.getColor(R.color.secondary_color));

        }

        gholder.binding.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    gholder.updateEmployeeStatusApi("active", employeeList.get(position).getId());
//                    gholder.binding.time.setTextColor(context.getColor(R.color.light_primary_color));
                } else {
                    gholder.updateEmployeeStatusApi("inactive", employeeList.get(position).getId());
//                    gholder.binding.time.setTextColor(context.getColor(R.color.secondary_color));
                }
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
                            ((BaseActivity) context).showSnackBar(binding.getRoot(), context.getString(R.string.you_are_active));
                        } else if (status.equals("inactive")) {
                            ((BaseActivity) context).showSnackBar(binding.getRoot(), context.getString(R.string.you_are_inactive));
                        }
                    } else {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), context.getString(R.string.something_went_wrong));
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }

    }


}
