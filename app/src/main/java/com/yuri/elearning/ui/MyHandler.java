package com.yuri.elearning.ui;

import android.view.View;

import com.yuri.elearning.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MyHandler {
    public void navigateToCourse(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.nav_course);
    }
}
