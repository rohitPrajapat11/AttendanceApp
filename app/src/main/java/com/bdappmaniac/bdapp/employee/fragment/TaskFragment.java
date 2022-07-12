package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.AllTaskItem;
import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmpTaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentTaskBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskFragment extends BaseFragment {
    FragmentTaskBinding binding;
    EmpTaskAdapter adapter;
    ArrayList<AllTaskItem> tasklist = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
        employeeTasksApi();
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
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

        adapter = new EmpTaskAdapter(tasklist,mContext);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recycleView.setAdapter(adapter);
        return binding.getRoot();
    }
    public void employeeTasksApi(){
        AppLoader.showLoaderDialog(mContext);
        MainService.employeeTasksApi(mContext, getToken()).observe((LifecycleOwner) this, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), this.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<TasksItem>>() {
                    }.getType();
                    ArrayList<AllTaskItem> taskItemlist = new Gson().fromJson(apiResponse.getData(),collectionType);
                    taskItemlist.addAll(taskItemlist);
                    adapter.setTasklist(taskItemlist);
                    adapter.notifyDataSetChanged();
//                  adapter= new EmpTaskAdapter(apiResponse.getData());
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

}