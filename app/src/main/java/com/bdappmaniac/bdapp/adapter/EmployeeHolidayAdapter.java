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
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.AdminEditHolidayDilogboxBinding;
import com.bdappmaniac.bdapp.databinding.EmployeeHolidayItemBinding;
import com.bdappmaniac.bdapp.databinding.HolidayBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EmployeeHolidayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HolidaysItem> list;
    int position;
    private int TYear, TMonth, TDay;

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
        if (list.get(position).getDate().equals("Saturday Holiday")) {
            vHolder.binding.date.setText(list.get(position).getDate());
        } else {
            vHolder.binding.date.setText(DateUtils.getFormattedTime(list.get(position).getDate(), DateUtils.appDateFormat, DateUtils.appDateFormatM));
        }
        vHolder.binding.holidays.setText(list.get(position).getName());
        SharedPref.init(context);
        if (SharedPref.getUserDetails().getType().equals("employee")) {
            vHolder.binding.item.setFocusable(false);
            vHolder.binding.item.setEnabled(false);
        }
        vHolder.binding.item.setOnLongClickListener(view -> {
            if (!list.get(position).getDate().equals("Saturday Holiday")) {
                String date = vHolder.binding.date.getText().toString();
                String name = vHolder.binding.holidays.getText().toString();
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
                binding.deleteBtn.setOnClickListener(view13 -> {
                    vHolder.removeHolidayApi(list.get(position).getId());
                    dialog.dismiss();
                });
                binding.editBtn.setOnClickListener(view12 -> {
                    vHolder.updateHolidayDialogBox(date, name);
                    dialog.dismiss();
                });
                binding.cancel.setOnClickListener(view1 -> dialog.dismiss());
            }
            return false;
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        if (context != null) {
            requestBody = toRequestBodyPart(val);
        }
        return requestBody;
    }

    public void setList(List<HolidaysItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }

    public class EmployeeHolidayHolder extends RecyclerView.ViewHolder {
        EmployeeHolidayItemBinding binding;

        public EmployeeHolidayHolder(EmployeeHolidayItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updateHolidayApi(int id, String name, String date) {
            AppLoader.showLoaderDialog(context);
            Map<String, RequestBody> map = new HashMap<>();
            map.put("name", toRequestBody(name));
            map.put("date", toRequestBody(date));
            MainService.updateHoliday(context, ((BaseActivity) context).getToken(), id, map).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        String str = DateUtils.getFormattedTime(date, DateUtils.appDateFormat, DateUtils.appDateFormatM);
                        list.set(getAdapterPosition(), new HolidaysItem(date, name, id));
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }

        private void updateHolidayDialogBox(String date, String name) {
            AdminEditHolidayDilogboxBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.admin_edit_holiday_dilogbox, null, false);
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            binding.dateTxt.setText(date);
            binding.reasonTxt.setText(name);
            dialog.show();
            binding.dateTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    TYear = c.get(Calendar.YEAR);
                    TMonth = c.get(Calendar.MONTH);
                    TDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DatePicker,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    String s = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    binding.dateTxt.setText(DateUtils.getFormattedTime(s, DateUtils.appDateFormatTo, DateUtils.appDateFormatM));
                                }
                            }, TYear, TMonth, TDay);

//                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DatePicker,
//                            (view1, year, monthOfYear, dayOfMonth) ->
//                            {
//                                String s = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                binding.dateTxt.setText(DateUtils.getFormattedTime(s, DateUtils.appDateFormat, DateUtils.appDateFormatM));
//                            }, TYear, TMonth, TDay);
                    datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    datePickerDialog.show();
                }
            });
            binding.updateBtn.setOnClickListener(view -> {
                String name1 = binding.reasonTxt.getText().toString();
                String date1 = DateUtils.getFormattedTime(binding.dateTxt.getText().toString(), DateUtils.appDateFormatM, DateUtils.appDateFormat);
                updateHolidayApi(list.get(getAdapterPosition()).getId(), name1, date1);
                dialog.dismiss();
            });
            binding.cancelBtn.setOnClickListener(view -> dialog.dismiss());
        }

        public void removeHolidayApi(int id) {
            AppLoader.showLoaderDialog(context);
            MainService.removeHoliday(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        list.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }
    }
}

