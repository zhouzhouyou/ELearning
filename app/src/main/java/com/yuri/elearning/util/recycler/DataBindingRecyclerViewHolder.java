package com.yuri.elearning.util.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class DataBindingRecyclerViewHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected DB dataBinding;

    public DataBindingRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        dataBinding = DataBindingUtil.bind(itemView);
    }

}
