package com.yuri.elearning.view;

import android.view.View;

import com.yuri.elearning.MobileNavigationDirections;
import com.yuri.elearning.model.LessonBriefInfo;
import com.yuri.elearning.view.home.HomeFragmentDirections;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MyHandler {
    public static int cid;

    public void navigateToCourse(View view, int cid) {
        //TODO
        NavController navController = Navigation.findNavController(view);
        MobileNavigationDirections.ActionGlobalNavCourse action = MobileNavigationDirections.actionGlobalNavCourse(cid);
        navController.navigate(action);
    }

    public void navigateToLesson(View view, LessonBriefInfo lesson) {

    }

    public void navigateToCategoryDetail(View view, int id) {
        NavController navController = Navigation.findNavController(view);
        HomeFragmentDirections.ActionNavHomeToCategoryDetailFragment action = HomeFragmentDirections.actionNavHomeToCategoryDetailFragment(id);
        navController.navigate(action);
    }
}
