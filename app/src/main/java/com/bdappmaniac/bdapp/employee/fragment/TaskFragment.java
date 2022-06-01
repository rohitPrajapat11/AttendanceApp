package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.TaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentTaskBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelTaskList;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.ArrayList;

public class TaskFragment extends BaseFragment {
    FragmentTaskBinding binding;
    TaskAdapter Adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
//        binding.addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (StringHelper.isEmpty(binding.taskEdit.getText().toString())) {
//                    showSnackBar(v, mContext.getString(R.string.task_is_empty));
//                } else {
//                    taskList.add(binding.taskEdit.getText().toString());
//                    Adapter.notifyDataSetChanged();
//                    binding.taskEdit.setText("");
//                }
//            }
//        });


        ArrayList<ModelTaskList> taskList = new ArrayList<>();
        taskList.add(new ModelTaskList("20-03-2022", "Fix the bug ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("21-03-2022", "Comple the remaining screens in  time", "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        taskList.add(new ModelTaskList("23-03-2022", "App  working flow is not correct ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        taskList.add(new ModelTaskList("26-03-2022", "Ui changes", "discription will be here in brief about the above  title ", "27-03-2022", R.drawable.warning, "Correction"));
        taskList.add(new ModelTaskList("20-03-2022", "Ui changes", "complete the changes", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("20-03-2022", "Fix the bug ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("21-03-2022", "Comple the remaining screens in time", "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        taskList.add(new ModelTaskList("23-03-2022", "App  working flow is not correct ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        taskList.add(new ModelTaskList("26-03-2022", "ui changes", "discription will be here in brief about the above  title ", "27-03-2022", R.drawable.warning, "Correction"));
        taskList.add(new ModelTaskList("20-03-2022", "Ui changes", "complete the changes", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("20-03-2022", "Fix the bug ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("21-03-2022", "Comple the remaining screens in time", "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        taskList.add(new ModelTaskList("23-03-2022", "App  working flow is not correct ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        taskList.add(new ModelTaskList("26-03-2022", "Ui changes", "discription will be here in brief about the above  title ", "27-03-2022", R.drawable.warning, "Correction"));
        taskList.add(new ModelTaskList("20-03-2022", "ui changes", "complete the changes", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("20-03-2022", "Fix the bug ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("21-03-2022", "Comple the remaining screens in time", "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        taskList.add(new ModelTaskList("23-03-2022", "App  working flow is not correct ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        taskList.add(new ModelTaskList("26-03-2022", "Ui changes", "discription will be here in brief about the above  title ", "27-03-2022", R.drawable.warning, "Correction"));
        taskList.add(new ModelTaskList("20-03-2022", "Ui changes", "complete the changes", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("20-03-2022", "Fix the bug ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.bug, "Bug"));
        taskList.add(new ModelTaskList("21-03-2022", "Comple the remaining screens in time", "discription will be here in brief about the above  title complete the changes", "28-03-2022", R.drawable.task, "Task"));
        taskList.add(new ModelTaskList("23-03-2022", "App  working flow is not correct ", "discription will be here in brief about the above  title ", "25-03-2022", R.drawable.error, "Error"));
        taskList.add(new ModelTaskList("26-03-2022", "Ui changes", "discription will be here in brief about the above  title ", "27-03-2022", R.drawable.warning, "Correction"));
        taskList.add(new ModelTaskList("20-03-2022", "Ui changes", "complete the changes", "25-03-2022", R.drawable.bug, "Bug"));


        Adapter = new TaskAdapter(mContext, taskList);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(Adapter);
        return binding.getRoot();
    }

}