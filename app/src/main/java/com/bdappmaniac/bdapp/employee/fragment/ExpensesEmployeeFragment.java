package com.bdappmaniac.bdapp.employee.fragment;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.EmpTaskAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmpLeavesBinding;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeExpensesBinding;
import com.bdappmaniac.bdapp.databinding.FragmentExpensesEmployeeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelEmpTask;

import java.util.ArrayList;


public class ExpensesEmployeeFragment extends BaseFragment {
FragmentExpensesEmployeeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_expenses_employee,container,false);



        binding.newExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.details.getVisibility() == VISIBLE) {
                    binding.details.setVisibility(View.GONE);
                } else {
                    binding.details.setVisibility(VISIBLE);
                }
            }
        });

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
        binding.recyclereee.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclereee.setAdapter(adapter);
         return binding.getRoot();
    }
}