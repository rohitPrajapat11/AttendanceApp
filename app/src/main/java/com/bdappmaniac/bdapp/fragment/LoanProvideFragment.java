package com.bdappmaniac.bdapp.fragment;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentLoanProvideBinding;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;

import java.util.Calendar;

public class LoanProvideFragment extends BaseFragment {
    FragmentLoanProvideBinding binding;
    String updateInCorpDate;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loan_provide, container, false);
        binding.nameTxt.addTextChangedListener(new TextChange(binding.nameTxt));
        binding.emailTxt.addTextChangedListener(new TextChange(binding.emailTxt));
        binding.loanAmountTxt.addTextChangedListener(new TextChange(binding.loanAmountTxt));
        binding.loanInstalmentTxt.addTextChangedListener(new TextChange(binding.loanInstalmentTxt));
        binding.loanIssueDate.addTextChangedListener(new TextChange(binding.loanIssueDate));
        binding.loanReasonTxt.addTextChangedListener(new TextChange(binding.loanReasonTxt));

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        binding.loanIssueDate.setOnClickListener(view -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String d = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            updateInCorpDate = d;
                            binding.loanIssueDate.setText(DateUtils.getFormattedTime(d, DateUtils.appDateFormat, DateUtils.appDateFormatTos));
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });
        binding.submitBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                showToast("Loan Provided Success");
            }
        });
        return binding.getRoot();
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    private boolean isAllFieldFillUp() {
        if (StringHelper.isEmpty(binding.nameTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.loanAmountTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.loanInstalmentTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.loanIssueDate.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.loanReasonTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.nameTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_name));
            return false;
        }
        if (TextUtils.isEmpty(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_email));
            return false;
        }
        if (TextUtils.isEmpty(binding.loanAmountTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_loan_amount));
            return false;
        }
        if (TextUtils.isEmpty(binding.loanInstalmentTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_loan_installment));
            return false;
        }
        if (TextUtils.isEmpty(binding.loanIssueDate.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_loan_date));
            return false;
        }
        if (TextUtils.isEmpty(binding.loanReasonTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_loan_reason));
            return false;
        }
        return true;
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.nameTxt.getText().toString())) {
            setTextViewDrawableColor(binding.nameTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.nameTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            setTextViewDrawableColor(binding.emailTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emailTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.loanAmountTxt.getText().toString())) {
            setTextViewDrawableColor(binding.loanAmountTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.loanAmountTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.loanInstalmentTxt.getText().toString())) {
            setTextViewDrawableColor(binding.loanInstalmentTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.loanInstalmentTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.loanIssueDate.getText().toString())) {
            setTextViewDrawableColor(binding.loanIssueDate, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.loanIssueDate, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.loanReasonTxt.getText().toString())) {
            setTextViewDrawableColor(binding.loanReasonTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.loanReasonTxt, R.color.prime);
        }
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.submitBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.submitBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.submitBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.submitBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
        isFieldFillUp();
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
            setValidations();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}