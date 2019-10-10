package com.yuri.elearning.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SPUtils {
    public static final String LANGUAGE = "language";
    public static final String FILE_NAME = "share_data";

    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) editor.putString(key, (String) object);
        else if (object instanceof Integer) editor.putInt(key, (Integer) object);
        else if (object instanceof Boolean) editor.putBoolean(key, (Boolean) object);
        else if (object instanceof Float) editor.putFloat(key, (Float) object);
        else if (object instanceof Long) editor.putLong(key, (Long) object);

        editor.apply();
    }

    public static Object get(Context context, String key, Class clz) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (String.class.isAssignableFrom(clz)) return sp.getString(key, "");
        else if (Integer.class.isAssignableFrom(clz)) return sp.getInt(key, 0);
        else if (Boolean.class.isAssignableFrom(clz)) return sp.getBoolean(key, false);
        else if (Float.class.isAssignableFrom(clz)) return sp.getFloat(key, 0);
        else if (Long.class.isAssignableFrom(clz)) return sp.getLong(key, 0);

        return null;
    }

    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) return sp.getString(key, (String) defaultObject);
        else if (defaultObject instanceof Integer) return sp.getInt(key, (Integer) defaultObject);
        else if (defaultObject instanceof Boolean)
            return sp.getBoolean(key, (Boolean) defaultObject);
        else if (defaultObject instanceof Float) return sp.getFloat(key, (Float) defaultObject);
        else if (defaultObject instanceof Long) return sp.getLong(key, (Long) defaultObject);

        return null;
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contain(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
