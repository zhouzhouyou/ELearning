package com.yuri.elearning.ui.calendar;

import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.util.base.BaseFragment;

public class CalendarFragment extends BaseFragment {

    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.calendar_fragment;
    }

    @Override
    protected void initView(View root) {

    }

}
