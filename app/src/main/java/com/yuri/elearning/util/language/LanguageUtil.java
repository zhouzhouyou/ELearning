package com.yuri.elearning.util.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.yuri.elearning.MainActivity;
import com.yuri.elearning.R;

import java.util.Locale;

public class LanguageUtil {
    private static final String TAG = "LanguageUtil";

    /**
     * @param context     context
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    public static void changeAppLanguage(Context context, String newLanguage) {
        if (TextUtils.isEmpty(newLanguage)) {
            return;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //获取想要切换的语言类型
        Locale locale = getLocaleByLanguage(context, newLanguage);
        configuration.setLocale(locale);
        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static Locale getLocaleByLanguage(Context context, String language) {
        Locale locale = Locale.getDefault();

        if (language.equals(context.getString(R.string.simplified_chinese))) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language.equals(context.getString(R.string.english))) {
            locale = Locale.ENGLISH;
        }
        Log.d(TAG, "getLocaleByLanguage: " + locale.getDisplayName());
        return locale;
    }

    public static Context attachBaseContext(Context context, String language) {
        Log.d(TAG, "attachBaseContext: " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtil.getLocaleByLanguage(context, language);

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }
}
