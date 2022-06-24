package com.bdappmaniac.bdapp.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.application.BdApp;
import com.bdappmaniac.bdapp.databinding.ActivityAdminBinding;
import com.bdappmaniac.bdapp.fragment.AdminHomeFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.ConnectivityReceiver;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

public class AdminActivity extends BaseActivity implements View.OnClickListener, CalendarCallBack {
    ActivityAdminBinding binding;
    NavController navController;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        StatusBarUtils.statusBarColor(this, R.color.white);
        ConnectivityReceiver.setConnectivityListener(status -> {
            if (!BdApp.getInstance().isInternet(AdminActivity.this)) {
                noInternetDialog();
            } else {
                if (noInternetdialog != null) {
                    noInternetdialog.dismiss();
                    showToast("error");
                    AppLoader.hideLoaderDialog();
                }
            }
        });
        navController = Navigation.findNavController(this, R.id.nav_controller);
        SharedPref.init(this);
        updateProfile();
        binding.bottomLayout.bottomNavBar.setItemSelected(R.id.homeBtns, true);
        binding.bottomLayout.bottomNavBar.setOnItemSelectedListener(i -> {
            switch (i) {
                case R.id.homeBtns:
                    if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminHomeFragment) {
                        navController.navigate(R.id.adminHomeFragment);
                    }
                    break;
                case R.id.employeeBtns:
                    if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.employeeListFragment) {
                        navController.navigate(R.id.employeeListFragment);
                    }
                    break;
                case R.id.taskBtns:
                    if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.toDoListFragment) {
                        navController.navigate(R.id.toDoListFragment);
                    }
                    break;
                case R.id.profileBtns:
                    if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminProfile) {
                        navController.navigate(R.id.adminProfile);
                    }
                    break;
            }
        });
        binding.headerLayout.menuBtn.setVisibility(View.GONE);
        binding.headerLayout.menuBtn.setOnClickListener(v -> drawerOpenCLose());
        binding.headerLayout.addBtn.setOnClickListener(v -> Constant.calendarCallBack.openEmployeeList());
        binding.headerLayout.backBtn.setOnClickListener(v -> onBackPressed());
        binding.headerLayout.additionBtn.setOnClickListener(view -> navController.navigate(R.id.registerEmployeeFragment));
        binding.headerLayout.settingBtn.setOnClickListener(view -> navController.navigate(R.id.settingFragment));
        binding.navigationDrawer.userName.setText(SharedPref.getUserDetails().getEmployeeName());
        binding.navigationDrawer.userJobTxt.setText(SharedPref.getUserDetails().getDesignation());
        binding.navigationDrawer.homeBtn.setOnClickListener(this);
        binding.navigationDrawer.settingBtn.setOnClickListener(this);
        binding.navigationDrawer.tmcBtn.setOnClickListener(this);
        binding.navigationDrawer.logOutBtn.setOnClickListener(this);
        binding.navigationDrawer.registerBtn.setOnClickListener(this);
        binding.navigationDrawer.profileBtn.setOnClickListener(this);
        binding.navigationDrawer.loanBtn.setOnClickListener(this);
        binding.navigationDrawer.addHolidayBtn.setOnClickListener(this);
        binding.navigationDrawer.rulesBtn.setOnClickListener(this);
        binding.navigationDrawer.desinationBtn.setOnClickListener(this);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.adminHomeFragment:
                    navHandel("Home");
                    headerHideShow(true);
                    bottomHideShow(true);
                    binding.bottomLayout.bottomNavBar.setItemSelected(R.id.homeBtns, true);
                    break;
                case R.id.employeeListFragment:
                    navHandel("Employees");
                    headerHideShow(true);
                    bottomHideShow(true);
                    binding.bottomLayout.bottomNavBar.setItemSelected(R.id.employeeBtns, true);
                    break;
                case R.id.adminProfile:
                    navHandel("Profile");
                    headerHideShow(false);
                    bottomHideShow(true);
                    binding.bottomLayout.bottomNavBar.setItemSelected(R.id.profileBtns, true);
                    break;
                case R.id.adminTermFragment:
                    navHandel("Employment Terms");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeDetailFragment:
                    navHandel("Employee Detail");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeAttendanceFragment:
                    navHandel("Employee Attendance");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.registerEmployeeFragment:
                    navHandel("Register Employee");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.settingFragment:
                    navHandel("Setting");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeListForLoanFragment:
                    navHandel("Employee Loans");
                    headerHideShow(true);
                    bottomHideShow(false);
                    break;
                case R.id.provideLoanFragment:
                    navHandel("Loan Details");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.testHolidayFragment:
                    navHandel("Holidays");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.attendanceRulesFragment:
                    navHandel("Attendance Rules");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.lockUnlockFragment:
                    navHandel("Lock/Unlock");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.toDoListFragment:
                    navHandel("Task");
                    headerHideShow(true);
                    bottomHideShow(true);
                    binding.bottomLayout.bottomNavBar.setItemSelected(R.id.taskBtns, true);
                    break;
                case R.id.designationFragment:
                    navHandel("Designation");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeAttandenceListFragment:
                    navHandel("Employee Attandence");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeDesListFragment:
                    navHandel("Employee List");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeExpensesFragment:
                    navHandel("Employee Expenses");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.overTimeFragment:
                    navHandel("OverTime Task");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.loanFragment:
                    navHandel("Advance Payment");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeToDoListFragment:
                    navHandel("Employee To Do List");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.approveLeavesFragment:
                    navHandel("Leaves");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
                case R.id.employeeLeaveApprovalFragment:
                    navHandel("Taken Leaves");
                    headerHideShow(false);
                    bottomHideShow(false);
                    break;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    void navHandel(String type) {
        switch (type) {
            case "Home":
                binding.headerLayout.title.setText("Home");
                binding.headerLayout.headerLayout.setPadding(0, 0, 0, 0);
                binding.headerLayout.headerLayout.setVisibility(View.GONE);
                binding.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.headerLayout.title.setVisibility(View.GONE);
                binding.headerLayout.addBtn.setVisibility(View.GONE);
                binding.headerLayout.backBtn.setVisibility(View.GONE);
                binding.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.headerLayout.extIcon.setVisibility(View.GONE);
                binding.headerLayout.additionBtn.setVisibility(View.GONE);
                binding.headerLayout.settingBtn.setVisibility(View.GONE);
                break;
            case "Employees":
                binding.headerLayout.title.setText("Employees");
                binding.headerLayout.headerLayout.setVisibility(View.VISIBLE);
                binding.headerLayout.headerLayout.setPadding(0, 30, 0, 30);
                binding.headerLayout.headerLayout.setBackgroundColor(getResources().getColor(R.color.white));
                binding.headerLayout.addBtn.setVisibility(View.GONE);
                binding.headerLayout.title.setVisibility(View.VISIBLE);
                binding.headerLayout.backBtn.setVisibility(View.GONE);
                binding.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.headerLayout.extIcon.setVisibility(View.GONE);
                binding.headerLayout.additionBtn.setVisibility(View.VISIBLE);
                binding.headerLayout.settingBtn.setVisibility(View.GONE);
                break;
            case "Profile":
                binding.headerLayout.title.setText("Profile");
                binding.headerLayout.headerLayout.setVisibility(View.VISIBLE);
                binding.headerLayout.settingBtn.setVisibility(View.GONE);
                break;
            case "Task":
                binding.headerLayout.title.setText("Task");
                binding.headerLayout.headerLayout.setVisibility(View.VISIBLE);
                binding.headerLayout.headerLayout.setPadding(0, 30, 0, 30);
                binding.headerLayout.headerLayout.setBackgroundColor(getResources().getColor(R.color.white));
                binding.headerLayout.title.setVisibility(View.VISIBLE);
                binding.headerLayout.additionBtn.setVisibility(View.GONE);
                binding.headerLayout.settingBtn.setVisibility(View.GONE);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        drawerOpenCLose();
        switch (v.getId()) {
            case R.id.homeBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminHomeFragment) {
                    navController.navigate(R.id.adminHomeFragment);
                }
                break;
            case R.id.settingBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.settingFragment) {
                    navController.navigate(R.id.settingFragment);
                }
                break;
            case R.id.tmcBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminTermFragment) {
                    navController.navigate(R.id.adminTermFragment);
                }
                break;
            case R.id.registerBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.registerEmployeeFragment) {
                    navController.navigate(R.id.registerEmployeeFragment);
                }
                break;
            case R.id.profileBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminProfile) {
                    navController.navigate(R.id.adminProfile);
                }
                break;
            case R.id.loanBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.loanFragment) {
                    navController.navigate(R.id.loanFragment);
                }
                break;
            case R.id.logOutBtn:
                logoutDialog();
                break;
            case R.id.addHolidayBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.testHolidayFragment) {
                    navController.navigate(R.id.testHolidayFragment);
                }
                break;
            case R.id.rulesBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.attendanceRulesFragment) {
                    navController.navigate(R.id.attendanceRulesFragment);
                }
                break;
            case R.id.desinationBtn:
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.designationFragment) {
                    navController.navigate(R.id.designationFragment);
                }
                break;
        }
    }

    void headerHideShow(boolean check) {
        if (check) {
            binding.headerLayout.headerLayout.setVisibility(View.VISIBLE);
        } else {
            binding.headerLayout.headerLayout.setVisibility(View.GONE);
        }

    }

    void bottomHideShow(boolean check) {
        if (check) {
            binding.bottomLayout.dashboardBottom.setVisibility(View.VISIBLE);
        } else {
            binding.bottomLayout.dashboardBottom.setVisibility(View.GONE);
        }
    }

    void textProfile() {
        Bitmap bitmap = TextToBitmap.textToBitmap(binding.navigationDrawer.userName.getText().toString(), this, 10, R.color.black);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        binding.navigationDrawer.userProfile.setImageDrawable(d);
    }

    @Override
    public void openCalendar() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.adminNav);
        if (navHostFragment != null) {
            List<Fragment> fragmentList = navHostFragment.getChildFragmentManager().getFragments();
            boolean isAdminHomeFragment = false;
            for (Fragment fragment1 : fragmentList) {
                if (fragment1 instanceof AdminHomeFragment) {
                    isAdminHomeFragment = true;
                    break;
                }
            }
            if (isAdminHomeFragment) {
                finish();
            }
        }
    }

    public void updateProfile() {
        if (SharedPref.getUserDetails().getProfile().isEmpty()) {
            textProfile();
        } else {
            Glide.with(this).load(SharedPref.getUserDetails().getProfile()).placeholder(R.drawable.user).into(binding.navigationDrawer.userProfile);
        }
    }
}