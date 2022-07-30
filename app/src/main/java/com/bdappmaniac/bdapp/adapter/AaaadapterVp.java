package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ViewpagerAdapterBinding;

public class AaaadapterVp extends RecyclerView.Adapter<AaaadapterVp.ViewHolder> {
    private int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    private Context ctx;

    public AaaadapterVp(Context ctx) {
        this.ctx = ctx;
    }

    public ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        ViewpagerAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.viewpager_adapter, group, false);
        return new AaaadapterVp.ViewHolder(binding);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (ViewHolder) getViewHolder(LayoutInflater.from(ctx), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.images.setImageResource(images[position]);

    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewpagerAdapterBinding binding;

        public ViewHolder(@NonNull ViewpagerAdapterBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
