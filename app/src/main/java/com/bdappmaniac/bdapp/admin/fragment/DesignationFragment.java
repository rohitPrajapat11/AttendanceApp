package com.bdappmaniac.bdapp.admin.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bdappmaniac.bdapp.Api.response.DesignationItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.admin.adapter.DesignationAdapter;
import com.bdappmaniac.bdapp.databinding.AddDesignationDialogBinding;
import com.bdappmaniac.bdapp.databinding.FragmentDesignationBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class DesignationFragment extends BaseFragment {
    FragmentDesignationBinding binding;
    DesignationAdapter adapter;
    List<DesignationItem> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_designation, container, false);
        binding.recycleView.setHasFixedSize(false);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DesignationAdapter(mContext, list);
        binding.recycleView.setAdapter(adapter);
        allDesignationApi();
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDesignationDialogBox();
            }
        });
        return binding.getRoot();
    }

    private void addDesignationDialogBox() {
        AddDesignationDialogBinding abinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.add_designation_dialog, null, false);
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(abinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        abinding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = abinding.desigTxt.getText().toString();
                if(abinding.desigTxt.getText().toString().isEmpty()) {
                   showSnackBar(binding.getRoot(), "The field is empty");
                }else {
                    addDesignationApi(name);
                    dialog.dismiss();
                }
            }
        });
        abinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void addDesignationApi(String name) {
        AppLoader.showLoaderDialog(mContext);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("name", toRequestBody(name));
        MainService.addDesignation(mContext, getToken(), map).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    showSnackBar(binding.getRoot(), "Designation Added Successfully");
                    allDesignationApi();
                } else {
                    ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
                }
            }
            AppLoader.hideLoaderDialog();
        });
    }

    private void allDesignationApi() {
        AppLoader.showLoaderDialog(mContext);
        MainService.allDesignation(mContext, getToken()).observe((LifecycleOwner) mContext, apiResponse -> {
            if (apiResponse == null) {
                ((BaseActivity) mContext).showSnackBar(binding.getRoot(), mContext.getString(R.string.something_went_wrong));
            } else {
                if ((apiResponse.getData() != null)) {
                    Type collectionType = new TypeToken<List<DesignationItem>>() {}.getType();
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
}