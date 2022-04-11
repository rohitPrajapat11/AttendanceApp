package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.TaskAdapter;
import com.bdappmaniac.bdapp.adapter.ToDoListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminTodoListBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.ArrayList;

public class AdminTodoList extends BaseFragment {
FragmentAdminTodoListBinding binding;
    ArrayList<String> tasksList = new ArrayList<>();
    private ToDoListAdapter Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_todo_list,container,false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });


        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringHelper.isEmpty(binding.taskEdit.getText().toString())) {
                    showSnackBar(v, mContext.getString(R.string.task_is_empty));
                } else {
                    tasksList.add(binding.taskEdit.getText().toString());
                    Adapter.notifyDataSetChanged();
                    binding.taskEdit.setText("");
                }
            }
        });
        Adapter = new ToDoListAdapter(tasksList, getContext());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(Adapter);


        return binding.getRoot();
    }
}