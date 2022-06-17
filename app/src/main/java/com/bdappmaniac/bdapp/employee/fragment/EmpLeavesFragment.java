package com.bdappmaniac.bdapp.employee.fragment;

import static android.view.View.VISIBLE;

import android.graphics.drawable.Animatable;
import android.os.Bundle;

import androidx.compose.animation.core.Animation;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.LeavesAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmpLeavesBinding;
import com.bdappmaniac.bdapp.model.ModelLeaves;

import java.util.ArrayList;


public class EmpLeavesFragment extends Fragment {
    FragmentEmpLeavesBinding binding;
    LeavesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emp_leaves, container, false);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
        binding.applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.details.setVisibility(View.GONE);
                binding.additem.setVisibility(VISIBLE);
            }
        });
        binding.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.details.getVisibility()==VISIBLE){
                    binding.additem.setVisibility(View.GONE);

                }
            }
        });
        binding.newLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.details.getVisibility() == VISIBLE) {
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setVisibility(VISIBLE);
                } else {
                    binding.details.setVisibility(VISIBLE);
                    binding.additem.setVisibility(View.GONE);
                }
            }
        });
        binding.applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.details.getVisibility()== VISIBLE){
                    binding.details.setVisibility(View.GONE);
                    binding.additem.setVisibility(VISIBLE);
                }
            }
        });

        ArrayList<ModelLeaves> leavelist = new ArrayList<>();
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
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "rejected"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "pending"));
        leavelist.add(new ModelLeaves("Urgent piece of work", "Casual Leave", "20-03-2022", "25-03-2022", "Urgent work need to get it done right now ", "approved"));

        adapter = new LeavesAdapter(leavelist, getContext());
        binding.recyclerLeaves.setAdapter(adapter);
        return binding.getRoot();

    }
}