package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.AdminHomeAdaapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHomeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.AdminHomeModel;

import java.util.ArrayList;


public class AdminHomeFragment extends Fragment {
    FragmentAdminHomeBinding binding;
    ArrayList<AdminHomeModel> itemList = new ArrayList<>();
    private AdminHomeAdaapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_home, container, false);
/*        //   new Handler().postDelayed(new Runnable() {
        //          @Override
//            public void run() {
//                setProgress();
//            }
        //      }, 1000);

        //      binding.exploreTxt.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.loanFragment);
//        });
//        return binding.getRoot();
//    }
//
//    public void setProgress() {
//        binding.semiCircleArcProgressBar.setPercentWithAnimation(45);
//    }*/
        ArrayList<AdminHomeModel> itemList = new ArrayList<>();
        itemList.add(new AdminHomeModel(R.drawable.icn_loan, "Adavance Payment"));
        itemList.add(new AdminHomeModel(R.drawable.icn_holiday, "Employee List"));
        itemList.add(new AdminHomeModel(R.drawable.icn_holidays, "Holiday"));
        itemList.add(new AdminHomeModel(R.drawable.icn_lock, "Lock / Unlock"));
        itemList.add(new AdminHomeModel(R.drawable.icn_rules, "To Do List"));

        adapter = new AdminHomeAdaapter(itemList, getContext());
        binding.recyclerMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerMenu.setAdapter(adapter);

        return binding.getRoot();
    }
}