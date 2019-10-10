package com.yuri.elearning.util.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class DataBindingFragment<DB extends ViewDataBinding> extends AbstractBaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DB db = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        init(db);
        return db.getRoot();
    }

    protected abstract void init(DB db);
}
