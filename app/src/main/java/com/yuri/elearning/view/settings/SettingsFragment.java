package com.yuri.elearning.view.settings;

import android.os.Bundle;
import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.util.PreferenceUtils;
import com.yuri.elearning.util.language.LanguageUtil;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    private Preference mSeeDetail;
    private ListPreference mLanguages;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //Load the Preference from the XML file
        addPreferencesFromResource(R.xml.app_preference);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSeeDetail();
        setLanguages();
    }

    private void setLanguages() {
        mLanguages = findPreference(getString(R.string.language));
        Objects.requireNonNull(mLanguages).setOnPreferenceChangeListener((preference, newValue) -> {
            String language = (String) newValue;
            LanguageUtil.changeAppLanguage(getContext(), language);
            return false;
        });
    }

    private void setSeeDetail() {
        mSeeDetail = findPreference(getString(R.string.application_information));
        Objects.requireNonNull(mSeeDetail).setOnPreferenceClickListener(preference -> {
            PreferenceUtils.gotoAppDetail(Objects.requireNonNull(getActivity()));
            return false;
        });
    }
}
