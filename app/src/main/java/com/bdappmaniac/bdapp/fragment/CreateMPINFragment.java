package com.bdappmaniac.bdapp.fragment;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentCreateMPINBinding;

public class CreateMPINFragment extends BaseFragment {
    FragmentCreateMPINBinding binding;
    String mobile = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getView() != null)
                    Navigation.findNavController(getView()).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_m_p_i_n, container, false);
//        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        showKeyboard(binding.pinView);

        binding.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle createPin = new Bundle();
                createPin.putString("Key", binding.pinView.getText().toString());
                if (checkValidation()) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.confirmMPINFragment, createPin);
                }
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });

        if (binding == null) {
            if (getArguments() != null) {
                mobile = getArguments().getString("mobile");
            }

            binding.pinView.setText("");
            binding.key0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                }
            });
            binding.key1.setOnClickListener((View.OnClickListener) this);
            binding.key2.setOnClickListener((View.OnClickListener) this);
            binding.key3.setOnClickListener((View.OnClickListener) this);
            binding.key4.setOnClickListener((View.OnClickListener) this);
            binding.key5.setOnClickListener((View.OnClickListener) this);
            binding.key6.setOnClickListener((View.OnClickListener) this);
            binding.key7.setOnClickListener((View.OnClickListener) this);
            binding.key8.setOnClickListener((View.OnClickListener) this);
            binding.key9.setOnClickListener((View.OnClickListener) this);
            binding.removeTXT.setOnClickListener((View.OnClickListener) this);
            binding.pinView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        return binding.getRoot();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.key0:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "0");
                break;
            case R.id.key1:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "1");
                break;
            case R.id.key2:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "2");
                break;
            case R.id.key3:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "3");
                break;
            case R.id.key4:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "4");
                break;
            case R.id.key5:
                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "5");
                break;
            case R.id.key6:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "6");
                break;
            case R.id.key7:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "7");
                break;
            case R.id.key8:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "8");
                break;
            case R.id.key9:
                binding.pinView.getText().insert(binding.pinView.getSelectionStart(), "9");
                break;
            case R.id.removeTXT:
                deleteBufferText();
                break;
        }
    }

    private void deleteBufferText() {
        Editable editable = binding.pinView.getText();
        int start = binding.pinView.getSelectionStart();
        if (!TextUtils.isEmpty(editable)) {
            if (start > 0) {
                editable.delete(start - 1, start);
            }
        }
    }

    public boolean checkValidation() {
        if (binding.pinView.length() == 0) {
            showSnackBar(binding.getRoot(), "Please Enter PIN!");
            return false;
        }
        if (binding.pinView.length() == 1) {
            showSnackBar(binding.getRoot(), "Please Enter The 6 Numbered PIN!");
            return false;
        }
        if (binding.pinView.length() == 2) {
            showSnackBar(binding.getRoot(), "Please Enter The 6 Numbered PIN!");
            return false;
        }
        if (binding.pinView.length() == 3) {
            showSnackBar(binding.getRoot(), "Please Enter The 6 Numbered PIN!");
            return false;
        }
        if (binding.pinView.length() == 4) {
            showSnackBar(binding.getRoot(), "Please Enter The 6 Numbered PIN!");
            return false;
        }
        if (binding.pinView.length() == 5) {
            showSnackBar(binding.getRoot(), "Please Enter The 6 Numbered PIN!");
            return false;
        }
        return true;
    }
//    private  void  showKeyboard(OtpView editText){
//        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(
//                Context.INPUT_METHOD_SERVICE
//        );
//        manager.showSoftInput(editText.getRootView(), InputMethodManager.SHOW_IMPLICIT);
//        editText.requestFocus();
//    }
}