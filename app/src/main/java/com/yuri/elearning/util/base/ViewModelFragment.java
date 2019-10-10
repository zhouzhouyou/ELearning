package com.yuri.elearning.util.base;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

public abstract class ViewModelFragment<VM extends ViewModel> extends BaseFragment {
    protected VM mVM;

    @Override
    protected void initView(View root) {
        Log.i(TAG, "initView: initViewModel");
        initVM();
        Log.i(TAG, "initView: afterVMCreated");
        afterCreateVM(root);
    }

    protected abstract void afterCreateVM(View root);

    protected abstract void initVM();
}
