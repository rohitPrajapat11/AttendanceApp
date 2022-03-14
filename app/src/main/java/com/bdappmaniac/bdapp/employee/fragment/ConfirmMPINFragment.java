package com.bdappmaniac.bdapp.employee.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.MOBILE;
import static com.bdappmaniac.bdapp.activity.BaseActivity.PIN_KEY;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
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
import com.bdappmaniac.bdapp.databinding.FragmentConfirmMPINBinding;
import com.bdappmaniac.bdapp.databinding.FragmentCreateMPINBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class ConfirmMPINFragment extends BaseFragment implements View.OnClickListener {
    FragmentCreateMPINBinding binding;
    String confirmPin;
    String newPin;
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
        binding.otpTypeTxt.setText(R.string.Renter);
        if (getArguments() != null) {
            newPin = getArguments().getString(PIN_KEY, "");
        }
        binding.forwardTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    confirmPin = binding.pinView.getText().toString();
                    if (confirmPin.equals(newPin)) {
                        showToast(mContext.getString(R.string.correct_pin));
//                        Navigation.findNavController(v).navigate(R.id.singUpFragment);
                    } else {
                        showToast(mContext.getString(R.string.incorrect_pin));
                    }
                } else {
                }
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        if (getArguments() != null) {
            // From SignIn Otp Fragment For Forgot M-Pin
            mobile = getArguments().getString(MOBILE);
        }
        binding.pinView.setText("");
        binding.key0.setOnClickListener(this);
        binding.key1.setOnClickListener(this);
        binding.key2.setOnClickListener(this);
        binding.key3.setOnClickListener(this);
        binding.key4.setOnClickListener(this);
        binding.key5.setOnClickListener(this);
        binding.key6.setOnClickListener(this);
        binding.key7.setOnClickListener(this);
        binding.key8.setOnClickListener(this);
        binding.key9.setOnClickListener(this);
        binding.removeTXT.setOnClickListener(this);
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
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_pin));
            return false;
        }
        if (binding.pinView.length() == 1) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_6_number_pin));
            return false;
        }
        if (binding.pinView.length() == 2) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_6_number_pin));
            return false;
        }
        if (binding.pinView.length() == 3) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_6_number_pin));
            return false;
        }
        if (binding.pinView.length() == 4) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_6_number_pin));
            return false;
        }
        if (binding.pinView.length() == 5) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_6_number_pin));
            return false;
        }
        return true;
    }
}