package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.listeners.OnMonthChangeListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.model.Month;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteria;
import com.applikeysolutions.cosmocalendar.settings.lists.DisabledDaysCriteriaType;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.bdappmaniac.bdapp.Api.response.EmployeeHolidayResponse;
import com.bdappmaniac.bdapp.Api.response.HolidaysItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmpHolidayAdapter;
import com.bdappmaniac.bdapp.adapter.EmployeeAttendanceListAdapter;
import com.bdappmaniac.bdapp.adapter.EmployeeHolidayAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeHolidayBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.model.ModelHolidayItems;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class EmployeeHolidayFragment extends BaseFragment {
    FragmentEmployeeHolidayBinding binding;
    EmployeeHolidayAdapter monthAdapter;
    ArrayList<ModelHolidayItems> itemsArrayList = new ArrayList<>();
    EmpHolidayAdapter holidayadapter;

    List<HolidaysItem> list = new ArrayList<>();
    List<EmployeeHolidayResponse> monthList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_holiday, container, false);
            StatusBarUtils.statusBarColor(getActivity(), R.color.primary_color);
            monthAdapter = new EmployeeHolidayAdapter(mContext, list);
//            binding.employeeHolidayRecyclers.setHasFixedSize(false);
//            binding.employeeHolidayRecyclers.setLayoutManager(new LinearLayoutManager(getContext()));
//            binding.employeeHolidayRecyclers.setAdapter(monthAdapter);
        }
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).popBackStack();
            }
        });

        ArrayList<ModelHolidayItems> itemsArrayList = new ArrayList<>();
        itemsArrayList.add(new ModelHolidayItems("Holi", "Mon", "20"));
        itemsArrayList.add(new ModelHolidayItems("Dhanteras", "Tue", "21"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems("Independence Day", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Office Leave", "Fri", "25"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems(" Ram Navmi", "Thu", "31"));
        itemsArrayList.add(new ModelHolidayItems("Maha Shivratri", "Fri", "1"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "4"));
        itemsArrayList.add(new ModelHolidayItems("Wasant Pancmi", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems("Independence Day", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Office Leave", "Fri", "25"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems(" Ram Navmi", "Thu", "31"));
        itemsArrayList.add(new ModelHolidayItems("Maha Shivratri", "Fri", "1"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "4"));
        itemsArrayList.add(new ModelHolidayItems("Wasant Pancmi", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems("Independence Day", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Office Leave", "Fri", "25"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems(" Ram Navmi", "Thu", "31"));
        itemsArrayList.add(new ModelHolidayItems("Maha Shivratri", "Fri", "1"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "4"));
        itemsArrayList.add(new ModelHolidayItems("Wasant Pancmi", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems("Independence Day", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Office Leave", "Fri", "25"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems(" Ram Navmi", "Thu", "31"));
        itemsArrayList.add(new ModelHolidayItems("Maha Shivratri", "Fri", "1"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "4"));
        itemsArrayList.add(new ModelHolidayItems("Wasant Pancmi", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems("Independence Day", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Office Leave", "Fri", "25"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "22"));
        itemsArrayList.add(new ModelHolidayItems(" Ram Navmi", "Thu", "31"));
        itemsArrayList.add(new ModelHolidayItems("Maha Shivratri", "Fri", "1"));
        itemsArrayList.add(new ModelHolidayItems("Goverdhan Pooja", "Wed", "4"));
        itemsArrayList.add(new ModelHolidayItems("Wasant Pancmi", "Thu", "24"));
        itemsArrayList.add(new ModelHolidayItems("Good Friday", "Fri", "9"));
        itemsArrayList.add(new ModelHolidayItems("Office Holiday", "Sat", "15"));

        binding.addBtn.setVisibility(View.GONE);
        holidayadapter = new EmpHolidayAdapter(itemsArrayList, mContext);
        binding.recyclerHolidays.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerHolidays.setAdapter(holidayadapter);

//        binding.recyclerHolidays.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    binding.calenderview.expand(0);
//
//                } else if (dy < 0) {
//                    binding.calenderview.collapse(0);
//
//                } else {
//                    System.out.println("No Vertical Scrolled");
//                }
//
//            }
//
//
//
//        });

        binding.recyclerHolidays.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override

                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!recyclerView.canScrollVertically(1) && dy > 0)
                    {
                //       binding.calenderview.c
                        Log .e("scroll","bottom");
                        Toast.makeText(mContext, "scrolled d", Toast.LENGTH_SHORT).show();
                    }else if (!recyclerView.canScrollVertically(-1) && dy < 0)
                    {
                      Toast.makeText(mContext, "scrolled u", Toast.LENGTH_SHORT).show();
                        Log.e("scroll","top");
                    }
                }
            });


        return binding.getRoot();
    }


    public void holidaysOfCurrentYearApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.holidaysOfCurrentYear(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<HolidaysItem>>() {
                    }.getType();
                    List<HolidaysItem> monthList = new Gson().fromJson(apiResponse.getData(), collectionType);
                    list.clear();
                    list.addAll(monthList);
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    monthAdapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        holidaysOfCurrentYearApi();
    }
}