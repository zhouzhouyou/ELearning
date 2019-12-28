package com.yuri.elearning.util.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yuri.elearning.view.MainActivity;
import com.yuri.elearning.util.App;
import com.yuri.elearning.util.SPUtils;
import com.yuri.elearning.util.language.LanguageUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected static Toast sToast;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(TAG, "attachBaseContext() called with: newBase = [" + newBase + "]");
        String language = (String) SPUtils.get(App.getContext(), SPUtils.LANGUAGE, String.class);
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, language));
    }

    private void changeLanguage(String language) {
        Log.d(TAG, "changeLanguage() called with: language = [" + language + "]");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(App.getContext(), language);
        }
        SPUtils.put(this, SPUtils.LANGUAGE, language);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: setContentView");
        setContentView(initLayout());
        Log.i(TAG, "onCreate: init");
        init();
    }

    public abstract int initLayout();

    public abstract void init();

    public void showToast(String msg, int duration) {
        if (null == sToast) {
            sToast = new Toast(this);
        }
        sToast.setDuration(duration);
        sToast.setText(msg);
        sToast.show();
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Log.i(TAG, "startActivity: " + clz.getSimpleName());
        Intent intent = buildIntent(clz, bundle);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> clz, Bundle bundle, int requestCode) {
        Intent intent = buildIntent(clz, bundle);
        startActivityForResult(intent, requestCode);
    }

    public Intent buildIntent(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (null != bundle) intent.putExtras(bundle);
        return intent;
    }

}
