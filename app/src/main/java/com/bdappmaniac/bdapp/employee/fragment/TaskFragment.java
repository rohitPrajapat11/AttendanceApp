package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.EmpTaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentTaskBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelEmpTask;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.ArrayList;

public class TaskFragment extends BaseFragment {
    FragmentTaskBinding binding;
    EmpTaskAdapter adapter;
    ArrayList<ModelEmpTask> childTaskList =new ArrayList<>();



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

        ArrayList<ModelEmpTask> childTaskiLIst = new ArrayList<>();
        childTaskiLIst.add(new ModelEmpTask("21-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("22-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("23-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("25-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("27-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("29-03-2022"));
        childTaskiLIst.add(new ModelEmpTask("1-04-2022"));
        childTaskiLIst.add(new ModelEmpTask("4-04-2022"));

        EmpTaskAdapter adapter = new EmpTaskAdapter(childTaskiLIst ,mContext);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recycleView.setAdapter(adapter);



        return binding.getRoot();
    }

}