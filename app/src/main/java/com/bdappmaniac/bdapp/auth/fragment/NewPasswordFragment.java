package com.bdappmaniac.bdapp.auth.fragment;

import static com.bdappmaniac.bdapp.activity.BaseActivity.EMAIL;

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
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentNewPasswordBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class NewPasswordFragment extends BaseFragment {
    FragmentNewPasswordBinding binding;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_password, container, false);
        if (getArguments() != null) {
            email = this.getArguments().getString(EMAIL, "");
        }
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.logInFragment);
            }
        });
        binding.newPasswordTxt.addTextChangedListener(new TextChange(binding.newPasswordTxt));
        binding.passwordTxt.addTextChangedListener(new TextChange(binding.passwordTxt));
        binding.confirmBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                String password = binding.newPasswordTxt.getText().toString();
                String password_confirmation = binding.passwordTxt.getText().toString();
                String token = binding.tokenTxt.getText().toString();
                resetPasswordApi(password, password_confirmation, token);
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
        if (StringHelper.isEmpty(binding.tokenTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.newPasswordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_password));
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_confirm_password));
            return false;
        }
        if (!binding.newPasswordTxt.getText().toString().equals(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.password_mismatch));
            return false;
        }
        if (TextUtils.isEmpty(binding.tokenTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_correct_otp));
            return false;
        }
        return true;
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
        if (StringHelper.isEmpty(binding.tokenTxt.getText().toString())) {
            setTextViewDrawableColor(binding.tokenTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.tokenTxt, R.color._172B4D);
        }
        setValidations();
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.confirmBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.confirmBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.confirmBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.confirmBtn.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
        }
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    private void resetPasswordApi(String password, String password_confirmation, String token) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("email", toRequestBody(email));
        map.put("password", toRequestBody(password));
        map.put("password_confirmation", toRequestBody(password_confirmation));
        map.put("token", toRequestBody(token));
        MainService.resetPassword(mContext, map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
//                    if(binding.newPasswordTxt.getText().toString() != binding.passwordTxt.getText().toString()) {
//                        showSnackBar(binding.getRoot(), "Passwords Mismatch");
//                    }
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(),apiResponse.getMessage());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.logInFragment);

                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
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
}