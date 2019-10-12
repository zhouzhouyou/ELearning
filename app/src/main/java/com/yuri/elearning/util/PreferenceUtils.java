package com.yuri.elearning.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class PreferenceUtils {
    public static void gotoAppDetail(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

}
