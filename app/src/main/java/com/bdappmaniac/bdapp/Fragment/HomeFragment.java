package com.bdappmaniac.bdapp.Fragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bdappmaniac.bdapp.CalendarAdapter;
import com.bdappmaniac.bdapp.CalendarDateModel;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.Utils.ValidationUtils;
import com.bdappmaniac.bdapp.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends BaseFragment {
   public FragmentHomeBinding binding;
    CalendarAdapter adapter;
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    ArrayList<Date> dates = new ArrayList<Date>();
    ArrayList<CalendarDateModel> calendarList2 = new ArrayList<>();
    int todayDate = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.checkInTime.setOnClickListener(view -> {
                    showTime(binding.checkInTime);
                }
        );
        binding.calendarDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.calendarRecycler.setVisibility(View.VISIBLE);
            }
        });
        binding.checkOutTime.setOnClickListener(view -> {
                    showTime(binding.checkOutTime);
                }
        );
        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                binding.saveBtn.setVisibility(View.GONE);
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                binding.cancelBtn.setVisibility(View.GONE);
            }
        });
        adapter = new CalendarAdapter(mContext, todayDate);
        binding.calendarRecycler.setAdapter(adapter);
        binding.cancelBtn.addTextChangedListener(new HomeFragment.TextChange(binding.cancelBtn));
        binding.saveBtn.addTextChangedListener(new HomeFragment.TextChange(binding.saveBtn));
        setUpCalendar();
        return binding.getRoot();
    }

    public void showTime(TextView textView) {

        final Calendar myCalendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String am_pm = "";
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                if (myCalendar.get(Calendar.AM_PM) == Calendar.AM)
                    am_pm = "AM";
                else if (myCalendar.get(Calendar.AM_PM) == Calendar.PM)
                    am_pm = "PM";
                String strHrsToShow = (myCalendar.get(Calendar.HOUR) == 0) ? "12" : myCalendar.get(Calendar.HOUR) + "";
                int minutes = myCalendar.get(Calendar.MINUTE);
                String sMin = "";
                if (Integer.parseInt(strHrsToShow) < 10) {
                    strHrsToShow = "0" + strHrsToShow;
                }
                if (minutes < 10) {
                    sMin = "0" + minutes;
                } else {
                    sMin = String.valueOf(minutes);
                }
                //UIHelper.showLongToastInCenter(context, strHrsToShow + ":" + myCalendar.get(Calendar.MINUTE) + " " + am_pm);
                textView.setText(strHrsToShow + ":" + sMin + " " + am_pm);
              editButton();
            }
        };
        new TimePickerDialog(textView.getContext(), R.style.DatePicker, mTimeSetListener, myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), false).show();
    }

    private void editButton() {
        binding.saveBtn.setVisibility(View.VISIBLE);
        binding.cancelBtn.setVisibility(View.VISIBLE);
    }

    private void setUpCalendar() {
        ArrayList<CalendarDateModel> calendarList = new ArrayList<>();
        Calendar monthCalendar = (Calendar) cal.clone();
        int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        dates.clear();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        while (dates.size() < maxDaysInMonth) {
            if (monthCalendar.getTime().getDate() == todayDate) {
                dates.add(monthCalendar.getTime());
                calendarList.add(new CalendarDateModel(monthCalendar.getTime()));
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                break;
            }
            dates.add(monthCalendar.getTime());
            calendarList.add(new CalendarDateModel(monthCalendar.getTime()));
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendarList2.clear();
        calendarList2.addAll(calendarList);
        adapter.setData(calendarList);
        binding.calendarRecycler.scrollToPosition(adapter.getItemCount() - 1);
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
//            binding.cancelBtn.setVisibility(View.VISIBLE);
//            binding.saveBtn.setVisibility(View.VISIBLE);
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
   public void getSelectedCalItem()
    {
        Toast.makeText(getActivity(), "TO", Toast.LENGTH_SHORT).show();
    }
}