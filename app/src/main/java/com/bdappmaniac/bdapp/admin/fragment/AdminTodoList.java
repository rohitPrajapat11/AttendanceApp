package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.AllTaskItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.ToDoListAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminTodoListBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AdminTodoList extends BaseFragment {
    FragmentAdminTodoListBinding binding;
    ArrayList<AllTaskItem> tasksList = new ArrayList<>();
    ToDoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_todo_list, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        adapter = new ToDoListAdapter(tasksList, getContext());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(adapter);

        allTaskWithEmployeeNameApi();
        return binding.getRoot();
    }

    private void allTaskWithEmployeeNameApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.allTaskWithEmployeeName(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<AllTaskItem>>() {
                    }.getType();
                    List<AllTaskItem> List = new Gson().fromJson(apiResponse.getData(), collectionType);
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    tasksList.clear();
                    tasksList.addAll(List);
                    adapter.notifyDataSetChanged();
                } else {
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}