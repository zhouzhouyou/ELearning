package com.yuri.elearning.ui.myClasses;

import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.util.base.BaseFragment;

public class MyClassesFragment extends BaseFragment {

    public static MyClassesFragment newInstance() {
        return new MyClassesFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.my_classes_fragment;
    }

    @Override
    protected void initView(View root) {

    }

}
