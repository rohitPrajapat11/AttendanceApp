package com.bdappmaniac.bdapp.admin.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.bdappmaniac.bdapp.databinding.HolidayBottomSheetDialogBinding;
import com.bdappmaniac.bdapp.helper.AppLoader;

import java.util.ArrayList;
import java.util.List;

public class DesignationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DesignationItem> list = new ArrayList<>();

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
        vHolder.binding.index.setText(String.valueOf(position+1));
        vHolder.binding.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
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
                binding.editBtn.setVisibility(View.GONE);
                binding.cancel.setOnClickListener(view1 -> dialog.dismiss());
                return false;
            }
        });
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
        public void removeDesignationApi(int id) {
            AppLoader.showLoaderDialog(context);
            MainService.removeDesignation(context, ((BaseActivity) context).getToken(), id).observe((LifecycleOwner) context, apiResponse -> {
                if (apiResponse == null) {
                    ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                } else {
                    if ((apiResponse.getData() != null)) {
                        ((BaseActivity) context).showSnackBar(binding.getRoot(), "Designation Removed Successfully");
                        list.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    } else {
                        ((BaseActivity) context).showToast(context.getString(R.string.something_went_wrong));
                    }
                }
                AppLoader.hideLoaderDialog();
            });
        }
    }
}
