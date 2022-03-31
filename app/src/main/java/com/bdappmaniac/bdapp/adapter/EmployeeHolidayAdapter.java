package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.EmployeeHolidayItemBinding;
import com.bdappmaniac.bdapp.databinding.HolidayBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EmployeeHolidayAdapter.EmployeeHolidayHolder vHolder = (EmployeeHolidayAdapter.EmployeeHolidayHolder) holder;
        vHolder.binding.theDate.setText(list.get(position).getDate());
        vHolder.binding.theReason.setText(list.get(position).getName());
        SharedPref.init(context);
        if(SharedPref.getUserDetails().getType().equals("employee"))
        {
            vHolder.binding.moreBtn.setVisibility(View.GONE);
        }
        vHolder.binding.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HolidayBottomSheetDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.holiday_bottom_sheet_dialog, null, false);
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(binding.getRoot());
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vHolder.removeHolidayApi(list.get(position).getId());
                        dialog.dismiss();
                    }
                });
                binding.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
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

        public void removeHolidayApi(int id) {
            AppLoader.showLoaderDialog(context);
            MainService.removeHoliday(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), "Holiday Removed Successfully");
                        list.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }
    }
}
