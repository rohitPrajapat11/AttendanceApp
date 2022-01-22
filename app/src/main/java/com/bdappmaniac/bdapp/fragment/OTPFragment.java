package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentOTPBinding;

import java.util.ConcurrentModificationException;

public class OTPFragment extends Fragment {
    FragmentOTPBinding binding;
    String pin = "1234";
    String pins;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_o_t_p, container, false);
        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pins = binding.pinView.getText().toString();

                if(pin.equals(pins)){
                    Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }
}