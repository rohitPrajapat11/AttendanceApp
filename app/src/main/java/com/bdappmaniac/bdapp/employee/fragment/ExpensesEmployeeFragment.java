package com.bdappmaniac.bdapp.employee.fragment;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.bdappmaniac.bdapp.Api.response.TasksItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.EmpTaskAdapter;

import com.bdappmaniac.bdapp.databinding.FragmentExpensesEmployeeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.ArrayList;


public class ExpensesEmployeeFragment extends BaseFragment {
    FragmentExpensesEmployeeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expenses_employee, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);

        //spinner
        String [] yolist = {"yoo","yoooo","yooooo"};
        binding.spinnerExpenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter ad = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item,yolist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerExpenseType.setAdapter(ad);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.details.getVisibility() == VISIBLE) {
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setImageResource(R.drawable.additem);
                } else {
                    binding.details.setVisibility(VISIBLE);
                    binding.additem.setImageResource(R.drawable.cross);
                }
            }
        });
        binding.addNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.details.getVisibility()== VISIBLE){
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setImageResource(R.drawable.additem);
                }
            }
        });
        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.details.getVisibility()== VISIBLE){
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setImageResource(R.drawable.additem);
                }
            }
        });

        ArrayList<TasksItem> childTaskiLIst = new ArrayList<>();


//        EmpTaskAdapter adapter = new EmpTaskAdapter(childTaskiLIst ,mContext);
//        binding.recyclereee.setLayoutManager(new LinearLayoutManager(mContext));
//        binding.recyclereee.setAdapter(adapter);
         return binding.getRoot();
    }
}