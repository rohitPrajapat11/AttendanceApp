package com.bdappmaniac.bdapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;



import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentCreateMPINBinding;
import com.chaos.view.PinView;
import com.mukesh.OtpView;

public class CreateMPINFragment extends Fragment {
    FragmentCreateMPINBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_m_p_i_n, container, false);
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        showKeyboard(binding.pinView);

        binding.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle createPin = new Bundle();
                createPin.putString("Key", binding.pinView.getText().toString());
                Navigation.findNavController(binding.getRoot()).navigate(R.id.confirmMPINFragment,createPin);
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

    private  void  showKeyboard(OtpView editText){
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE
        );
        manager.showSoftInput(editText.getRootView(), InputMethodManager.SHOW_IMPLICIT);
        editText.requestFocus();
    }
}