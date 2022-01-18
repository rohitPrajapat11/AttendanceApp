package com.bdappmaniac.bdapp.Fragment;

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

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.Utils.StringHelper;
import com.bdappmaniac.bdapp.databinding.FragmentNewPasswordBinding;

public class NewPasswordFragment extends BaseFragment {
    FragmentNewPasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_password, container, false);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.logInFragment);
            }
        });
        binding.newPasswordTxt.addTextChangedListener(new NewPasswordFragment.TextChange(binding.newPasswordTxt));
        binding.passwordTxt.addTextChangedListener(new NewPasswordFragment.TextChange(binding.passwordTxt));

        binding.confirmBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                showSnackBar(view, "Password Reset");
            }
        });
        return binding.getRoot();
    }

    private boolean isAllFieldFillUp() {
        if (StringHelper.isEmpty(binding.newPasswordTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.newPasswordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Password!");
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Confirm Password!");
            return false;
        }
        if (!binding.newPasswordTxt.getText().toString().equals(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Password Mismatch!");
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
            if (binding.passwordTxt.getText().toString().equals(binding.newPasswordTxt.getText().toString())) {
                binding.passwordValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.primary_color));
            } else {
                binding.passwordValidation.setColorFilter(ContextCompat.getColor(mContext, R.color._A8A8A8));
            }
            isFieldFillUp();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.newPasswordTxt.getText().toString())) {
            setTextViewDrawableColor(binding.newPasswordTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.newPasswordTxt, R.color._172B4D);
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
            binding.confirmBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.confirmBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.confirmBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.confirmBtn.setTextColor(ContextCompat.getColor(mContext, R.color._172B4D));
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