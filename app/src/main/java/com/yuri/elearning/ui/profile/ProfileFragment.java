package com.yuri.elearning.ui.profile;

import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.util.base.ViewModelFragment;
import com.yuri.elearning.viewmodel.LoginViewModel;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ProfileFragment extends ViewModelFragment<LoginViewModel> {
    private NavController mNavController;

    @Override
    protected void afterCreateVM(View root) {
        mNavController = Navigation.findNavController(root);
        mVM.authenticationState.observe(getViewLifecycleOwner(), authenticationState -> {
            switch (authenticationState) {
                case AUTHENTICATED:
                    //TODO:
                    break;
                case UNAUTHENTICATED:
                    mNavController.navigate(R.id.nav_login);
                    break;
                default:
                    //TODO
            }
        });
    }

    @Override
    protected void initVM() {
        mVM = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.profile_fragment;
    }
}
