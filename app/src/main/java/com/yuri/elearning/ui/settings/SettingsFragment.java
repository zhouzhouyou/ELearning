package com.yuri.elearning.ui.settings;

import android.os.Bundle;

import com.yuri.elearning.R;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preference);
    }

}
