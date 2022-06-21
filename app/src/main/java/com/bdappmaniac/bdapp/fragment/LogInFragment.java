package com.bdappmaniac.bdapp.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.LoginResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.AdminActivity;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.activity.HomeActivity;
import com.bdappmaniac.bdapp.databinding.FragmentLogInBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;

public class LogInFragment extends BaseFragment {
    FragmentLogInBinding binding;
    String userType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false);
        binding.backBtn.setOnClickListener(view -> {
            requireActivity().finish();
        });
        binding.passwordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHidePass(view);
            }
        });
        binding.emailTxt.addTextChangedListener(new TextChange(binding.emailTxt));
        binding.passwordTxt.addTextChangedListener(new TextChange(binding.passwordTxt));
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailTxt.getText().toString();
                String password = binding.passwordTxt.getText().toString();
                if (checkValidation()) {
                    loginApi(email, password);
                }
            }
        });
        binding.forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.forgotPasswordFragment);
            }
        });
        binding.btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userType = "admin";
                binding.btnAdmin.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.prime));
                binding.btnEmp.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.white));
                binding.btnAdmin.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                binding.btnEmp.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
        });
        binding.btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userType = "employee";
                binding.btnEmp.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.prime));
                binding.btnAdmin.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.white));
                binding.btnAdmin.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                binding.btnEmp.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            }
        });
        return binding.getRoot();
    }

    private void loginApi(String email, String password) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("email", toRequestBody(email));
        map.put("password", toRequestBody(password));
        MainService.userLogIn(mContext, map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((!apiResponse.getData().isJsonNull())) {
                    LoginResponse loginResponse = new Gson().fromJson(apiResponse.getData(), LoginResponse.class);
                    SharedPref.init(mContext);
                    if (loginResponse.getStatus().equals("active")) {
                        if (Objects.equals(userType, "employee")) {
                            SharedPref.putUserDetails(loginResponse);
                            showSnackBar(binding.getRoot(), apiResponse.getMessage());
                            startActivity(new Intent(mContext, HomeActivity.class));
                            getActivity().finish();
                        } else if (Objects.equals(userType, "admin")) {
                            SharedPref.putUserDetails(loginResponse);
                            showSnackBar(binding.getRoot(), apiResponse.getMessage());
                            startActivity(new Intent(mContext, AdminActivity.class));
                            getActivity().finish();
                        }
                    } else if (!apiResponse.isSuccess() && apiResponse.getData().isJsonNull()) {
                        showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    }
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                }
            }
            AppLoader.hideLoaderDialog();
        });
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
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_valid_email));
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_password));
            return false;
        }
        if (userType.isEmpty()) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_select_userType));
            return false;
        }
        return true;
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            setTextViewDrawableColor(binding.emailTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emailTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            setTextViewDrawableColor(binding.passwordTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.passwordTxt, R.color.prime);
        }
        setValidations();
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.signInBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.signInBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.signInBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.signInBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public void showHidePass(View view) {
        if (binding.passwordTxt.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
            ((ImageView) (view)).setImageResource(R.drawable.icn_show_password);
            ((ImageView) (view)).setColorFilter(ContextCompat.getColor(requireContext(), R.color._192d4d));
            binding.passwordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            binding.passwordTxt.setSelection(binding.passwordTxt.getText().length());
        } else {
            ((ImageView) (view)).setImageResource(R.drawable.icn_hide_password);
            ((ImageView) (view)).setColorFilter(ContextCompat.getColor(requireContext(), R.color._A8A8A8));
            binding.passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            binding.passwordTxt.setSelection(binding.passwordTxt.getText().length());
        }

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
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.prime));
            } else {
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color._A8A8A8));
            }
            isFieldFillUp();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}