package com.yuri.elearning.util.recycler;

import android.util.Log;
import android.view.LayoutInflater;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {
    protected final String TAG = getClass().getSimpleName();
    protected LayoutInflater layoutInflater;
    protected List<T> dataList;
    protected int layoutId;

    public BaseRecyclerAdapter(LayoutInflater layoutInflater, List<T> dataList, int layoutId) {
        Log.i(TAG, "BaseRecyclerAdapter: " + layoutId);
        this.layoutInflater = layoutInflater;
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindVH(holder, position);
        afterBindVH(holder, position);
    }

    protected void afterBindVH(VH holder, int position) {
        //ignore
    }

    protected abstract void bindVH(VH holder, int position);

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * in case the {@link #dataList} is null
     *
     * @return size of dataList
     */
    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public List<T> getDataList() {
        Log.i(TAG, "getDataList: ");
        return dataList;
    }

    /**
     * in this case, it will update UI
     *
     * @param dataList dataList to be set
     */
    public void setDataList(List<T> dataList) {
        Log.i(TAG, "setDataList: ");
        if (this.dataList == null) this.dataList = dataList;
        else {
            this.dataList.clear();
            this.dataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }
}
