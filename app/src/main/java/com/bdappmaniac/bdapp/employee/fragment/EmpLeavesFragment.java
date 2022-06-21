package com.bdappmaniac.bdapp.employee.fragment;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.LeavesAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmpLeavesBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.model.ModelLeaves;

import java.util.ArrayList;


public class EmpLeavesFragment extends BaseFragment {
    FragmentEmpLeavesBinding binding;
    LeavesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_leaves, container, false);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              takeMeHome();
            }
        });
        binding.applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.details.setVisibility(View.GONE);
                binding.additem.setImageResource(R.drawable.additem);
            }
        });
        binding.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.details.getVisibility()==VISIBLE){
                    binding.additem.setImageResource(R.drawable.cross);

                }
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
        binding.applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.details.getVisibility()== VISIBLE){
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setImageResource(R.drawable.additem);
                }
            }
        });

        ArrayList<ModelLeaves> leavelist = new ArrayList<>();
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "rejected"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "rejected"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "rejected"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "rejected"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));

        adapter = new LeavesAdapter(leavelist, getContext());
        binding.recyclerLeaves.setAdapter(adapter);
        return binding.getRoot();

    }
}