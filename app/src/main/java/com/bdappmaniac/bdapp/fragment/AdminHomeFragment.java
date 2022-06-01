package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.AdminHomeAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHomeBinding;
import com.bdappmaniac.bdapp.model.AdminHomeModel;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;

public class AdminHomeFragment extends Fragment {
    FragmentAdminHomeBinding binding;
    ArrayList<AdminHomeModel> itemList = new ArrayList<>();
    AdminHomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_home, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.f1f3f5);
//           new Handler().postDelayed(new Runnable() {
//           @Override
//            public void run() {
//                setProgress();
//            }
//              }, 1000);
//        binding.exploreTxt.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.loanFragment);
//        });
//        return binding.getRoot();
//    }
//
//    public void setProgress() {
//        binding.semiCircleArcProgressBar.setPercentWithAnimation(45);
//    }
        ArrayList<AdminHomeModel> itemList = new ArrayList<>();
        itemList.add(new AdminHomeModel(R.drawable.icn_advance_payment, "Advance Payment"));
        itemList.add(new AdminHomeModel(R.drawable.icn_attendence, "Attendance"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_employee_list, "Employee List"));
        itemList.add(new AdminHomeModel(R.drawable.icn_expense, "Expenses"));
        itemList.add(new AdminHomeModel(R.drawable.icn_holidays, "Holiday"));
        itemList.add(new AdminHomeModel(R.drawable.icn_locks, "Lock / Unlock"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_to_do_list, "OverTime List"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_to_do_list, "Task List"));

        adapter = new AdminHomeAdapter(itemList, getContext());
        binding.recyclerMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerMenu.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
    }
}