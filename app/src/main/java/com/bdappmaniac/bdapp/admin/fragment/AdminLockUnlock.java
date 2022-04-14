package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.LockUnlockAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminLockUnlockBinding;
import com.bdappmaniac.bdapp.model.LockUnlockModel;

import java.util.ArrayList;


public class AdminLockUnlock extends Fragment {
    FragmentAdminLockUnlockBinding binding;
    ArrayList<LockUnlockModel> employeeList = new ArrayList<>();
    private LockUnlockAdapter lAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_lock_unlock, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        ArrayList<LockUnlockModel> employeList = new ArrayList<>();
        employeList.add(new LockUnlockModel("Rohit Parajapat", "6:35 PM"));
        employeList.add(new LockUnlockModel("Rohit Soni", "6:35 PM"));
        employeList.add(new LockUnlockModel("Hinmanshu Malviya", "6:35 PM"));
        employeList.add(new LockUnlockModel("Harshit Agarwal", "6:35 PM"));
        employeList.add(new LockUnlockModel("Mayank Soni", "6:35 PM"));
        employeList.add(new LockUnlockModel("Ajay Suthar", "6:35 PM"));

        lAdapter = new LockUnlockAdapter(employeList, getContext());
        binding.recyclerlockUnlock.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerlockUnlock.setAdapter(lAdapter);
        return binding.getRoot();
    }
}