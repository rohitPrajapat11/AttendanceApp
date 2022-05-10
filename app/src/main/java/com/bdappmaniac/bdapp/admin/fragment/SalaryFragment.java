package com.bdappmaniac.bdapp.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentSalaryBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SalaryFragment extends Fragment {
    FragmentSalaryBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance(Locale.ENGLISH);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_salary, container, false);
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.ivCalendarNext.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, 1);
            setUpCalendar();
        });

        binding.ivCalendarPrevious.setOnClickListener(v -> {
            cal.add(Calendar.MONTH, -1);
            setUpCalendar();
        });
        return binding.getRoot();
    }

    private void setUpCalendar() {
        String currentString = sdf.format(cal.getTime());
        String[] separated = currentString.split(" ");
        binding.monthTxt.setText(separated[0]);
        binding.yearTxt.setText(separated[1]);
    }
}