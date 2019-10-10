package com.yuri.elearning.util.recycler;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SimpleRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private SparseArray<View> mViewSparseArray;
    private OnItemBaseClickListener mOnItemBaseClickListener;

    public SimpleRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mViewSparseArray = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public SimpleRecyclerViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public SimpleRecyclerViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public SimpleRecyclerViewHolder setImageResource(int viewId, int resourceId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resourceId);
        return this;
    }

    public void setOnItemBaseClickListener(OnItemBaseClickListener baseClickListener) {
        mOnItemBaseClickListener = baseClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemBaseClickListener != null) {
            mOnItemBaseClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemBaseClickListener != null) {
            mOnItemBaseClickListener.onItemLongClickListener(getAdapterPosition());
        }
        return false;
    }

    protected interface OnItemBaseClickListener {
        void onItemClickListener(int position);

        void onItemLongClickListener(int position);
    }
}
