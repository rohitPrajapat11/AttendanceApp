package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.EmpTaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentTaskBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelEmpTask;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.ArrayList;

public class TaskFragment extends BaseFragment {
    FragmentTaskBinding binding;
    EmpTaskAdapter adapter;
    ArrayList<ModelEmpTask> childTaskList =new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
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
        ArrayList<ModelEmpTask> childTaskiLIst = new ArrayList<>();
        childTaskiLIst.add(new ModelEmpTask("March 20,2022"));
        childTaskiLIst.add(new ModelEmpTask("March 22,2022"));
        childTaskiLIst.add(new ModelEmpTask("March 23,2022"));
        childTaskiLIst.add(new ModelEmpTask("March 26,2022"));
        childTaskiLIst.add(new ModelEmpTask("March 28,2022"));
        childTaskiLIst.add(new ModelEmpTask("March 29,2022"));


        EmpTaskAdapter adapter = new EmpTaskAdapter(childTaskiLIst ,mContext);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recycleView.setAdapter(adapter);



        return binding.getRoot();
    }

}