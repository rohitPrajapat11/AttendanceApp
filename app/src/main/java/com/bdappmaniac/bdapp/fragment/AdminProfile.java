package com.bdappmaniac.bdapp.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.Api.response.LoginResponse;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.AdminActivity;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.FragmentAdminProfileBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.utils.DateUtils;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AdminProfile extends BaseFragment {
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    FragmentAdminProfileBinding binding;
    String imgPath;
    File file = null;
    MultipartBody.Part fileToUpload;
    String updateDobDate, updateInCorpDate;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_profile, container, false);
        SharedPref.init(mContext);
        setResponseData(SharedPref.getUserDetails());
        binding.nameTxt.addTextChangedListener(new TextChange(binding.nameTxt));
        binding.emailTxt.addTextChangedListener(new TextChange(binding.emailTxt));
        binding.phoneTxt.addTextChangedListener(new TextChange(binding.phoneTxt));
        binding.dobTxt.addTextChangedListener(new TextChange(binding.dobTxt));
        binding.addressTxt.addTextChangedListener(new TextChange(binding.addressTxt));
        binding.incDateTxt.addTextChangedListener(new TextChange(binding.incDateTxt));
        binding.gstinTxt.addTextChangedListener(new TextChange(binding.gstinTxt));
        binding.gstidTxt.addTextChangedListener(new TextChange(binding.gstidTxt));
        binding.otherNumTxt.addTextChangedListener(new TextChange(binding.otherNumTxt));

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        binding.dobTxt.setOnClickListener(view -> {
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
                            updateDobDate = d;
                            binding.dobTxt.setText(DateUtils.getFormattedTime(d, DateUtils.appDateFormat, DateUtils.appDateFormatTos));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChange(true);
            }
        });
        binding.saveBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                if (imgPath != null) {
                    File file = new File(imgPath);
                    File img = Compressor.getDefault(mContext).compressToFile(file);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), img);
                    fileToUpload = MultipartBody.Part.createFormData("profile", file.getName(), requestBody);
                }
                Map<String, RequestBody> map = new HashMap<>();
                map.put("employee_name", toRequestBody(binding.nameTxt.getText().toString()));
                map.put("email", toRequestBody(binding.emailTxt.getText().toString()));
                map.put("emp_mobile_no", toRequestBody(binding.phoneTxt.getText().toString()));
                map.put("designation", toRequestBody(binding.designationTxt.getText().toString()));
                map.put("dob", toRequestBody(updateDobDate));
                map.put("employee_address", toRequestBody(binding.addressTxt.getText().toString()));
                map.put("GSTID", toRequestBody(binding.gstidTxt.getText().toString()));
                map.put("GSTIN", toRequestBody(binding.gstinTxt.getText().toString()));
                map.put("incorDate", toRequestBody(updateInCorpDate));
                map.put("otherNumber", toRequestBody(binding.otherNumTxt.getText().toString()));
                updateEmployeeProfileApi(map, fileToUpload);
                onEditChange(false);
            }
        });
        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditChange(false);
            }
        });
        binding.designationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.admin_designation_dialogbox);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                TextView adminTxt = dialog.findViewById(R.id.adminTxt);
                TextView admTxt = dialog.findViewById(R.id.adminText);
                String getAdmin = adminTxt.getText().toString();
                String getAdm = admTxt.getText().toString();

                adminTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = getAdmin;
                        binding.designationTxt.setText(s);
                        dialog.dismiss();
                    }
                });
                admTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = getAdm;
                        binding.designationTxt.setText(s);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        binding.incDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, R.style.DatePicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String d = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                updateInCorpDate = d;
                                binding.incDateTxt.setText(DateUtils.getFormattedTime(d, DateUtils.appDateFormat, DateUtils.appDateFormatTos));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.getWindow().setGravity(Gravity.CENTER);
                datePickerDialog.show();
            }
        });
        binding.cameraBtn.setOnClickListener(view -> selectImage());
        return binding.getRoot();
    }

    private void selectImage() {
        final CharSequence[] options = {mContext.getString(R.string.take_photo), mContext.getString(R.string.chose_from_gallery), mContext.getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(mContext.getString(R.string.choose_option));
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals(mContext.getString(R.string.take_photo))) {
                if (checkPermission()) {
                    openCamera(1);
                } else {
                    requestCameraPermission(1);
                }
                dialog.dismiss();
            } else if (options[item].equals(mContext.getString(R.string.chose_from_gallery))) {
                dialog.dismiss();
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
            } else if (options[item].equals(mContext.getString(R.string.cancel))) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void openCamera(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            file = createImageFile();
        } catch (IOException ex) {
            Toast.makeText(requireActivity(), getResources().getString(R.string.start_camera_failed), Toast.LENGTH_LONG).show();
        }
        if (file != null) {
            imgPath = file.getAbsolutePath();
            Uri fileUri = FileProvider.getUriForFile(requireContext(), requireActivity().getPackageName() + ".BdApp.fileProvider", file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    public File createImageFile() throws IOException {
        String imageFileName = "BdApp_Image-" + System.currentTimeMillis() + "_";
        File storageDir = requireActivity().getFilesDir();
        return File.createTempFile(imageFileName, ".png", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CAMERA) {
            Glide.with(mContext).load(imgPath).placeholder(R.drawable.user).into(binding.profile);
            binding.profile.setPadding(0, 0, 0, 0);
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            if (data != null) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    try {
                        imgPath = getRealPathFromURI(selectedImage);
                        Glide.with(requireContext()).load(selectedImage).placeholder(R.drawable.user).into(binding.profile);
                        binding.profile.setPadding(0, 0, 0, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission(int requestCode) {
        String[] permissions = {Manifest.permission.CAMERA};
        String rationale = mContext.getString(R.string.provide_permission);
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle(mContext.getString(R.string.permissions))
                .setSettingsDialogTitle(mContext.getString(R.string.camera_permission));
        Permissions.check(getActivity(), permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
                openCamera(requestCode);
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                Snackbar.make(binding.getRoot(), mContext.getString(R.string.permission_denied), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        if (StringHelper.isEmpty(binding.dobTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.gstidTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.gstinTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.incDateTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.otherNumTxt.getText().toString())) {
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
            showSnackBar(binding.getRoot(), mContext.getString(R.string.phone_number));
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
        if (TextUtils.isEmpty(binding.dobTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_dob));
            return false;
        }
        if (TextUtils.isEmpty(binding.addressTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_address));
            return false;
        }
        if (TextUtils.isEmpty(binding.gstinTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_address));
            return false;
        }
        if (TextUtils.isEmpty(binding.gstidTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_address));
            return false;
        }
        if (TextUtils.isEmpty(binding.incDateTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_address));
            return false;
        }
        if (TextUtils.isEmpty(binding.otherNumTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), mContext.getString(R.string.enter_address));
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
        if (StringHelper.isEmpty(binding.phoneTxt.getText().toString())) {
            setTextViewDrawableColor(binding.phoneTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.phoneTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.designationTxt.getText().toString())) {
            setTextViewDrawableColor(binding.designationTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.designationTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.dobTxt.getText().toString())) {
            setTextViewDrawableColor(binding.dobTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.dobTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.addressTxt.getText().toString())) {
            setTextViewDrawableColor(binding.addressTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.addressTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.gstidTxt.getText().toString())) {
            setTextViewDrawableColor(binding.gstidTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.gstidTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.gstinTxt.getText().toString())) {
            setTextViewDrawableColor(binding.gstinTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.gstinTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.incDateTxt.getText().toString())) {
            setTextViewDrawableColor(binding.incDateTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.incDateTxt, R.color.prime);
        }
        if (StringHelper.isEmpty(binding.otherNumTxt.getText().toString())) {
            setTextViewDrawableColor(binding.otherNumTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.otherNumTxt, R.color.prime);
        }
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.saveBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.saveBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.saveBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.saveBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
        isFieldFillUp();
    }

    void onEditChange(boolean check) {
        if (check) {
            binding.editBtn.setVisibility(View.GONE);
            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.cancelBtn.setVisibility(View.VISIBLE);
            binding.cameraBtn.setVisibility(View.VISIBLE);
        } else {
            binding.saveBtn.setVisibility(View.GONE);
            binding.cancelBtn.setVisibility(View.GONE);
            binding.cameraBtn.setVisibility(View.GONE);
            binding.editBtn.setVisibility(View.VISIBLE);
        }
        binding.nameTxt.setEnabled(check);
        binding.emailTxt.setEnabled(check);
        binding.phoneTxt.setEnabled(check);
        binding.dobTxt.setEnabled(check);
        binding.addressTxt.setEnabled(check);
        binding.designationTxt.setEnabled(check);
        binding.otherNumTxt.setEnabled(check);
        binding.gstinTxt.setEnabled(check);
        binding.gstidTxt.setEnabled(check);
        binding.incDateTxt.setEnabled(check);
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    void textProfile() {
        Bitmap bitmap = TextToBitmap.textToBitmap(binding.nameTxt.getText().toString(), mContext, 10, R.color.black);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        binding.profile.setImageDrawable(d);
    }

    public void setResponseData(LoginResponse updateResponse) {
        updateInCorpDate = updateResponse.getIncorDate();
        updateDobDate = updateResponse.getDob().toString();
        binding.nameTxt.setText(updateResponse.getEmployeeName());
        binding.emailTxt.setText(updateResponse.getEmail());
        binding.phoneTxt.setText(String.valueOf(updateResponse.getEmpMobileNo()));
        binding.designationTxt.setText(updateResponse.getDesignation());
        binding.addressTxt.setText(updateResponse.getEmployeeAddress());
        binding.incDateTxt.setText(DateUtils.getFormattedTime(String.valueOf(updateResponse.getIncorDate()), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        binding.gstidTxt.setText(updateResponse.getGSTID());
        binding.gstinTxt.setText(updateResponse.getGSTIN());
        binding.otherNumTxt.setText(String.valueOf(updateResponse.getOtherNumber()));
        if (updateResponse.getProfile() != null) {
            Glide.with(mContext).load(updateResponse.getProfile()).placeholder(R.drawable.user).into(binding.profile);
        }
        if (updateResponse.getDob() == null) {
            binding.dobTxt.setText("");
        } else {
            binding.dobTxt.setText(DateUtils.getFormattedTime(String.valueOf(updateResponse.getDob()), DateUtils.appDateFormat, DateUtils.appDateFormatTo));
        }
    }

    public void updateEmployeeProfileApi(Map<String, RequestBody> map, MultipartBody.Part fileToUpload) {
        AppLoader.showLoaderDialog(mContext);
        MainService.updateEmployeeProfile(mContext, getToken(), map, fileToUpload).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    LoginResponse updateResponse = new Gson().fromJson(apiResponse.getData(), LoginResponse.class);
                    updateResponse.setAccessToken(getToken());
                    SharedPref.putUserDetails(updateResponse);
                    //setResponseData(updateResponse);
                    ((AdminActivity) mContext).updateProfile();
                    showSnackBar(binding.getRoot(), apiResponse.getMessage());
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
            setValidations();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.prime);
    }
}