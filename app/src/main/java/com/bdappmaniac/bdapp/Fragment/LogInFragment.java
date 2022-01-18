package com.bdappmaniac.bdapp.Fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.Utils.StatusBarUtils;
import com.bdappmaniac.bdapp.Utils.StringHelper;
import com.bdappmaniac.bdapp.Utils.ValidationUtils;
import com.bdappmaniac.bdapp.databinding.FragmentLogInBinding;

public class LogInFragment extends BaseFragment {
    FragmentLogInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.singUpFragment);
            }
        });
        binding.backBtn.setOnClickListener(view -> {
            requireActivity().finish();
        });

        StatusBarUtils.statusBarColor(requireActivity(), R.color.transparent);
        binding.emailTxt.addTextChangedListener(new TextChange(binding.emailTxt));
        binding.passwordTxt.addTextChangedListener(new TextChange(binding.passwordTxt));
        binding.adminBtn.setOnClickListener(view -> {
            view.setBackgroundResource(R.drawable.green_10r_bg);
            binding.adminBtn.setTextColor(Color.parseColor("#FFFFFF"));
            view.setElevation(5);
            binding.employeeBtn.setElevation(1);
            binding.employeeBtn.setTextColor(Color.parseColor("#172B4D"));
            binding.employeeBtn.setBackgroundResource(R.drawable.white_10r_white_bg);
            //  binding.loginTypeTxt.setText("Sign In as Admin");
        });
        binding.employeeBtn.setOnClickListener(view -> {
            view.setBackgroundResource(R.drawable.green_10r_bg);
            binding.employeeBtn.setTextColor(Color.parseColor("#FFFFFF"));
            binding.adminBtn.setTextColor(Color.parseColor("#172B4D"));
            view.setElevation(5);
            binding.adminBtn.setElevation(1);
            //  binding.loginTypeTxt.setText("Sign In as Employee");
            binding.adminBtn.setBackgroundResource(R.drawable.white_10r_white_bg);
        });
        binding.loginBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                Toast.makeText(mContext, "All Done", Toast.LENGTH_SHORT).show();
            }
        });
        binding.createAccountBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.singUpFragment);
        });
        binding.forgetPasswordBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.forgotPasswordFragment);
        });
        return binding.getRoot();
    }

    private boolean isAllFieldFillUp() {
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Email Name!");
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Valid Email!");
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Password!");
            return false;
        }

        return true;
    }

    public class TextChange implements TextWatcher {
        View view;

        private TextChange(View v) {
            view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.primary_color));
            } else {
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color._A8A8A8));
            }
            isFieldFillUp();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            setTextViewDrawableColor(binding.emailTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emailTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            setTextViewDrawableColor(binding.passwordTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.passwordTxt, R.color._172B4D);
        }
        setValidations();
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.loginBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.loginBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.loginBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.loginBtn.setTextColor(ContextCompat.getColor(mContext, R.color._172B4D));
        }
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
}