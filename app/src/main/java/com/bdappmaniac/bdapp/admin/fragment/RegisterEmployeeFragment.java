package com.bdappmaniac.bdapp.admin.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.BottomDialogRegisterSuccessBinding;
import com.bdappmaniac.bdapp.databinding.DesignationDialogboxBinding;
import com.bdappmaniac.bdapp.databinding.FragmentRegisterEmpolyeeBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class RegisterEmployeeFragment extends BaseFragment {
    FragmentRegisterEmpolyeeBinding binding;
    BottomDialogRegisterSuccessBinding dBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                if (getView() != null)
//                    Navigation.findNavController(getView()).navigateUp();
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_empolyee, container, false);
        binding.sighUpBtn.setOnClickListener(v -> {
            showBottomSheetDialog();
        });
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        binding.designationTxt.setOnClickListener(v -> {
            designationDialog();
        });

        return binding.getRoot();
    }

    private void showBottomSheetDialog() {
        dBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.bottom_dialog_register_success, null, false);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(dBinding.getRoot());
        ((View) dBinding.getRoot().getParent()).setBackgroundColor(requireActivity().getResources().getColor(R.color.transparent));
        dBinding.sendBtn.setOnClickListener(v -> {
            sharePrint();
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
        startActivity(Intent.createChooser(shareIntent, "Share Details using"));
    }

    private void designationDialog() {
        DesignationDialogboxBinding designationBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.designation_dialogbox, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(designationBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        designationBinding.adTxt.setOnClickListener(v -> {
            binding.designationTxt.setText("Android Developer");
            dialog.dismiss();
        });
        designationBinding.iosTxt.setOnClickListener(v -> {
            binding.designationTxt.setText("IOS Developer");
            dialog.dismiss();
        });
        designationBinding.wdTxt.setOnClickListener(v -> {
            binding.designationTxt.setText("Web Developer");
            dialog.dismiss();
        });
        designationBinding.pmTxt.setOnClickListener(v -> {
            binding.designationTxt.setText("Project Manager");
            dialog.dismiss();
        });
        designationBinding.oTxt.setOnClickListener(v -> {
            binding.designationTxt.setText("Other");
            dialog.dismiss();
        });
        dialog.show();
    }
}