package com.yuri.elearning.ui.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.yuri.elearning.R;
import com.yuri.elearning.util.base.ViewModelFragment;
import com.yuri.elearning.viewmodel.LoginViewModel;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class LoginFragment extends ViewModelFragment<LoginViewModel> {
    private NavController mNavController;
    private Button mLoginButton;
    private Button mRegisterButton;
    private EditText mAccount;
    private EditText mPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    protected void initVM() {
        mVM = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    protected void afterCreateVM(View root) {
        mNavController = Navigation.findNavController(root);
        mLoginButton = root.findViewById(R.id.login_button);
        mRegisterButton = root.findViewById(R.id.register_button);
        mAccount = root.findViewById(R.id.et_account);
        mPassword = root.findViewById(R.id.et_password);
        handleLogin();
        handleAuthenticationStateChanged(root);
        handleRegister();
        handleBackPressed();
    }

    private void handleLogin() {
        mLoginButton.setOnClickListener(v -> {
            String tempAccount = mAccount.getText().toString();
            String password = mPassword.getText().toString();
            if (!tempAccount.equals("") && !password.equals("")) {
                int account;
                try {
                    account = Integer.valueOf(tempAccount);
                    mVM.authenticate(account, password);
                    return;
                } catch (Exception e) {
                    //ignore
                }
            }
            Snackbar.make(v, getString(R.string.wrong_password_or_account), Snackbar.LENGTH_SHORT).show();
        });
    }

    private void handleAuthenticationStateChanged(View root) {
        mVM.authenticationState.observe(getViewLifecycleOwner(), authenticationState -> {
            switch (authenticationState) {
                case AUTHENTICATED:
                    mNavController.popBackStack();
                    break;
                case INVALID_AUTHENTICATION:
                    Snackbar.make(root, getString(R.string.wrong_password_or_account), Snackbar.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void handleRegister() {
        mRegisterButton.setOnClickListener(v -> mNavController.navigate(R.id.action_loginFragment_to_register_navigation));
    }

    private void handleBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                mVM.refuseAuthentication();
                mNavController.popBackStack(R.id.nav_home, false);
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.login_fragment;
    }
}
