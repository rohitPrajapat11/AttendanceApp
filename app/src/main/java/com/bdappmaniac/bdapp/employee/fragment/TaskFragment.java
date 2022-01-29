package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.TaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentTaskBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.ArrayList;

public class TaskFragment extends BaseFragment {
    FragmentTaskBinding binding;
    private TaskAdapter Adapter;
    ArrayList<String> taskList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.isEmpty(binding.taskEdit.getText().toString())) {
                    showSnackBar(v, "Task is empty");
                } else {
                    taskList.add(binding.taskEdit.getText().toString());
                    Adapter.notifyDataSetChanged();
                    binding.taskEdit.setText("");
                }
            }
        });
        Adapter = new TaskAdapter(taskList, getContext());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(Adapter);
        return binding.getRoot();
    }
}