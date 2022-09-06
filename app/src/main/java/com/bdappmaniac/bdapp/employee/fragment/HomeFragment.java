package com.bdappmaniac.bdapp.employee.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.USER_WORK;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

import com.airbnb.lottie.L;
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
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import okhttp3.RequestBody;

public class HomeFragment extends BaseFragment {
    public FragmentHomeBinding binding;
    CountDownTimer countDownTimer;
    Long CurrentTimerMills;

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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        binding.progressTime.setMax(32400000);

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
//
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
                    WorkingHrsTimer(32400000);
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
        MainService.checkInTime(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.checkOutTime.setText("");
                    SharedPref.write(SharedPref.CHECK_IN_TIME, apiResponse.getData().getAsJsonObject().get("time").getAsString());
                    binding.checkInTime.setText(apiResponse.getData().getAsJsonObject().get("time").getAsString());
                    if (apiResponse.getMessage().equals("Check in")) {
                        SharedPref.write(SharedPref.STATUS, true);

                    } else {
                        SharedPref.write(SharedPref.STATUS, false);
                    }
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void CheckOutApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.checkOutTime(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    SharedPref.write(USER_WORK, false);
                    SharedPref.write(SharedPref.CHECK_OUT_TIME, apiResponse.getData().getAsJsonObject().get("time").getAsString());
                    binding.checkOutTime.setText(apiResponse.getData().getAsJsonObject().get("time").getAsString());
                    String date = DateUtils.getDate(System.currentTimeMillis(),DateUtils.appDateFormat);
                    workingHrsApi(date);
//                    binding.timeStatusLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void WorkingHrsTimer(long totalmills) {
        countDownTimer = new CountDownTimer(totalmills, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long millisUntilFinished) {
                CurrentTimerMills = new Long(millisUntilFinished);
                int miliseconds = (int) (millisUntilFinished);
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) (millisUntilFinished / (1000 * 60)) % 60;
                int hrs = (int) (millisUntilFinished / (1000 * 60 * 60)) % 24;
                binding.remainTimeTv.setText(String.valueOf(String.format("%02d", hrs) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
//                = new ProgressBarAnimation(binding.progressTime.setProgress(), 3500);
                binding.progressTime.setProgress(32400000 - miliseconds);
            }
            @Override
            public void onFinish() {
//                countDownTimer.cancel();
                binding.remainTimeTv.setText("00:00:00");
            }
        }.start();
    }

    @Override
    public void onStop() {
        TimeManagerGetter();
        super.onStop();
    }

    @Override
    public void onResume() {
        TimeManagerSetter();
        super.onResume();
    }

    public void TimeManagerGetter() {
        SharedPref.write(SharedPref.ON_STOP_TIME, String.valueOf(System.currentTimeMillis()));
        SharedPref.write(SharedPref.ON_STOP_TIMER, CurrentTimerMills.toString());
    }

    public void TimeManagerSetter() {
        if (SharedPref.read(SharedPref.STATUS, false)) {
            binding.checkInTime.setText(SharedPref.read(SharedPref.CHECK_IN_TIME, ""));
            binding.checkOutTime.setText(SharedPref.read(SharedPref.CHECK_OUT_TIME, ""));

            String time = (SharedPref.read(SharedPref.ON_STOP_TIME, ""));
            long stoptime = Long.parseLong(time);
            long StartStopTime = (System.currentTimeMillis());

            String timer = (SharedPref.read(SharedPref.ON_STOP_TIMER, ""));
            long e = Long.parseLong(timer);
            long ee = (StartStopTime - stoptime);
            long v = (e - ee);
            WorkingHrsTimer(v);
        }
    }
    private void workingHrsApi(String date) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("date", toRequestBody(date));
        MainService.workedHoursOnGivenDay(mContext, getToken(), map).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if (apiResponse.getData() != null) {
                    JsonObject jsonObject = new Gson().fromJson(apiResponse.getData(), JsonObject.class);
                    binding.workingHrs.setText(jsonObject.get("worked_hours").getAsString());
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }




}

