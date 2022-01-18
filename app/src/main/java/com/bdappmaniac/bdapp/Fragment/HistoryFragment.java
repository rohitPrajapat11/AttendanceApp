package com.bdappmaniac.bdapp.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.Utils.StatusBarUtils;
import com.bdappmaniac.bdapp.databinding.FragmentHistoryBinding;
import com.bdappmaniac.bdapp.databinding.HistoryItemBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        String[] category = new String[]{"History Task 1", "History Task 2", "History Task 3",
                "History Task 4", "History Task 5", "History Task 5"};
        List<String> taskList = new ArrayList<>(Arrays.asList(category));
        HistoryAdapter notificationAdapter = new HistoryAdapter(getContext(), taskList);
        binding.historyRecycler.setAdapter(notificationAdapter);
        return binding.getRoot();
    }
}