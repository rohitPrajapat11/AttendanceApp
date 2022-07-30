package com.bdappmaniac.bdapp.employee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.adapter.AaaadapterVp;
import com.bdappmaniac.bdapp.databinding.FragmentVpfragmentBinding;
import com.bdappmaniac.bdapp.helper.HorizontalMarginItemDecoration;
import com.bdappmaniac.bdapp.utils.DateUtils;

import java.util.Date;
public class Vpfragment extends Fragment {
    FragmentVpfragmentBinding binding;
    AaaadapterVp adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vpfragment, container, false);


        adapter = new AaaadapterVp(getContext());

        // adding the adapter to viewPager2
        // to show the views in recyclerview
        binding.viewpager.setAdapter(adapter);

        // To get swipe event of viewpager2
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            // triggered when there is
            // scroll state will be changed
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        binding.viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewpager.setOffscreenPageLimit(2);
        float nextItemVisiblePx = getContext().getResources().getDimension(R.dimen.viewpager_next_item_visible);
        float currentItemHorizontalMarginPx = getContext().getResources().getDimension(R.dimen.viewpager_current_item_horizontal_margin);
        float pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx;
        ViewPager2.PageTransformer pageTransformer = new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setTranslationX(-pageTranslationX * position);
                page.setScaleY((float) 1 - 0.35F * Math.abs(position));
//                 page.setAlpha(0.25F + ((float)1 - Math.abs(position)));
            }
        };
        binding.viewpager.setPageTransformer(pageTransformer);
        HorizontalMarginItemDecoration horizontalMarginItemDecoration = new HorizontalMarginItemDecoration(getContext(), R.dimen.viewpager_current_item_horizontal_margin);
        binding.viewpager.addItemDecoration(horizontalMarginItemDecoration);

        binding.getdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                today();
                binding.text.setText(binding.datePicker.getDayOfMonth() + "/" + DateUtils.getMonthName(binding.datePicker.getMonth()) + "/" + binding.datePicker.getYear());
//                binding.text2Tv.setText(binding.timepicker.getHour() + "/" + binding.timepicker.getMinute());
                binding.text2Tv.setText(DateUtils.getPrettyDate(binding.text.getText().toString(),"dd/MMMM/yyyy","dd"));
                binding.text3.setText(binding.timepicker.getHour()+":"+binding.timepicker.getMinute());
            }
        });
//        binding.text2Tv.setText(DateUtils.isToday(binding.datePicker.getDayOfMonth(), "dd-MM-yyy"));
//        max and minimun dates
//        binding.datePicker.setMaxDate(System.currentTimeMillis());
//        binding.datePicker.setMinDate(System.currentTimeMillis() - 86400000);
        return binding.getRoot();
        }

}