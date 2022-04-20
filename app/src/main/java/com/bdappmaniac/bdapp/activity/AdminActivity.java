package com.bdappmaniac.bdapp.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.admin.fragment.AdminHomeFragment;
import com.bdappmaniac.bdapp.application.BdApp;
import com.bdappmaniac.bdapp.databinding.ActivityAdminBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.ConnectivityReceiver;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.interfaces.CalendarCallBack;
import com.bdappmaniac.bdapp.interfaces.OnChangeConnectivityListener;
import com.bdappmaniac.bdapp.utils.Constant;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

public class AdminActivity extends BaseActivity implements View.OnClickListener, CalendarCallBack {
    ActivityAdminBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        StatusBarUtils.statusBarColor(this, R.color.white);
        ConnectivityReceiver.setConnectivityListener(new OnChangeConnectivityListener() {
            @Override
            public void onChanged(boolean status) {
                if (!BdApp.getInstance().isInternet(AdminActivity.this)) {
                    noInternetDialog();
                } else {
                    if (noInternetdialog != null) {
                        noInternetdialog.dismiss();
                        showToast("error");
                        AppLoader.hideLoaderDialog();
                    }
                }
            }
        });
        navController = Navigation.findNavController(this, R.id.nav_controller);
        updateProfile();
        binding.homeLayout.bottomLayout.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminHomeFragment) {
                    navController.navigate(R.id.adminHomeFragment);
                }
            }
        });
        binding.homeLayout.bottomLayout.employeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.employeeListFragment) {
                    navController.navigate(R.id.employeeListFragment);
                }
            }
        });
        binding.homeLayout.bottomLayout.loanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.loanFragment) {
                    navController.navigate(R.id.loanFragment);
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
            Constant.calendarCallBack.openEmployeeList();
        });
        binding.homeLayout.headerLayout.backBtn.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.navigationDrawer.userName.setText(SharedPref.getUserDetails().getEmployeeName());
        binding.navigationDrawer.userJobTxt.setText(SharedPref.getUserDetails().getDesignation());
        binding.navigationDrawer.homeBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.settingBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.tmcBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.logOutBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.registerBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.profileBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.loanBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.addHolidayBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.rulesBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.desinationBtn.setOnClickListener(this::onClick);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.adminHomeFragment) {
                    navHandel("Home");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.employeeListFragment) {
                    navHandel("Employee");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.loanFragment) {
                    navHandel("Loan");
                    headerHideShow(true);
                    bottomHideShow(true);
                } else if (destination.getId() == R.id.adminProfile) {
                    navHandel("Profile");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.adminTermFragment) {
                    navHandel("TermCondition");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeDetailFragment) {
                    navHandel("EmployeeDetail");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeAttendanceFragment) {
                    navHandel("EmployeeAttendance");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.registerEmployeeFragment) {
                    navHandel("RegisterEmployee");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.settingFragment) {
                    navHandel("Setting");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeListForLoanFragment) {
                    navHandel("EmployeeLoanList");
                    headerHideShow(true);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.provideLoanFragment) {
                    navHandel("LoanDetails");
                    headerHideShow(true);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.adminHolidayFragment) {
                    navHandel("Holidays");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.attendanceRulesFragment) {
                    navHandel("attendanceRulesFragment");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.lockUnlockFragment) {
                    navHandel("Lock/Unlock");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.toDoListFragment) {
                    navHandel("ToDoList");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.designationFragment) {
                    navHandel("Designation");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeAttandenceListFragment) {
                    navHandel("EmployeeAttandenceList");
                    headerHideShow(false);
                    bottomHideShow(false);
                } else if (destination.getId() == R.id.employeeDesListFragment) {
                    navHandel("Employee List");
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
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home_select);
                binding.homeLayout.bottomLayout.employeeBtn.setImageResource(R.drawable.icn_employees);
                binding.homeLayout.bottomLayout.loanBtn.setImageResource(R.drawable.icn_loan);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "Employee":
                binding.homeLayout.headerLayout.title.setText("Employees");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.employeeBtn.setImageResource(R.drawable.icn_employee_select);
                binding.homeLayout.bottomLayout.loanBtn.setImageResource(R.drawable.icn_loan);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "Loan":
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.headerLayout.title.setText("Advance Payment");
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.employeeBtn.setImageResource(R.drawable.icn_employees);
                binding.homeLayout.bottomLayout.loanBtn.setImageResource(R.drawable.icn_loan_select);
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
                binding.homeLayout.bottomLayout.employeeBtn.setImageResource(R.drawable.icn_employees);
                binding.homeLayout.bottomLayout.loanBtn.setImageResource(R.drawable.icn_loan);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
            case "EmployeeLoanList":
                binding.homeLayout.headerLayout.title.setText("Select Employee");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
            case "LoanDetails":
                binding.homeLayout.headerLayout.title.setText("Employee List");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "Lock/unlock":
                binding.homeLayout.headerLayout.title.setText("Lock / Unlock");
                binding.homeLayout.headerLayout.addBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.backBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.headerLayout.menuBtn.setVisibility(View.GONE);
                binding.homeLayout.headerLayout.extIcon.setVisibility(View.GONE);
                break;
            case "ToDoList":
                binding.homeLayout.headerLayout.title.setText("ToDoList");
                break;
            case "Designation":
                binding.homeLayout.headerLayout.title.setText("Designation");
                break;
            case "TermCondition":
            case "EmployeeDetail":
            case "RegisterEmployee":

                noHeader();
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
                if (Objects.requireNonNull(navController.getCurrentDestination()).getId() != R.id.adminHolidayFragment) {
                    navController.navigate(R.id.adminHolidayFragment);
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
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.auth_nav);
        if (navHostFragment != null) {
            List<Fragment> fragmentList = navHostFragment.getChildFragmentManager().getFragments();
            boolean isLoginFragment = false;
            for (Fragment fragment1 : fragmentList) {
                if (fragment1 instanceof AdminHomeFragment) {
                    isLoginFragment = true;
                    break;
                }
            }
            if (isLoginFragment) {
                finish();
            }
        }
    }

    private void noHeader() {
        binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
        binding.homeLayout.bottomLayout.employeeBtn.setImageResource(R.drawable.icn_employees);
        binding.homeLayout.bottomLayout.loanBtn.setImageResource(R.drawable.icn_loan);
        binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile_select);
        binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
        binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
        binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
        binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.VISIBLE);
    }

    public void updateProfile() {
        if (SharedPref.getUserDetails().getProfile().isEmpty()) {
            textProfile();
        } else {
            Glide.with(this).load(SharedPref.getUserDetails().getProfile()).placeholder(R.drawable.user).into(binding.navigationDrawer.userProfile);
        }
    }
}