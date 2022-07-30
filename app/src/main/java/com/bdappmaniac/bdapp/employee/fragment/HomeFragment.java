package com.bdappmaniac.bdapp.employee.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.USER_WORK;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.CalendarAdapter;
import com.bdappmaniac.bdapp.adapter.EmpHolidayAdapter;
import com.bdappmaniac.bdapp.databinding.DialogCheckinBinding;
import com.bdappmaniac.bdapp.databinding.DialogCheckoutBinding;
import com.bdappmaniac.bdapp.databinding.FragmentHomeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.ProgressBarAnimation;
import com.bdappmaniac.bdapp.interfaces.TimeLayoutCallBack;
import com.bdappmaniac.bdapp.model.CalendarDateModel;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends BaseFragment implements TimeLayoutCallBack {
    public FragmentHomeBinding binding;
    ProgressBarAnimation mProgressAnimation;
    CountDownTimer countDownTimer;
    ArrayList<ModelHolidayItems> itemsArrayList = new ArrayList<>();
    EmpHolidayAdapter holidayadapter;
    CalendarAdapter adapter;
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    ArrayList<Date> dates = new ArrayList<Date>();
    String selectedDateByCalendar = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    ArrayList<CalendarDateModel> calendarList2 = new ArrayList<>();
    int todayDate = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        HomeActivity.getTime = this;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);

        binding.progressTime.setMax(32400000);


//        mProgressAnimation.setProgress();

        Constant.timeLayoutCallBack = this;
        SharedPref.init(mContext);
        if (SharedPref.read(USER_WORK, false)) {
//            binding.timeStatusLayout.setVisibility(View.VISIBLE);
//            binding.checkInTime.setText(SharedPref.getStringValue(CURRENT_TIME));
        }

        binding.userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.profileFragment);
            }
        });
        binding.imgcheckinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.checkintxt.getText().equals("CHECK IN")) {
                    CheckInDialog();
                } else if (binding.checkintxt.getText().equals("CHECK OUT")) {
                    CheckOutDialog();

                }
            }
        });
        binding.checkInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.EmpmyAttendence);
            }
        });
        binding.loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.empLoanfragment);
            }
        });
        binding.holidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.employeeHolidayFragment);
            }
        });
        binding.expence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.EmpExpencesfragment);
            }
        });
        binding.leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.EmpLeavesFragment);
            }
        });
        binding.username.setText(SharedPref.getUserDetails().getEmployeeName());
        binding.userEmail.setText(SharedPref.getUserDetails().getEmail());
        binding.userDesignation.setText(SharedPref.getUserDetails().getDesignation());
//        binding.userProfile.setImageResource(SharedPref.getUserDetails().getProfile());
//        }else if(SharedPref.read(USER_WORK, false)) {
//            binding.timeStatusLayout.setVisibility(View.GONE);
//        }
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
    public void CheckInDialog() {
      DialogCheckinBinding checkInDialog = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_checkin, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(checkInDialog.getRoot());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        checkInDialog.checkinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkintxt.getText().equals("CHECK IN")) {
                    binding.imgcheckinbtn.setBackgroundResource(R.drawable.bg_btn_gradient_in);
                    binding.checkintxt.setText("CHECK OUT");
                    CheckInApi();
                    WorkingHrsTimer();
                    dialog.dismiss();
                } else if (binding.checkintxt.getText().equals("CHECK OUT")) {
                    binding.imgcheckinbtn.setBackgroundResource(R.drawable.bg_btn_gragient_out);
                    binding.checkintxt.setText("CHECK IN");
                    dialog.dismiss();
                }
            }
        });
        checkInDialog.dismise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void CheckOutDialog() {
        DialogCheckoutBinding checkOutDialog = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_checkout, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(checkOutDialog.getRoot());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        checkOutDialog.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkintxt.getText().equals("CHECK IN")) {
                    binding.imgcheckinbtn.setBackgroundResource(R.drawable.bg_btn_gradient_in);
                    binding.checkintxt.setText("CHECK OUT");
                    dialog.dismiss();
                } else if (binding.checkintxt.getText().equals("CHECK OUT")) {
                    binding.imgcheckinbtn.setBackgroundResource(R.drawable.bg_btn_gragient_out);
                    binding.checkintxt.setText("CHECK IN");
                    CheckOutApi();
                    countDownTimer.cancel();
                    dialog.dismiss();
                }
            }
        });
        checkOutDialog.dismise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void CheckInApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.checkInTime(mContext, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Toast.makeText(mContext, apiResponse.getMessage() ,Toast.LENGTH_SHORT).show();
                    binding.checkOutTime.setText("");
                    binding.checkInTime.setText(apiResponse.getData().getAsJsonObject().get("time").getAsString());
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage() ,Toast.LENGTH_SHORT).show();
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void CheckOutApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.checkOutTime(mContext, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Toast.makeText(mContext, apiResponse.getMessage() ,Toast.LENGTH_SHORT).show();
                    SharedPref.write(USER_WORK, false);

                    binding.checkOutTime.setText(apiResponse.getData().getAsJsonObject().get("time").getAsString());
//                    binding.timeStatusLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void WorkingHrsTimer() {
        countDownTimer = new CountDownTimer(32400000, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long millisUntilFinished) {
                int miliseconds = (int) (millisUntilFinished);
                int seconds = (int) (millisUntilFinished/1000)% 60;
                int minutes = (int) (millisUntilFinished / (1000 * 60)) % 60;
                int hrs = (int) (millisUntilFinished / (1000 * 60 * 60)) % 24;
                binding.remainTimeTv.setText(String.valueOf(String.format("%02d", hrs)+":"+String.format("%02d", minutes)+":"+String.format("%02d", seconds)));
//                = new ProgressBarAnimation(binding.progressTime.setProgress(), 3500);
                binding.progressTime.setProgress(32400000-miliseconds);
            }
            @Override
            public void onFinish() {
            }
        }.start();
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
    public void TimeStatusLayoutCallBack() {
//        binding.timeStatusLayout.setVisibility(View.VISIBLE);
    }
    @Override
    public void CheckInTimeCallBack() {
//        binding.checkInTime.setText(SharedPref.getStringValue(CURRENT_TIME));
    }



}