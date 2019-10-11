package com.yuri.elearning.ui.authentication;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.yuri.elearning.R;
import com.yuri.elearning.util.base.ViewModelFragment;
import com.yuri.elearning.viewmodel.LoginViewModel;
import com.yuri.elearning.viewmodel.RegistrationViewModel;
import com.yuri.elearning.viewmodel.RegistrationViewModel.RegistrationState;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ChooseUserPasswordFragment extends ViewModelFragment<RegistrationViewModel> {
    private Button mRegisterLogin;
    private EditText mAccount;
    private EditText mPassword;
    private NavController mNavController;
    private LoginViewModel mLoginViewModel;


    @Override
    protected void afterCreateVM(View root) {
        mNavController = Navigation.findNavController(root);
        mRegisterLogin = root.findViewById(R.id.register_login);
        mAccount = root.findViewById(R.id.et_account);
        mPassword = root.findViewById(R.id.et_password);
        initRegisterLogin();
        handleRegistrationStateChanged();
        initBackPressed();
    }

    private void initRegisterLogin() {
        mRegisterLogin.setOnClickListener(v -> {
            String tempAccount = mAccount.getText().toString();
            String password = mPassword.getText().toString();
            if (!tempAccount.equals("") && !password.equals("")) {
                int account;
                try {
                    account = Integer.valueOf(tempAccount);
                    mVM.createAccountAndLogin(account, password);
                    return;
                } catch (Exception e) {
                    //ignore
                }
            }
            Snackbar.make(v, getString(R.string.please_check_input), Snackbar.LENGTH_SHORT).show();
        });
    }

    private void handleRegistrationStateChanged() {
        mVM.getRegistrationState().observe(getViewLifecycleOwner(), registrationState -> {
            if (registrationState == RegistrationState.REGISTRATION_COMPLETED) {
                String authToken = mVM.getAuthToken();
                mLoginViewModel.authenticate(authToken);
                mNavController.popBackStack(R.id.nav_profile, false);
            }
        });
    }

    private void initBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        mVM.userCancelledRegistration();
                        mNavController.popBackStack(R.id.nav_login, false);
                    }
                });
    }

    @Override
    protected void initVM() {
        ViewModelProvider provider = ViewModelProviders.of(requireActivity());
        mVM = provider.get(RegistrationViewModel.class);
        mLoginViewModel = provider.get(LoginViewModel.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.choose_user_password_fragment;
    }
}
