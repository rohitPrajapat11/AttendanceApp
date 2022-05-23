package com.bdappmaniac.bdapp.employee.fragment;

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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentForgotPasswordBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class ForgotPasswordFragment extends BaseFragment {
    FragmentForgotPasswordBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.logInFragment);
            }
        });
//        binding.passwordTxtBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.logInFragment);
//            }
//        });
        binding.sendMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
//                    Navigation.findNavController(v).navigate(R.id.newPasswordFragment);
                    sendMailApi(binding.emailEdt.getText().toString());
                }
            }
        });
        binding.emailEdt.addTextChangedListener(new TextChange(binding.emailEdt));
        return binding.getRoot();
    }

    private boolean isAllFieldFillUp() {
        if (StringHelper.isEmpty(binding.emailEdt.getText().toString())) {
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailEdt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.emailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_email));
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailEdt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_valid_email));
            return false;
        }
        return true;
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.emailEdt.getText().toString())) {
            setTextViewDrawableColor(binding.emailEdt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emailEdt, R.color._172B4D);
        }
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.sendMailBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.sendMailBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.sendMailBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.sendMailBtn.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
        }
        isFieldFillUp();
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    private void sendMailApi(String email) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("email", toRequestBody(email));
        MainService.sendMail(mContext, map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.isSuccess())) {
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
                    Bundle bundle = new Bundle();
                    bundle.putString(EMAIL, binding.emailEdt.getText().toString());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.newPasswordFragment, bundle);
                } else {
                    binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.bRed));
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
            if (ValidationUtils.validateEmail(binding.emailEdt.getText().toString())) {
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.primary_color));
            } else {
                binding.emailValidation.setColorFilter(ContextCompat.getColor(mContext, R.color._A8A8A8));
            }
            setValidations();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}