package com.bdappmaniac.bdapp.auth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.HomeActivity;

import com.bdappmaniac.bdapp.databinding.FragmentOTPBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;

public class OTPFragment extends BaseFragment implements View.OnClickListener {
    FragmentOTPBinding binding;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_o_t_p, container, false);
        binding.forwardTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    getActivity().finish();
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
            mobile = getArguments().getString("mobile");
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
        BiometricManager manager = BiometricManager.from(mContext);
        switch (manager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getContext(), mContext.getString(R.string.dont_have_biometric_scanner), Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getContext(), mContext.getString(R.string.dont_have_fingerprint_saved) + mContext.getString(R.string.check_your_security_settings), Toast.LENGTH_SHORT).show();
                break;
        }

        BiometricPrompt prompt = new BiometricPrompt(this, ContextCompat.getMainExecutor(mContext), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getContext(), mContext.getString(R.string.correct_pin), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, HomeActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(mContext.getString(R.string.biometric))
                .setNegativeButtonText(mContext.getString(R.string.cancel))
                .build();
        binding.fingerPrintScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt.authenticate(info);
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