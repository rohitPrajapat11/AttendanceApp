package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.ViewPagerAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttandenceListBinding;

public class EmployeeAttandenceListFragment extends Fragment {
    FragmentEmployeeAttandenceListBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attandence_list, container, false);
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
        });

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.add(new FragmentViewOne(), "Page 1");
        viewPagerAdapter.add(new FragmentViewTwo(), "Page 2");
        binding.viewpager.setAdapter(viewPagerAdapter);
        return binding.getRoot();
    }

}