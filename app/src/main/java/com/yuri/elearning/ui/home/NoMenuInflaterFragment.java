package com.yuri.elearning.ui.home;

import android.view.Menu;
import android.view.MenuInflater;

import com.yuri.elearning.util.base.BaseFragment;

import androidx.annotation.NonNull;

public abstract class NoMenuInflaterFragment extends BaseFragment {
    @Override
    protected void initOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //ignore
    }

    @Override
    protected void setMenu() {
        //ignore
    }
}
