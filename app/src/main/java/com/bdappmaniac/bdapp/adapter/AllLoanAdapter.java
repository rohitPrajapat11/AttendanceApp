package com.bdappmaniac.bdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.LoanDetailsItemBinding;
import com.bdappmaniac.bdapp.model.AllLoanModel;

import java.util.List;

public class AllLoanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<AllLoanModel> loanList;

    public AllLoanAdapter(Context context, List<AllLoanModel> loanList) {
        this.context = context;
        this.loanList = loanList;
    }

    private RecyclerView.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup group) {
        LoanDetailsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.loan_details_item, group, false);
        return new AllLoanAdapter.AllLoanHolder(binding);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AllLoanAdapter.AllLoanHolder vHolder = (AllLoanAdapter.AllLoanHolder) holder;
        vHolder.binding.empNameTxt.setText(loanList.get(position).getEmpName());
        vHolder.binding.amountTxt.setText(context.getResources().getString(R.string.inr)+" "+loanList.get(position).getAmount());
        vHolder.binding.takingDateTxt.setText(loanList.get(position).getTakingDate());

    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class AllLoanHolder extends RecyclerView.ViewHolder {
        LoanDetailsItemBinding binding;

        public AllLoanHolder(LoanDetailsItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
