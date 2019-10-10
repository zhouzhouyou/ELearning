package com.yuri.elearning.util.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class DataBindingRecyclerAdapter<DB extends ViewDataBinding, T>
        extends BaseRecyclerAdapter<DataBindingRecyclerViewHolder, T> {
    private int brId;

    /**
     * This adapter is used with data binding.
     * for example:
     * {@code
     * DataBindingRecyclerAdapter<MainActivityBindingItem, Entity> simpleAdapter
     * = new DataBindingRecyclerAdapter<>(
     * getLayoutInflater(),
     * Collections.emptyList(),
     * R.layout.activity_main,
     * BR.entity);
     * }
     *
     * @param layoutInflater referer to {@code getLayoutInflater()}, in Fragment, call {@code getActivity() first}
     * @param dataList       initial data
     * @param layoutId       layout file name
     * @param brId           define in the layout file {@code <data><variable name = brId /></data>}
     */
    public DataBindingRecyclerAdapter(LayoutInflater layoutInflater, List<T> dataList, int layoutId, int brId) {
        super(layoutInflater, dataList, layoutId);
        this.brId = brId;
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingRecyclerViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + position);
        bindVH(holder, position);
    }

    @NonNull
    @Override
    public DataBindingRecyclerViewHolder<DB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        DB db = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
        initViewHolder(db);
        return new DataBindingRecyclerViewHolder<>(db.getRoot());
    }

    @Override
    protected void bindVH(DataBindingRecyclerViewHolder holder, int position) {
        Log.i(TAG, "bindVH: " + position);
        holder.dataBinding.setVariable(brId, dataList.get(position));
        holder.dataBinding.executePendingBindings();
    }

    protected abstract void initViewHolder(DB db);
}
