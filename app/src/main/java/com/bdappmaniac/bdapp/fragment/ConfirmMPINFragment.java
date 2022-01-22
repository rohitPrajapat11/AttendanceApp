package com.bdappmaniac.bdapp.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentConfirmMPINBinding;

public class ConfirmMPINFragment extends BaseFragment {
    FragmentConfirmMPINBinding binding;
    String confirmPin;
    String newPin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_m_p_i_n, container, false);
        confirmPin = binding.pinView.getText().toString();
        if(getArguments()!=null)
        {
            newPin = getArguments().getString("Key","");
        }
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmPin.equals(newPin)){
                    Toast.makeText(getContext(), "All Done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Incorrect Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        return binding.getRoot();
    }
}