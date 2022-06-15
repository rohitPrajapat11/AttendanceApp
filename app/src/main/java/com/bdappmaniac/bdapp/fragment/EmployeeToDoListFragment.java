package com.bdappmaniac.bdapp.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.adapter.EmployeeToDoListItemAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeToDoListBinding;
import com.bdappmaniac.bdapp.databinding.TaskAddDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.model.ModelEmpTask;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class EmployeeToDoListFragment extends BaseFragment {
    FragmentEmployeeToDoListBinding binding;
    ArrayList<TasksItem> list = new ArrayList<>();
    EmployeeToDoListItemAdapter adapter;
    //    AllTaskItem allTaskItem;
    ArrayList<ModelEmpTask> modelEmpTask;
    private int TYear, TMonth, TDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_to_do_list, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
//            modelEmpTask = (ModelEmpTask) bundle.getSerializable("EmployeeTaskList");
        }
        adapter = new EmployeeToDoListItemAdapter(getContext(), modelEmpTask);
//        binding.childRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.childRecycler.setAdapter(adapter);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialogBox();
            }
        });
        binding.completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.completeBtn.setVisibility(View.GONE);
                binding.completedBtn.setVisibility(View.VISIBLE);
            }
        });
        binding.completedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.completedBtn.setVisibility(View.GONE);
                binding.completeBtn.setVisibility(View.VISIBLE);
            }
        });
        return binding.getRoot();
    }

    public void addTaskDialogBox() {
        TaskAddDialogBinding taskBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.task_add_dialog, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(taskBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        taskBinding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (taskBinding.reasonTxt.getText().toString().isEmpty()) {
//                    showSnackBar(binding.getRoot(), "Field cannot stay empty");
//                } else {
//                    SharedPref.init(mContext);
////                    String emp_id = String.valueOf(ModelEmpTask.getEmpId());
////                    Log.d("asdfg", list.toString());
//                    String content = taskBinding.reasonTxt.getText().toString();
////                    createTaskApi(emp_id, content);
//                    dialog.dismiss();
//                }
                dialog.dismiss();
            }
        });
        taskBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void createTaskApi(String emp_id, String content) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("emp_id", toRequestBody(emp_id));
        map.put("content", toRequestBody(content));
        MainService.createTask(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showToast(mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<TasksItem>>() {
                    }.getType();
                    List<TasksItem> List = new Gson().fromJson(apiResponse.getData(), collectionType);
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    list.clear();
                    list.addAll(List);
                    adapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showToast(apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }
}