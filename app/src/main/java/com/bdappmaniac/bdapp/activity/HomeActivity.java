package com.bdappmaniac.bdapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.bdappmaniac.bdapp.CalendarCallBack;
import com.bdappmaniac.bdapp.Constant;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.databinding.ActivityHomeBinding;
import com.google.firebase.crashlytics.buildtools.api.net.Constants;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, CalendarCallBack {
    ActivityHomeBinding binding;
    NavController navController;

    /*  CalendarAdapter adapter;
      SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
      Calendar cal = Calendar.getInstance(Locale.ENGLISH);
      Calendar currentDate = Calendar.getInstance(Locale.ENGLISH);
      ArrayList<Date> dates = new ArrayList<Date>();
      ArrayList<CalendarDateModel> calendarList2 = new ArrayList<>();
      int todayDate = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        StatusBarUtils.statusBarColor(this, R.color.white);
        navController = Navigation.findNavController(this, R.id.nav_controller);

     /*   adapter = new CalendarAdapter(this,todayDate);
        binding.homeLayout.calendarRecycler.setAdapter(adapter);*/
        //  setUpCalendar();
        textProfile();
        binding.homeLayout.bottomLayout.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.homeFragment);
            }
        });
        binding.homeLayout.bottomLayout.taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.taskFragment);
            }
        });
        binding.homeLayout.bottomLayout.historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.historyFragment);
            }
        });
        binding.homeLayout.bottomLayout.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.profileFragment);
            }
        });
        binding.homeLayout.headerLayout.menuBtn.setOnClickListener(v -> {
            drawerOpenCLose();
        });
        binding.homeLayout.headerLayout.calendarBtn.setOnClickListener(v -> {
            Constant.calendarCallBack.openCalendar();
        });
        binding.navigationDrawer.homeBtn.setOnClickListener(this::onClick);
        binding.navigationDrawer.settingBtn.setOnClickListener(this::onClick);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.homeFragment) {
                    navHandel("Home");
                    headerHideShow(true);
                } else if (destination.getId() == R.id.taskFragment) {
                    navHandel("Task");
                    headerHideShow(true);
                } else if (destination.getId() == R.id.historyFragment) {
                    navHandel("History");
                    headerHideShow(true);
                } else if (destination.getId() == R.id.profileFragment) {
                    navHandel("Profile");
                    headerHideShow(false);
                }
            }
        });
    }

    void navHandel(String type) {
        switch (type) {
            case "Home":
                binding.homeLayout.headerLayout.title.setText("Home");
                binding.homeLayout.headerLayout.calendarBtn.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home_select);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);
                break;
            case "Task":
                binding.homeLayout.headerLayout.title.setText("Task");
                binding.homeLayout.headerLayout.calendarBtn.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task_select);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);

                break;
            case "History":
                binding.homeLayout.headerLayout.calendarBtn.setVisibility(View.VISIBLE);
                binding.homeLayout.headerLayout.title.setText("Working History");
                binding.homeLayout.bottomLayout.homeBtn.setImageResource(R.drawable.icn_home);
                binding.homeLayout.bottomLayout.taskBtn.setImageResource(R.drawable.icn_task);
                binding.homeLayout.bottomLayout.historyBtn.setImageResource(R.drawable.icn_history_select);
                binding.homeLayout.bottomLayout.profileBtn.setImageResource(R.drawable.icn_profile);
                binding.homeLayout.bottomLayout.homeIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.taskIndicator.setVisibility(View.GONE);
                binding.homeLayout.bottomLayout.historyIndicator.setVisibility(View.VISIBLE);
                binding.homeLayout.bottomLayout.profileIndicator.setVisibility(View.GONE);

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
        switch (v.getId()) {
            case R.id.homeBtn:
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.settingBtn:
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

    /*
        private void setUpCalendar() {
            ArrayList<CalendarDateModel> calendarList = new ArrayList<>();
            Calendar monthCalendar = (Calendar) cal.clone();
            int maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            dates.clear();
            monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
            while (dates.size() < maxDaysInMonth) {
                if(monthCalendar.getTime().getDate() == todayDate)
                {
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
            binding.homeLayout.calendarRecycler.scrollToPosition(adapter.getItemCount()-1);
        }*/
    void textProfile() {
        Bitmap bitmap = TextToBitmap.textToBitmap(binding.navigationDrawer.userName.getText().toString(), this, 10, R.color.black);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        binding.navigationDrawer.userProfile.setImageDrawable(d);
    }


    @Override
    public void openCalendar() {

    }
}