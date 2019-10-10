package com.yuri.elearning.util.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;

public abstract class SimpleRecyclerAdapter<T> extends BaseRecyclerAdapter<SimpleRecyclerViewHolder, T> {

    /**
     * create a base recycler adapter whose view holder is {@link SimpleRecyclerViewHolder}
     *
     * @param layoutInflater references {@code getLayoutInflater()}
     * @param dataList       can be null
     * @param layoutId       {@code R.layout.your_recycler_item}
     */
    public SimpleRecyclerAdapter(LayoutInflater layoutInflater, List<T> dataList, int layoutId) {
        super(layoutInflater, dataList, layoutId);
    }

    @NonNull
    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new SimpleRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleRecyclerViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + position);
        bindData(holder, dataList.get(position));
    }

    /**
     * after creating a new holder
     *
     * @param holder holder that hold the layout {@link #layoutId}
     * @param data   data to be bind to the holder
     */
    protected abstract void bindData(SimpleRecyclerViewHolder holder, T data);
}
