package com.yuri.elearning.util.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class AbstractBaseFragment extends Fragment {
    protected static Toast sToast;
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach() called with: context = [" + context + "]");
        super.onAttach(context);
        mContext = context;
    }

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
