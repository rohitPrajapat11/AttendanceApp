package com.bdappmaniac.bdapp.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.DesignationItem;
import com.bdappmaniac.bdapp.Api.sevices.MainService;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.databinding.DesignationItemBinding;
import com.bdappmaniac.bdapp.databinding.EditDesignationDialogBinding;
import com.bdappmaniac.bdapp.databinding.HolidayBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.utils.StringHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DesignationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DesignationItem> list;

    public DesignationAdapter(Context context, List<DesignationItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        DesignationItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.designation_item, group, false);
        return new DesignationAdapter.DesignationHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DesignationAdapter.DesignationHolder vHolder = (DesignationAdapter.DesignationHolder) holder;
        vHolder.binding.deseTxt.setText(list.get(position).getName());
        vHolder.binding.index.setText(String.valueOf(position + 1));
        vHolder.binding.item.setOnLongClickListener(view -> {
            String name = vHolder.binding.deseTxt.getText().toString();
            HolidayBottomSheetDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.holiday_bottom_sheet_dialog, null, false);
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            binding.deleteBtn.setOnClickListener(view13 -> {
                vHolder.removeDesignationApi(list.get(position).getId());
                dialog.dismiss();
            });
            binding.editBtn.setOnClickListener(view12 -> {
                vHolder.updateDesignationDialogBox(name);
                dialog.dismiss();
            });
            binding.cancel.setOnClickListener(view1 -> dialog.dismiss());
            return false;
        });
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        if (context != null) {
            requestBody = toRequestBodyPart(val);
        }
        return requestBody;
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DesignationHolder extends RecyclerView.ViewHolder {
        DesignationItemBinding binding;

        public DesignationHolder(DesignationItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateDesignationApi(int id, String name) {
            AppLoader.showLoaderDialog(context);
            Map<String, RequestBody> map = new HashMap<>();
            map.put("name", toRequestBody(name));
            MainService.updateDesignation(context, ((BaseActivity) context).getToken(), id, map).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        list.set(getAdapterPosition(), new DesignationItem(name, id));
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }

        public void updateDesignationDialogBox(String name) {
            EditDesignationDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.edit_designation_dialog, null, false);
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            binding.desigTxt.setText(name);
            dialog.show();
            binding.updateBtn.setOnClickListener(view -> {
                String name1 = binding.desigTxt.getText().toString();
                updateDesignationApi(list.get(getAdapterPosition()).getId(), name1);
                dialog.dismiss();
            });
            binding.cancelBtn.setOnClickListener(view -> dialog.dismiss());
        }

        @SuppressLint("NotifyDataSetChanged")
        public void removeDesignationApi(int id) {
            AppLoader.showLoaderDialog(context);
            MainService.removeDesignation(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), apiResponse.getMessage());
                        list.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(apiResponse.getMessage());
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }
    }
}
