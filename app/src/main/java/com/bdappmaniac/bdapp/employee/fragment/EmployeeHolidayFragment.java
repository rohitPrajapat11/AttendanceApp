package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.EmpHolidayDataItems;
import com.bdappmaniac.bdapp.Api.response.EmployeeTaskDataItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmpHolidayAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeHolidayBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHolidayFragment extends BaseFragment {
    FragmentEmployeeHolidayBinding binding;
    ArrayList<EmpHolidayDataItems> monthlist = new ArrayList<>();
    EmpHolidayAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_holiday, container, false);
            StatusBarUtils.statusBarColor(getActivity(), R.color.white);
            binding.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });

            adapter= new EmpHolidayAdapter(monthlist,mContext);
            binding.recyclerholiday.setAdapter(adapter);

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
//        });
            binding.collapsibleCalendar.addEventTag(2022, 5, 9);
            
        }
        return binding.getRoot();
    }

    public void holidaysOfCurrentYearApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.holidaysOfCurrentYear(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<EmpHolidayDataItems>>() {
                    }.getType();
                    monthlist.clear();
                    monthlist.addAll(new Gson().fromJson(apiResponse.getData(), collectionType));
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    adapter.notifyDataSetChanged();
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
        AppLoader.hideLoaderDialog();
        holidaysOfCurrentYearApi();
    }
}