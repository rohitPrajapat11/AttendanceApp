package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.ApiResponse;
import com.bdappmaniac.bdapp.Api.response.EmployeeTaskDataItem;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceData;
import com.bdappmaniac.bdapp.Api.response.MyAttendanceItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.MyAttendanceHistoryAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentMyAttendanceBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MyAttendance extends BaseFragment {
    public FragmentMyAttendanceBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance();
    List<MyAttendanceData> datalist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_attendance, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);

        setUpCalendar();
        myAttendanceApi();

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        //calander on click L&R
        binding.leftBtn.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, -1);
            setUpCalendar();
        });
        binding.rightBtn.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, 1);
            setUpCalendar();
        });
        return binding.getRoot();
    }

    // caalnder
    private void setUpCalendar() {
        String currentString = sdf.format(cal.getTime());
        String[] separated = currentString.split(" ");
        binding.monthTxt.setText(separated[0]);
        binding.yearTxt.setText(separated[1]);
        recyclerSetAdapter(separated[0]);
    }

    //all Attendance Api
    public void myAttendanceApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.myAttendanceApi(mContext, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<MyAttendanceData>>() {
                    }.getType();
                    datalist.addAll(new Gson().fromJson(apiResponse.getData(), collectionType));
                    recyclerSetAdapter(binding.monthTxt.getText().toString());
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
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
    }
    private void recyclerSetAdapter(String month) {
        int monthNumber = DateUtils.getMonthNumber(month) + 1;
        for (int i = 0; i < datalist.size(); i++) {
            if (monthNumber == Integer.parseInt(datalist.get(i).getMonth())) {
                MyAttendanceHistoryAdapter historyAdapter = new MyAttendanceHistoryAdapter(datalist.get(i).getAttendance(), mContext);
                binding.historyRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                binding.historyRecycler.setAdapter(historyAdapter);
                break;
            }
        }
    }

}