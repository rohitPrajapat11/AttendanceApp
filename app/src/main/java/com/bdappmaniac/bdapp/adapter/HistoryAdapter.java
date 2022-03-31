package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.EmployeeHistoryDataItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.HistoryItemBinding;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<EmployeeHistoryDataItem> List = new ArrayList<>();
    String getDate, getInTime, getOutTime, getAbTxt;
    private int mYear, mMonth, mDay;

    public HistoryAdapter(Context context, List<EmployeeHistoryDataItem> List) {
        this.context = context;
        this.List = (List<EmployeeHistoryDataItem>) List;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        HistoryItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.history_item, group, false);
        return new HistoryAdapter.HistoryHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HistoryAdapter.HistoryHolder vHolder = (HistoryAdapter.HistoryHolder) holder;
        vHolder.binding.dateText.setText(DateUtils.getFormattedTime(List.get(position).getDate(),DateUtils.appDateFormat,DateUtils.appDateFormatTo));
        vHolder.binding.dayTxt.setText(DateUtils.getDayFromDate(List.get(position).getDate()));
        vHolder.binding.dropDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vHolder.binding.constraintLayout2.setVisibility(View.VISIBLE);
                vHolder.binding.dropDownBtn.setVisibility(View.GONE);
                vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
                if (List.get(position).getInouts() != null) {
                    HistoryListAdapter historyListAdapter = new HistoryListAdapter(context, List.get(position).getInouts());
                    vHolder.binding.historyListRecyclers.setLayoutManager(new LinearLayoutManager(context));
                    vHolder.binding.historyListRecyclers.setAdapter(historyListAdapter);
                } else {
                    vHolder.binding.constraintLayout2.setVisibility(View.GONE);
                    vHolder.binding.dropDownBtn.setVisibility(View.GONE);
                    vHolder.binding.DropUpBtn.setVisibility(View.VISIBLE);
                }
            }
        });
        vHolder.binding.DropUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vHolder.binding.constraintLayout2.setVisibility(View.GONE);
                vHolder.binding.dropDownBtn.setVisibility(View.VISIBLE);
                vHolder.binding.DropUpBtn.setVisibility(View.GONE);
            }
        });
    }

    //    private void CheckInAndOutDialog(HistoryAdapterHolder vHolder) {
//        CheckInAndOutDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.check_in_and_out_dialog, null, false);
//        Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setContentView(binding.getRoot());
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setGravity(Gravity.CENTER);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        binding.checkInTime.setText(vHolder.binding.checkInTimes.getText());
//        binding.checkOutTime.setText(vHolder.binding.checkOutTimes.getText());
//        binding.dateTxt.setText(vHolder.binding.dateText.getText());
//        binding.absentReasonTxt.setText(vHolder.binding.reasonTxt.getText());
//        binding.checkInTime.setOnClickListener(view ->
//        {
//            showTime(binding.checkInTime);
//            getInTime = binding.checkInTime.getText().toString();
//        });
//        binding.checkOutTime.setOnClickListener(view -> {
//            showTime(binding.checkOutTime);
//            getOutTime = binding.checkOutTime.getText().toString();
//        });
//        binding.dateTxt.setOnClickListener(view -> {
//            showDate(binding.dateTxt);
//            getDate = binding.dateTxt.getText().toString();
//        });
//        binding.absentReasonTxt.setOnClickListener(view -> {
//            getAbTxt = binding.absentReasonTxt.getText().toString();
//        });
//        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                binding.saveBtn.setVisibility(View.GONE);
//            }
//        });
//        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                binding.cancelBtn.setVisibility(View.GONE);
//            }
//        });
//        binding.absentCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    binding.checkInLayout.setVisibility(View.GONE);
//                    binding.absentReasonTxt.setVisibility(View.VISIBLE);
//                    binding.checkOutLayout.setVisibility(View.GONE);
//                    binding.cancelBtn.setVisibility(View.VISIBLE);
//                    binding.saveBtn.setVisibility(View.VISIBLE);
//                    binding.saveAllBtn.setVisibility(View.GONE);
//                } else {
//                    binding.checkInLayout.setVisibility(View.VISIBLE);
//                    binding.checkOutLayout.setVisibility(View.VISIBLE);
//                    binding.absentReasonTxt.setVisibility(View.GONE);
//                    binding.saveAllBtn.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        dialog.show();
//    }
//
//    public void showTime(TextView textView) {
//
//        final Calendar myCalendar = Calendar.getInstance();
//        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//            @SuppressLint("SetTextI18n")
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                String am_pm = "";
//                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                myCalendar.set(Calendar.MINUTE, minute);
//                if (myCalendar.get(Calendar.AM_PM) == Calendar.AM)
//                    am_pm = "AM";
//                else if (myCalendar.get(Calendar.AM_PM) == Calendar.PM)
//                    am_pm = "PM";
//                String strHrsToShow = (myCalendar.get(Calendar.HOUR) == 0) ? "12" : myCalendar.get(Calendar.HOUR) + "";
//                int minutes = myCalendar.get(Calendar.MINUTE);
//                String sMin = "";
//                if (Integer.parseInt(strHrsToShow) < 10) {
//                    strHrsToShow = "0" + strHrsToShow;
//                }
//                if (minutes < 10) {
//                    sMin = "0" + minutes;
//                } else {
//                    sMin = String.valueOf(minutes);
//                }
//                //UIHelper.showLongToastInCenter(context, strHrsToShow + ":" + myCalendar.get(Calendar.MINUTE) + " " + am_pm);
//                textView.setText(strHrsToShow + ":" + sMin + " " + am_pm);
//            }
//        };
//        new TimePickerDialog(textView.getContext(), R.style.DatePicker, mTimeSetListener, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), false).show();
//    }
//
//    private void showDate(TextView textView) {
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year,
//                                  int monthOfYear, int dayOfMonth) {
//                textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                days = dayOfMonth;
//            }
//        }, mYear, mMonth, mDay);
//        datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        datePickerDialog.getWindow().setGravity(Gravity.CENTER);
//        datePickerDialog.show();
//    }

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

    public static class HistoryHolder extends RecyclerView.ViewHolder {
        HistoryItemBinding binding;

        public HistoryHolder(HistoryItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
