package com.yuri.elearning.view.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yuri.elearning.R;
import com.yuri.elearning.util.PreferenceUtils;
import com.yuri.elearning.util.language.LanguageUtil;
import com.yuri.elearning.view.authentication.LoginViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    private LoginViewModel mLoginViewModel;
    private Preference mSeeDetail;
    private Preference mLogOut;
    private ListPreference mLanguages;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mLoginViewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
        //Load the Preference from the XML file
        addPreferencesFromResource(R.xml.app_preference);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSeeDetail();
        setLogOut();
        setLanguages();
    }

    private void setLanguages() {
        mLanguages = findPreference(getString(R.string.language));
        mLanguages.setOnPreferenceChangeListener((preference, newValue) -> {
            String language = (String) newValue;
            LanguageUtil.changeAppLanguage(getContext(), language);
            return false;
        });
    }

    private void setLogOut() {
        mLogOut = findPreference(getString(R.string.log_out));
        mLoginViewModel.authenticationState.observe(getViewLifecycleOwner(), authenticationState -> {
            mLogOut.setEnabled(authenticationState == LoginViewModel.AuthenticationState.AUTHENTICATED);
        });

        Objects.requireNonNull(mLogOut).setOnPreferenceClickListener(preference -> {
            getConfirmationToLogOut();
            return false;
        });
    }

    private void getConfirmationToLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(R.string.are_you_sure_to_log_out)
                .setPositiveButton(R.string.confirm, (dialog, which) -> {
                    mLoginViewModel.refuseAuthentication();
                    Toast.makeText(getActivity(), getString(R.string.log_out), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                });
        builder.create().show();
    }

    private void setSeeDetail() {
        mSeeDetail = findPreference(getString(R.string.application_information));
        Objects.requireNonNull(mSeeDetail).setOnPreferenceClickListener(preference -> {
            PreferenceUtils.gotoAppDetail(Objects.requireNonNull(getActivity()));
            return false;
        });
    }
}
