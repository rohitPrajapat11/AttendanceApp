package com.bdappmaniac.bdapp.admin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.Api.response.DesignationItem;
import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.admin.fragment.RegisterEmployeeFragment;
import com.bdappmaniac.bdapp.databinding.RegisterDesignationItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDesignationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DesignationItem> list = new ArrayList<>();
    RegistrationDesignationAdapter adapter;
    RegisterEmployeeFragment registerEmployeeFragment;

    public RegistrationDesignationAdapter(Context context, List<DesignationItem> list, RegisterEmployeeFragment registerEmployeeFragment) {
        this.context = context;
        this.list = list;
        this.registerEmployeeFragment = registerEmployeeFragment;
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
        RegisterDesignationItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.register_designation_item, group, false);
        return new RegistrationDesignationAdapter.RegisterDesignationHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RegistrationDesignationAdapter.RegisterDesignationHolder vHolder = (RegistrationDesignationAdapter.RegisterDesignationHolder) holder;
        vHolder.binding.deseTxt.setText(list.get(position).getName());
        vHolder.binding.item.setOnClickListener(view -> {
            registerEmployeeFragment.setDes(list.get(position).getName());
            registerEmployeeFragment.dialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RegisterDesignationHolder extends RecyclerView.ViewHolder {
        RegisterDesignationItemBinding binding;

        public RegisterDesignationHolder(RegisterDesignationItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
