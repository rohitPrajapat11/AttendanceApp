package com.bdappmaniac.bdapp.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ActivityHomeBinding;
import com.bdappmaniac.bdapp.databinding.ExitDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.PresentAndAbsentDialogboxBinding;
import com.bdappmaniac.bdapp.employee.fragment.HomeFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;

public class HomeActivity extends BaseActivity implements View.OnClickListener, CalendarCallBack {
    ActivityHomeBinding binding;
    NavController navController;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        StatusBarUtils.statusBarColor(this, R.color.white);
        navController = Navigation.findNavController(this, R.id.nav_controller);
        SharedPref.init(this);
//        Toast.makeText(this, SharedPref.getUserDetails().getAccessToken(), Toast.LENGTH_SHORT).show();
        textProfile();
        boolean workIsEnable = SharedPref.read(USER_WORK, false);
        binding.homeLayout.headerLayout.extIcon.setChecked(workIsEnable);
        binding.homeLayout.bottomLayout.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment);
                }
            }
        });
        binding.homeLayout.bottomLayout.taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.taskFragment) {
                    navController.navigate(R.id.taskFragment);
                }
            }
        });
        binding.homeLayout.bottomLayout.historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.historyFragment) {
                    navController.navigate(R.id.historyFragment);
                }
            }
        });
        binding.homeLayout.bottomLayout.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.profileFragment) {
                    navController.navigate(R.id.profileFragment);
                }
            }
        });
        binding.homeLayout.headerLayout.menuBtn.setOnClickListener(v -> {
            drawerOpenCLose();
        });
        binding.homeLayout.headerLayout.addBtn.setOnClickListener(v -> {
            Constant.calendarCallBack.openCalendar();
        });
        binding.homeLayout.headerLayout.extIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.homeLayout.headerLayout.extIcon.isChecked()) {
                    String dates = DateUtils.getCurrentDate();
                    workedHoursOnGivenDayApi(dates);
                } else {
                    exitDialog();
                }
            }
        });
        binding.navigationDrawer.homeBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.settingBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.logOutBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.tmcBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.loanBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.holidayBtn.setOnClickListener(this::onClick);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.homeFragment) {
                    navHandel("Home");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.taskFragment) {
                    navHandel("Task");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.historyFragment) {
                    navHandel("History");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.profileFragment) {
                    navHandel("Profile");
                    headerHideShow(false);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.loanFragment) {
                    navHandel("Loan");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.homeTermsAndConditionsFragment) {
                    navHandel("TermAndConditions");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.homeSettingFragment) {
                    navHandel("Settings");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeHolidayFragment) {
                    navHandel("Settings");
                    headerHideShow(false);
                    bottomHideShow(false);
                }
            }
        });
    }

    void navHandel(String type) {
        switch (type) {
            case "Home":
                binding.homeLayout.headerLayout.title.setText("Home");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home_select);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.VISIBLE);
                break;
            case "Task":
                binding.homeLayout.headerLayout.title.setText("Task");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task_select);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "History":
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.title.setText("Working History");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history_select);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "Profile":
                binding.homeLayout.headerLayout.title.setText("");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
                break;
        }
    }

    void drawerOpenCLose() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            binding.mainDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onClick(View v) {
        drawerOpenCLose();
        switch (v.getId()) {
            case R.id.homeBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.loanBtn:
                showToast(getString(R.string.in_progress));
                break;
            case R.id.tmcBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.homeTermsAndConditionsFragment) {
                    navController.navigate(R.id.homeTermsAndConditionsFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.settingBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.homeSettingFragment) {
                    navController.navigate(R.id.homeSettingFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logOutBtn:
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                logoutDialog();
                break;
            case R.id.holidayBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.employeeHolidayFragment) {
                    navController.navigate(R.id.employeeHolidayFragment);
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                }
        }
    }

    void headerHideShow(boolean check) {
        if (check) {
            binding.homeLayout.headerLayout.headerLayout.setVisibility(View.VISIBLE);
        } else {
            binding.homeLayout.headerLayout.headerLayout.setVisibility(View.GONE);
        }
    }

    void bottomHideShow(boolean check) {
        if (check) {
            binding.homeLayout.bottomLayout.dashboardBottom.setVisibility(View.VISIBLE);
        } else {
            binding.homeLayout.bottomLayout.dashboardBottom.setVisibility(View.GONE);
        }
    }

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
//        binding.homeLayout.calendarRecycler.scrollToPosition(adapter.getItemCount() - 1);
//    }

    void textProfile() {
        Bitmap bitmap = TextToBitmap.textToBitmap(binding.navigationDrawer.userName.getText().toString(), this, 10, R.color.black);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        binding.navigationDrawer.userProfile.setImageDrawable(d);
    }

    @Override
    public void openCalendar() {
    }

    @Override
    public void openEmployeeList() {
        CalendarCallBack.super.openEmployeeList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.auth_nav);
        if (navHostFragment != null) {
            List<Fragment> fragmentList = navHostFragment.getChildFragmentManager().getFragments();
            boolean isLoginFragment = false;
            for (Fragment fragment1 : fragmentList) {
                if (fragment1 instanceof HomeFragment) {
                    isLoginFragment = true;
                    break;
                }
            }
            if (isLoginFragment) {
                finish();
            }
        }
    }

    private void markAttendanceByEmployeeApi(String reason) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("absent_reason", toRequestBody(reason));
        MainService.markAttendanceByEmployee(this, getToken(), map).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    showSnackBar(binding.getRoot(), "Your Attendance Is Marked As Absent");
                    binding.homeLayout.headerLayout.extIcon.setChecked(false);
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }


    private void workedHoursOnGivenDayApi(String date) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("date", toRequestBody(date));
        MainService.workedHoursOnGivenDay(this, getToken(), map).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    JsonObject jsonObject = new Gson().fromJson(apiResponse.getData(), JsonObject.class);
                    if (jsonObject.get("worked_hours").getAsString().equals("00:00:00")) {
                        if (!jsonObject.get("absent").getAsBoolean()) {
                            presentAndAbsentDialog();
                        } else {
                            checkInTimeApi();
                        }
                    } else {
                        workingStart();
                    }
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void checkInTimeApi() {
        AppLoader.showLoaderDialog(this);
        MainService.checkInTime(this, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    workingStart();
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
    public void workingStart() {
        SharedPref.write(USER_WORK, true);
        String getCurrentTime = DateUtils.getCurrentTime();
        SharedPref.putString(CURRENT_TIME, getCurrentTime);
        binding.homeLayout.headerLayout.extIcon.setChecked(true);
        showSnackBar(binding.getRoot(), getString(R.string.working_time_has_started));
        Constant.timeLayoutCallBack.TimeStatusLayoutCallBack();
        Constant.timeLayoutCallBack.CheckInTimeCallBack();
        startService();
    }


    void exitDialog() {
        ExitDialogboxBinding exitDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.exit_dialogbox, null, false);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(exitDialogboxBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        exitDialogboxBinding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                checkOutTimeApi();
            }
        });
        exitDialogboxBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.homeLayout.headerLayout.extIcon.setChecked(true);
                dialog.dismiss();
            }
        });
    }

    private void checkOutTimeApi() {
        AppLoader.showLoaderDialog(this);
        MainService.checkOutTime(this, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    showSnackBar(binding.getRoot(), getString(R.string.working_time_has_stopped));
                    SharedPref.write(USER_WORK, false);
                    stopService();
//                    binding.timeStatusLayout.setVisibility(View.GONE);
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    void presentAndAbsentDialog() {
        button = null;
        PresentAndAbsentDialogboxBinding presentAndAbsentDialogboxBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.present_and_absent_dialogbox, null, false);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(presentAndAbsentDialogboxBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        presentAndAbsentDialogboxBinding.presentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button = presentAndAbsentDialogboxBinding.presentBtn;
                presentAndAbsentDialogboxBinding.presentBtn.setBackground(getDrawable(R.drawable.green_bg_att));
                presentAndAbsentDialogboxBinding.absentBtn.setBackground(getDrawable(R.drawable.gray_round_present));
                presentAndAbsentDialogboxBinding.absentReasonTxt.setVisibility(View.GONE);
                presentAndAbsentDialogboxBinding.saveBtn.setVisibility(View.VISIBLE);
            }
        });
        presentAndAbsentDialogboxBinding.absentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button = presentAndAbsentDialogboxBinding.absentBtn;
                presentAndAbsentDialogboxBinding.absentBtn.setBackground(getDrawable(R.drawable.red_round_bg));
                presentAndAbsentDialogboxBinding.presentBtn.setBackground(getDrawable(R.drawable.gray_round_present));
                presentAndAbsentDialogboxBinding.absentReasonTxt.setVisibility(View.VISIBLE);
                presentAndAbsentDialogboxBinding.saveBtn.setVisibility(View.VISIBLE);
            }
        });
        presentAndAbsentDialogboxBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button == presentAndAbsentDialogboxBinding.presentBtn) {
                    showSnackBar(binding.getRoot(), getString(R.string.working_time_has_started));
                    checkInTimeApi();
                    dialog.dismiss();
                } else if (button == presentAndAbsentDialogboxBinding.absentBtn) {
                    if (presentAndAbsentDialogboxBinding.absentReasonTxt.getText().toString().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Enter reason for absent");
                    } else {
                        markAttendanceByEmployeeApi(presentAndAbsentDialogboxBinding.absentReasonTxt.getText().toString());
                        dialog.dismiss();
                    }
                }
            }
        });
        presentAndAbsentDialogboxBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.homeLayout.headerLayout.extIcon.setChecked(false);
                dialog.dismiss();
            }
        });
    }

//    private void startClock(){
//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Calendar c = Calendar.getInstance();
//
//                                int hours = c.get(Calendar.HOUR_OF_DAY);
//                                int minutes = c.get(Calendar.MINUTE);
//                                int seconds = c.get(Calendar.SECOND);
//
//                                String curTime = String.format("%02d  %02d  %02d", hours, minutes, seconds);
////                                clock.setText(curTime); //change clock to your textview
//                                Toast.makeText(HomeActivity.this, "Time - " + curTime, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//
//        t.start();
//    }
}