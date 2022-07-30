package com.bdappmaniac.bdapp.activity;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.application.BdApp;
import com.bdappmaniac.bdapp.databinding.ActivityHomeBinding;
import com.bdappmaniac.bdapp.databinding.CustomInactiveDialogBinding;
import com.bdappmaniac.bdapp.databinding.ExitDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.PresentAndAbsentDialogboxBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.ConnectivityReceiver;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.interfaces.OnChangeConnectivityListener;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;

public class HomeActivity extends BaseActivity implements View.OnClickListener, CalendarCallBack{
    ActivityHomeBinding binding;
    NavController navController;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        StatusBarUtils.statusBarColor(this, R.color.white);
        ConnectivityReceiver.setConnectivityListener(new OnChangeConnectivityListener() {
            @Override
            public void onChanged(boolean status) {
                if (!BdApp.getInstance().isInternet(HomeActivity.this)) {
                    noInternetDialog();
                } else {
                    if (noInternetdialog != null) {
                        noInternetdialog.dismiss();
                        AppLoader.hideLoaderDialog();
                    }
                }
            }
        });
        navController = Navigation.findNavController(this, R.id.nav_controller);
        SharedPref.init(this);
        updateProfile();
        employeeStatusByIdApi(SharedPref.getUserDetails().getId());
//        Toast.makeText(this, SharedPref.getUserDetails().getAccessToken(), Toast.LENGTH_SHORT).show();
        String dates = DateUtils.getCurrentDate();
        absentCheck(dates);
        boolean workIsEnable = SharedPref.read(USER_WORK, false);
//        binding.homeLayout.headerLayout.extIcon.setChecked(workIsEnable);
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
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.empHistoryFragment) {
                    navController.navigate(R.id.empHistoryFragment);
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
        binding.navigationDrawer.userName.setText(SharedPref.getUserDetails().getEmployeeName());
        binding.navigationDrawer.userJobTxt.setText(SharedPref.getUserDetails().getDesignation());
        binding.navigationDrawer.homeBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.settingBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.logOutBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.tmcBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.rulesBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.profileBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.taskBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.MyattendanceBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.leaveBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.expenceBtn.setOnClickListener(this::onClick);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.homeFragment) {
                    navHandel("Home");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.taskFragment) {
                    navHandel("Task");
                    headerHideShow(false);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.loanFragment) {
                    navHandel("Loan");
                    headerHideShow(false);
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
                } else if (destination.getId() == R.id.employeeAttendanceRulesFragment) {
                    navHandel("Attendance Rules");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.empLoanfragment) {
                    navHandel("Loan");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.EmpmyAttendence) {
                    navHandel("My Attendance");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.empHistoryFragment) {
                    navHandel("My History");
                    headerHideShow(true);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.EmpLeavesFragment) {
                    navHandel("Leaves");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.EmpExpencesfragment) {
                    navHandel("Expenses");
                    headerHideShow(false);
                    bottomHideShow(false);
                }else if (destination.getId() == R.id.profileFragment) {
                    navHandel("Profile");
                    headerHideShow(false);
                    bottomHideShow(false);
                }else if (destination.getId() == R.id.EmpGetLoanFragment) {
                    navHandel("Get Loan");
                    headerHideShow(false);
                    bottomHideShow(false);
                }else if (destination.getId() == R.id.EmpManageLoansFragment) {
                    navHandel("Manage Advance");
                    headerHideShow(false);
                    bottomHideShow(false);
                }
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    void navHandel(String type) {
        switch (type) {
            case "Home":
                binding.homeLayout.headerLayout.title.setText("Home");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.settingBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.title.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home_select);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
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
                binding.homeLayout.headerLayout.title.setText("Profile");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
                break;
            case "My Attendance":
                binding.homeLayout.headerLayout.title.setText("My Attendance");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
                break;
                case "Expenses":
                binding.homeLayout.headerLayout.title.setText("Expenses");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
                break;
                case "Leaves":
                binding.homeLayout.headerLayout.title.setText("Leaves");
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
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.empLoanfragment) {
                    navController.navigate(R.id.empLoanfragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
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
            case R.id.rulesBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.employeeAttendanceRulesFragment) {
                    navController.navigate(R.id.employeeAttendanceRulesFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.profileBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.profileFragment) {
                    navController.navigate(R.id.profileFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.taskBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.taskFragment) {
                    navController.navigate(R.id.taskFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.MyattendanceBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.EmpmyAttendence) {
                    navController.navigate(R.id.EmpmyAttendence);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.expenceBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.EmpExpencesfragment) {
                    navController.navigate(R.id.EmpExpencesfragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.historyBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.empHistoryFragment) {
                    navController.navigate(R.id.empHistoryFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.leaveBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.EmpLeavesFragment) {
                    navController.navigate(R.id.EmpLeavesFragment);
                }
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
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
//        if (check) {
//            binding.homeLayout.bottomLayout.dashboardBottom.setVisibility(View.VISIBLE);
//        } else {
//            binding.homeLayout.bottomLayout.dashboardBottom.setVisibility(View.GONE);
//        }
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
        Bitmap bitmap = TextToBitmap.textToBitmap(SharedPref.getUserDetails().getEmployeeName(), this, 10, R.color.black);
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
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
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
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
//                    binding.homeLayout.headerLayout.extIcon.setChecked(false);
                    binding.homeLayout.headerLayout.absentBtn.setVisibility(View.VISIBLE);
//                    binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), apiResponse.getMessage());
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
                        if (jsonObject.get("absent").getAsBoolean()) {
//                            binding.homeLayout.headerLayout.extIcon.setChecked(false);
                            Toast.makeText(this, "Absent", Toast.LENGTH_SHORT).show();
                        } else {
                            presentAndAbsentDialog();
                        }
                    } else {
//                        CheckInApiCallBack();
                    }
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void absentCheck(String date) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("date", toRequestBody(date));
        MainService.workedHoursOnGivenDay(this, getToken(), map).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    JsonObject jsonObject = new Gson().fromJson(apiResponse.getData(), JsonObject.class);
                    if (jsonObject.get("worked_hours").getAsString().equals("00:00:00")) {
                        if (jsonObject.get("absent").getAsBoolean()) {
                            binding.homeLayout.headerLayout.absentBtn.setVisibility(View.VISIBLE);
//                            binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                        }
                    }
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void workingStart() {
        SharedPref.write(USER_WORK, true);
        String getCurrentTime = DateUtils.getCurrentTime();
        SharedPref.putString(CURRENT_TIME, getCurrentTime);
//        binding.homeLayout.headerLayout.extIcon.setChecked(true);
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
//                checkOutTimeApi();
            }
        });
        exitDialogboxBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.homeLayout.headerLayout.extIcon.setChecked(true);
                dialog.dismiss();
            }
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
//                    CheckInApiCallBack();
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
//                binding.homeLayout.headerLayout.extIcon.setChecked(false);
                dialog.dismiss();
            }
        });
    }

    public void updateProfile() {
        if (SharedPref.getUserDetails().getProfile() == null) {
            textProfile();
        } else {
            Glide.with(this).load(SharedPref.getUserDetails().getProfile()).placeholder(R.drawable.user).error(R.drawable.user).into(binding.navigationDrawer.userProfile);
        }
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
//    public void removeDesignationApi(int id) {
//        AppLoader.showLoaderDialog(context);
//        MainService.removeDesignation(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
//            if (apiResponse == null) {
//                ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
//            } else {
//                if ((apiResponse.getData() != null)) {
//                    ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
//                    list.remove(getAdapterPosition());
//                    notifyDataSetChanged();
//                } else {
//                    ((BaseActivity) context).showToast(apiResponse.getMessage());
//                }
//            }
//            AppLoader.hideLoaderDialog();
//        });
//    }

    public void employeeStatusByIdApi(int id) {
        AppLoader.showLoaderDialog(this);
        MainService.employeeStatusById(this, getToken(), id).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) this).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    if (SharedPref.getUserDetails().getStatus().equals("inactive")) {
                        ActiveInactiveDialog();
                    } else if (SharedPref.getUserDetails().getStatus().equals("active")) {
                        showToast("Active");
                    }
                } else {
                    ((BaseActivity) this).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    void ActiveInactiveDialog() {
        CustomInactiveDialogBinding customInactiveDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.custom_inactive_dialog, null, false);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(customInactiveDialogBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        customInactiveDialogBinding.exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}