package com.bdappmaniac.bdapp.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.bdappmaniac.bdapp.utils.ValidationUtils;
import com.bdappmaniac.bdapp.databinding.FragmentSingUpBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class SingUpFragment extends BaseFragment {
    FragmentSingUpBinding binding;
    String imgPath;
    File file = null;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sing_up, container, false);
        StatusBarUtils.statusBarColor(requireActivity(), R.color.transparent);
        binding.nameTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.emailTxt));
        binding.emailTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.emailTxt));
        binding.passwordTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.passwordTxt));
        binding.phoneTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.emailTxt));
        binding.emPhoneTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.passwordTxt));
        binding.confirmPasswordTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.emailTxt));
        binding.dobTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.passwordTxt));
        binding.addressTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.emailTxt));
        binding.pinCodeTxt.addTextChangedListener(new SingUpFragment.TextChange(binding.passwordTxt));

        binding.backToLogin.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.logInFragment);
        });
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });
        binding.sighUpBtn.setOnClickListener(view -> {
            if (checkValidation()) {
                Toast.makeText(mContext, "All Done", Toast.LENGTH_SHORT).show();
            }
        });
        binding.cameraBtn.setOnClickListener(view -> selectImage());
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
                            binding.dobTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            datePickerDialog.getWindow().setGravity(Gravity.CENTER);
            datePickerDialog.show();
        });

        return binding.getRoot();
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Option");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo")) {
                if (checkPermission()) {
                    openCamera(1);
                } else {
                    requestCameraPermission(1);
                }
                dialog.dismiss();
            } else if (options[item].equals("Choose From Gallery")) {
                dialog.dismiss();
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
            } else if (options[item].equals("Cancel")) {
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
        String rationale = "Please provide camera permission so that you can ...";
        com.nabinbhandari.android.permissions.Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("Permissions")
                .setSettingsDialogTitle("Camera Permission");
        Permissions.check(getActivity(), permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
                openCamera(requestCode);
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                Snackbar.make(binding.getRoot(), "Permission Denied", Snackbar.LENGTH_SHORT).show();
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
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.emPhoneTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.phoneTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.passwordTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.dobTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.confirmPasswordTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.addressTxt.getText().toString())) {
            return false;
        }
        if (StringHelper.isEmpty(binding.pinCodeTxt.getText().toString())) {
            return false;
        }
        return true;
    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(binding.nameTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Name!");
            return false;
        }
        if (TextUtils.isEmpty(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Email Name!");
            return false;
        }
        if (!ValidationUtils.validateEmail(binding.emailTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Valid Email!");
            return false;
        }
        if (TextUtils.isEmpty(binding.phoneTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Phone Number!");
            return false;
        }
        if (binding.phoneTxt.getText().length() != 10) {
            showSnackBar(binding.getRoot(), "Please Enter 10 Digit Number");
            return false;
        }
        if (TextUtils.isEmpty(binding.emPhoneTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Emergency Number!");
            return false;
        }
        if (binding.emPhoneTxt.getText().length() != 10) {
            showSnackBar(binding.getRoot(), "Please Enter 10 Digit Number");
            return false;
        }
        if (TextUtils.isEmpty(binding.dobTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Date Of Birth!");
            return false;
        }
        if (TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Password!");
            return false;
        }
        if (TextUtils.isEmpty(binding.confirmPasswordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Password To Confirm!");
            return false;
        }
        if (!binding.passwordTxt.getText().toString().equals(binding.passwordTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Password Mismatch!");
            return false;
        }
        if (TextUtils.isEmpty(binding.addressTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter Address!");
            return false;
        }
        if (TextUtils.isEmpty(binding.pinCodeTxt.getText().toString())) {
            showSnackBar(binding.getRoot(), "Please Enter PinCode!");
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
            if (!TextUtils.isEmpty(binding.passwordTxt.getText().toString())) {
                if (binding.passwordTxt.getText().toString().equals(binding.confirmPasswordTxt.getText().toString())) {
                    binding.confirmPsValidation.setColorFilter(ContextCompat.getColor(mContext, R.color.primary_color));
                } else {
                    binding.confirmPsValidation.setColorFilter(ContextCompat.getColor(mContext, R.color._A8A8A8));
                }
            }
            setValidations();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
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
        if (StringHelper.isEmpty(binding.emPhoneTxt.getText().toString())) {
            setTextViewDrawableColor(binding.emPhoneTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.emPhoneTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.dobTxt.getText().toString())) {
            setTextViewDrawableColor(binding.dobTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.dobTxt, R.color._172B4D);
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
        if (StringHelper.isEmpty(binding.addressTxt.getText().toString())) {
            setTextViewDrawableColor(binding.addressTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.addressTxt, R.color._172B4D);
        }
        if (StringHelper.isEmpty(binding.pinCodeTxt.getText().toString())) {
            setTextViewDrawableColor(binding.pinCodeTxt, R.color._A8A8A8);
        } else {
            setTextViewDrawableColor(binding.pinCodeTxt, R.color._172B4D);
        }
    }

    private void setValidations() {
        if (isAllFieldFillUp()) {
            binding.sighUpBtn.setBackgroundResource(R.drawable.green_10r_bg);
            binding.sighUpBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            binding.sighUpBtn.setBackgroundResource(R.drawable.light_green_15r_bg);
            binding.sighUpBtn.setTextColor(ContextCompat.getColor(mContext, R.color._172B4D));
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
}