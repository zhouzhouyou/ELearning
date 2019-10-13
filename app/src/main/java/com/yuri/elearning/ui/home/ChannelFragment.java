package com.yuri.elearning.ui.home;

import android.view.View;
import android.widget.TextView;

import com.yuri.elearning.R;
import com.yuri.elearning.util.base.BaseFragment;

public class ChannelFragment extends BaseFragment {
    TextView mTextView;

    @Override
    protected void setMenu() {
        //ignore
    }

    public static ChannelFragment newInstance() {
        return new ChannelFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.channel_fragment;
    }

    @Override
    protected void initView(View root) {
        mTextView = root.findViewById(R.id.text_channel);
    }
}
