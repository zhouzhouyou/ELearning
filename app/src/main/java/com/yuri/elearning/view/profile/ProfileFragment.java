package com.yuri.elearning.view.profile;

import android.os.Bundle;
import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.data.repository.UserRepository;
import com.yuri.elearning.databinding.ProfileFragmentBinding;
import com.yuri.elearning.model.User;
import com.yuri.elearning.util.base.DataBindingFragment;
import com.yuri.elearning.view.authentication.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ProfileFragment extends DataBindingFragment<ProfileFragmentBinding> {
    private NavController mNavController;
    private LoginViewModel mVM;
    private ProfileViewModel mProfileViewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVM = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
        mNavController = Navigation.findNavController(view);
        mVM.authenticationState.observe(getViewLifecycleOwner(), authenticationState -> {
            switch (authenticationState) {
                case UNAUTHENTICATED:
                case INVALID_AUTHENTICATION:
                    mNavController.navigate(R.id.nav_login);
                    break;
            }
        });
    }

    @Override
    protected void init(ProfileFragmentBinding profileFragmentBinding) {
        goOn(profileFragmentBinding);
    }

    private void goOn(ProfileFragmentBinding profileFragmentBinding) {
        mProfileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        mProfileViewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), profileFragmentBinding::setUser);
        profileFragmentBinding.setHandler(mProfileViewModel);
        mProfileViewModel.setUser(new User(UserRepository.ID, UserRepository.NAME, "", UserRepository.MONEY));
    }

    @Override
    protected int getLayout() {
        return R.layout.profile_fragment;
    }
}
