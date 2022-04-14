package com.bdappmaniac.bdapp.admin.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.DesignationItem;
import com.bdappmaniac.bdapp.Api.response.EmpRegisterResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.admin.adapter.RegistrationDesignationAdapter;
import com.bdappmaniac.bdapp.databinding.BottomDialogRegisterSuccessBinding;
import com.bdappmaniac.bdapp.databinding.FragmentRegisterEmpolyeeBinding;
import com.bdappmaniac.bdapp.databinding.RegisterDesignationDialogboxBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class RegisterEmployeeFragment extends BaseFragment {
    public Dialog dialog;
    FragmentRegisterEmpolyeeBinding binding;
    BottomDialogRegisterSuccessBinding dBinding;
    RegistrationDesignationAdapter adapter;
    String joiningDate;
    List<DesignationItem> list = new ArrayList<>();
    private int mYear, mMonth, mDay, mHour, mMinute;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_empolyee, container, false);
        StatusBarUtils.statusBarColor(requireActivity(), R.color.transparent);
        binding.emailTxt.addTextChangedListener(new RegisterEmployeeFragment.TextChange(binding.emailTxt));
        binding.passwordTxt.addTextChangedListener(new RegisterEmployeeFragment.TextChange(binding.passwordTxt));
        binding.sighUpBtn.setOnClickListener(v -> {
            if (checkValidation()) {
                Map<String, RequestBody> map = new HashMap<>();
                map.put("employee_name", toRequestBody(binding.nameTxt.getText().toString()));
                map.put("emp_mobile_no", toRequestBody(binding.phoneTxt.getText().toString()));
                map.put("email", toRequestBody(binding.emailTxt.getText().toString()));
                map.put("password", toRequestBody(binding.passwordTxt.getText().toString()));
                map.put("password_confirmation", toRequestBody(binding.confirmPasswordTxt.getText().toString()));
                map.put("designation", toRequestBody(binding.designationTxt.getText().toString()));
                map.put("joining_date", toRequestBody(joiningDate));
                registerEmployee(map);
            }
        });
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.designationTxt.setOnClickListener(v -> {
            designationDialog();
        });
        binding.joiningTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                binding.joiningTxt.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                                joiningDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                datePickerDialog.show();
            }
        });
        return binding.getRoot();
    }

    public void registerEmployee(Map<String, RequestBody> map) {
        AppLoader.showLoaderDialog(mContext);
        MainService.employeeRegistration(mContext, map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    EmpRegisterResponse empRegisterResponse = new Gson().fromJson(apiResponse.getData(), EmpRegisterResponse.class);
                    showSnackBar(binding.getRoot(), "New Employee has been Created");
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.employeeListFragment);
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void showBottomSheetDialog() {
        dBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.bottom_dialog_register_success, null, false);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(dBinding.getRoot());
        ((View) dBinding.getRoot().getParent()).setBackgroundColor(requireActivity().getResources().getColor(R.color.transparent));
        dBinding.sendBtn.setOnClickListener(v -> {
            //    sharePrint();
        });
        bottomSheetDialog.show();
    }

    private void sharePrint() {
        dBinding.sendLayout.setDrawingCacheEnabled(true);
        dBinding.sendLayout.buildDrawingCache();
        Bitmap bm = dBinding.sendLayout.getDrawingCache();
        String imgBitmapPath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm, "Title", null);
        Uri imgBitmapUri = Uri.parse(imgBitmapPath);
        String shareText = "Hii.........";
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imgBitmapUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, mContext.getString(R.string.share_details)));
    }

    private void designationDialog() {
        RegisterDesignationDialogboxBinding designationBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.register_designation_dialogbox, null, false);
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(designationBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        designationBinding.adTxt.setOnClickListener(v -> {
//            binding.designationTxt.setText(mContext.getString(R.string.adroid_developre));
//            dialog.dismiss();
//        });
        designationBinding.recycleView.setHasFixedSize(false);
        designationBinding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RegistrationDesignationAdapter(mContext, list, this);
        designationBinding.recycleView.setAdapter(adapter);
        allDesignationApi();
        dialog.show();
    }

    private boolean isAllFieldFillUp() {
        if (StringHelper.isEmpty(binding.nameTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.phoneTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.designationTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.confirmPasswordTxt.getText().toString())) {
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
        if (TextUtils.isEmpty(binding.phoneTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_your_mobile_number));
            return false;
        }
        if (binding.phoneTxt.getText().length() != 10) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_10_digit_number));
            return false;
        }
        if (TextUtils.isEmpty(binding.designationTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_designation));
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.please_enter_password));
            return false;
        }
        if (TextUtils.isEmpty(binding.confirmPasswordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.confirm_password));
            return false;
        }
        return true;
    }

    private void isFieldFillUp() {
        if (StringHelper.isEmpty(binding.nameTxt.getText().toString())) {
            setTextViewDrawableColor(binding.nameTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.nameTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.emailTxt.getText().toString())) {
            setTextViewDrawableColor(binding.emailTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emailTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.phoneTxt.getText().toString())) {
            setTextViewDrawableColor(binding.phoneTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.phoneTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.designationTxt.getText().toString())) {
            setTextViewDrawableColor(binding.designationTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.designationTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            setTextViewDrawableColor(binding.passwordTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.passwordTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.confirmPasswordTxt.getText().toString())) {
            setTextViewDrawableColor(binding.confirmPasswordTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.confirmPasswordTxt, R.color._172B4D);
        }
        setValidations();
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.sighUpBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.sighUpBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.sighUpBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.sighUpBtn.setTextColor(ContextCompat.getColor(mContext, R.color._172B4D));
        }
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    private void allDesignationApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.allDesignation(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<DesignationItem>>() {
                    }.getType();
                    List<DesignationItem> List = new Gson().fromJson(apiResponse.getData(), collectionType);
                    list.clear();
                    list.addAll(List);
                    adapter.notifyDataSetChanged();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    public void setDes(String des) {
        binding.designationTxt.setText(des);
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
}