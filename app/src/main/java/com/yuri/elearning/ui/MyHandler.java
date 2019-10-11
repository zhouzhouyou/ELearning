package com.yuri.elearning.ui;

import android.view.View;

import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.ui.home.HomeFragmentDirections;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MyHandler {
    public void navigateToCourse(View view, Course course) {
        int cid = course.cid;
        //TODO
        NavController navController = Navigation.findNavController(view);
        HomeFragmentDirections.ActionNavHomeToNavCourse action = HomeFragmentDirections.actionNavHomeToNavCourse();
        action.setCid(cid);
        navController.navigate(action);
//        NavController navController = Navigation.findNavController(view);
//        navController.navigate(R.id.nav_course);
    }
}
