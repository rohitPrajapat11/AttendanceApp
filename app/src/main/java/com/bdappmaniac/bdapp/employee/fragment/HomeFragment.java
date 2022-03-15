package com.bdappmaniac.bdapp.employee.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.CURRENT_TIME;
import static com.bdappmaniac.bdapp.activity.BaseActivity.USER_WORK;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.CalendarAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentHomeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.interfaces.CheckTimeCallBack;
import com.bdappmaniac.bdapp.model.CalendarDateModel;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.SharedPref;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import okhttp3.RequestBody;

public class HomeFragment extends BaseFragment implements CheckTimeCallBack {
    public FragmentHomeBinding binding;
    CalendarAdapter adapter;
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    ArrayList<Date> dates = new ArrayList<Date>();
    String selectedDateByCalendar = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    ArrayList<CalendarDateModel> calendarList2 = new ArrayList<>();
    int todayDate = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        if (SharedPref.read(USER_WORK, true)) {
            binding.timeStatusLayout.setVisibility(View.VISIBLE);
            binding.checkInTime.setText(SharedPref.getStringValue(CURRENT_TIME));
        }
        Constant.checkTimeCallBack = this;
//        binding.checkInTime.setOnClickListener(view -> {
//            showTime(binding.checkInTime);
//        });
//        binding.calendarDateTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.calendarView.setVisibility(View.VISIBLE);
//            }
//        });
//        binding.okCalendarBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.calendarDateTxt.setText(selectedDateByCalendar);
//                binding.calendarView.setVisibility(View.GONE);
//            }
//        });
//        binding.cancelCalendarBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.calendarView.setVisibility(View.GONE);
//            }
//        });
//        binding.checkOutTime.setOnClickListener(view -> {
//                    showTime(binding.checkOutTime);
//                }
//        );
//        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                binding.calendarView.setVisibility(View.GONE);
//                binding.saveBtn.setVisibility(View.GONE);
//            }
//        });
//        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setVisibility(View.GONE);
//                binding.calendarView.setVisibility(View.GONE);
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
//                } else {
//                    binding.checkInLayout.setVisibility(View.VISIBLE);
//                    binding.checkOutLayout.setVisibility(View.VISIBLE);
//                    binding.absentReasonTxt.setVisibility(View.GONE);
//                }
//            }
//        });
//        adapter = new CalendarAdapter(mContext, todayDate, this);
//        binding.calendarRecycler.setAdapter(adapter);
//        binding.cancelBtn.addTextChangedListener(new TextChange(binding.cancelBtn));
//        binding.saveBtn.addTextChangedListener(new TextChange(binding.saveBtn));
//        setUpCalendar();
        return binding.getRoot();
    }

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
//                if (myCalendar.getTimeInMillis() >= myCalendar.getTimeInMillis()) {
//                    //it's after current
//                    int hour = hourOfDay % 12;
//                    textView.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour, minute, hourOfDay < 12 ? "am" : "pm"));
//                } else {
//                    //it's before current'
//                    Toast.makeText(getContext(), "Invalid Time", Toast.LENGTH_LONG).show();
//                }
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
//                UIHelper.showLongToastInCenter(context, strHrsToShow + ":" + myCalendar.get(Calendar.MINUTE) + " " + am_pm);
//                textView.setText(strHrsToShow + ":" + sMin + " " + am_pm);
//                  editButton();
//            }
//        };
//        new TimePickerDialog(textView.getContext(), R.style.DatePicker, mTimeSetListener, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), false).show();
//    }
//
//        private void editButton() {
//        binding.saveBtn.setVisibility(View.VISIBLE);
//        binding.cancelBtn.setVisibility(View.VISIBLE);
//    }
//
//    private void setUpCalendar() {
//        ArrayList<CalendarDateModel> calendarList = new ArrayList<>();
//        Calendar monthCalendar = (Calendar) cal.clone();
//        int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        dates.clear();
//        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
//        while (dates.size() < maxDaysInMonth) {
//            if (monthCalendar.getTime().getDate() == todayDate) {
//                dates.add(monthCalendar.getTime());
//                calendarList.add(new CalendarDateModel(monthCalendar.getTime()));
//                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
//                break;
//            }
//            dates.add(monthCalendar.getTime());
//            calendarList.add(new CalendarDateModel(monthCalendar.getTime()));
//            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        calendarList2.clear();
//        calendarList2.addAll(calendarList);
//        adapter.setData(calendarList);
//        binding.calendarRecycler.scrollToPosition(adapter.getItemCount() - 1);
//    }
    public void getSelectedCalItem(String date) {
        selectedDateByCalendar = date;
    }

    @Override
    public void CheckInTimeCallBack() {
        checkInTimeApi();
    }

    @Override
    public void checkOutCallBack() {
        checkOutTimeApi();
    }

    private void checkInTimeApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.checkInTime(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    binding.timeStatusLayout.setVisibility(View.VISIBLE);
                    binding.checkInTime.setText(SharedPref.getStringValue(CURRENT_TIME));
                } else {
                    ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
                }
            }
        });
        AppLoader.hideLoaderDialog();
    }

    private void checkOutTimeApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.checkOutTime(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    if (SharedPref.read(USER_WORK, false)) {
                        binding.timeStatusLayout.setVisibility(View.GONE);
                    }
                    showSnackBar(binding.getRoot(), mContext.getString(R.string.working_time_has_stopped));
                    ((BaseActivity) mContext).stopService();
                } else {
                    ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
                }
            }
        });
        AppLoader.hideLoaderDialog();
    }


    public class TextChange implements TextWatcher {
        View view;

        private TextChange(View v) {
            view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}