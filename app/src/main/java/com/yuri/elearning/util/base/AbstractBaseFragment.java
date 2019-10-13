package com.yuri.elearning.util.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.yuri.elearning.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AbstractBaseFragment extends Fragment {
    protected static Toast sToast;
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        initOptionsMenu(menu, inflater);
    }

    /**
     * you can change your item's visibilities here
     * if you don't want the menu, just override this method and write no code
     * @param menu menu
     * @param inflater menu inflater
     */
    protected void initOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu();
    }

    /**
     * if you want to remove menu, use {@code setHasOptionsMenu(false)}
     * but if you want to override back button in action bar, please keep it true
     */
    protected void setMenu() {
        setHasOptionsMenu(true);
        setMenuVisibility(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach() called with: context = [" + context + "]");
        super.onAttach(context);
        mContext = context;
    }

    /**
     * resource id, invoked by inflater.
     * @return resource id
     */
    protected abstract int getLayout();

    public void showToast(String msg, int duration) {
        Log.d(TAG, "showToast() called with: msg = [" + msg + "], duration = [" + duration + "]");
        if (null == sToast) sToast = Toast.makeText(getContext(), msg, duration);
        else {
            sToast.setDuration(duration);
            sToast.setText(msg);
        }
        sToast.show();
    }
}
