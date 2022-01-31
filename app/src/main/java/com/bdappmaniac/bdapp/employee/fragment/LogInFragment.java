package com.bdappmaniac.bdapp.employee.fragment;

import android.content.Intent;
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

import com.bdappmaniac.bdapp.activity.AuthActivity;
import com.bdappmaniac.bdapp.activity.HomeActivity;
import com.bdappmaniac.bdapp.databinding.FragmentLogInBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;

public class LogInFragment extends BaseFragment {
    FragmentLogInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        binding.backBtn.setOnClickListener(view -> {
            requireActivity().finish();
        });

        StatusBarUtils.statusBarColor(requireActivity(), R.color.transparent);
        binding.emailTxt.addTextChangedListener(new TextChange(binding.emailTxt));
        binding.mpinTxt.addTextChangedListener(new TextChange(binding.mpinTxt));
//        binding.adminBtn.setOnClickListener(view -> {
//            view.setBackgroundResource(R.drawable.green_10r_bg);
//            binding.adminBtn.setTextColor(Color.parseColor("#FFFFFF"));
//            view.setElevation(5);
//            binding.employeeBtn.setElevation(1);
//            binding.employeeBtn.setTextColor(Color.parseColor("#343637"));
//            binding.employeeBtn.setBackgroundResource(R.drawable.white_10r_white_bg);
//            //  binding.loginTypeTxt.setText("Sign In as Admin");
//        });
//        binding.employeeBtn.setOnClickListener(view -> {
//            view.setBackgroundResource(R.drawable.green_10r_bg);
//            binding.employeeBtn.setTextColor(Color.parseColor("#FFFFFF"));
//            binding.adminBtn.setTextColor(Color.parseColor("#343637"));
//            view.setElevation(5);
//            binding.adminBtn.setElevation(1);
//            //  binding.loginTypeTxt.setText("Sign In as Employee");
//            binding.adminBtn.setBackgroundResource(R.drawable.white_10r_white_bg);
//        });
//        binding.createAccountBtn.setOnClickListener(view -> {
//            Navigation.findNavController(view).navigate(R.id.singUpFragment);
//        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.emailTxt.getText().toString();
                String mpin = binding.mpinTxt.getText().toString();

                if(checkValidation()){
                    if (id.equals("a@gmail.com") && mpin.equals("123456")) {
                        startActivity(new Intent(mContext, HomeActivity.class));
                        getActivity().finish();
                    } else if (id.equals("e@gmail.com") && mpin.equals("123456")) {
                        Navigation.findNavController(v).navigate(R.id.createMPINFragment);
                    }
                }
            }
        });
        binding.forgetmpindBtn.setOnClickListener(view -> {
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
        if (StringHelper.isEmpty(binding.mpinTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Valid Email!");
            return false;
        }
        if (TextUtils.isEmpty(binding.mpinTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter MPIN!");
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
        if (StringHelper.isEmpty(binding.mpinTxt.getText().toString())) {
            setTextViewDrawableColor(binding.mpinTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.mpinTxt, R.color._172B4D);
        }
        setValidations();
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.loginBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.loginBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.loginBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.loginBtn.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
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