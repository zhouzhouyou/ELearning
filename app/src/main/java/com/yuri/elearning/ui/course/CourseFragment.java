package com.yuri.elearning.ui.course;

import android.os.Bundle;
import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.databinding.CourseFragmentBinding;
import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.util.base.DataBindingFragment;
import com.yuri.elearning.viewmodel.CourseViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

public class CourseFragment extends DataBindingFragment<CourseFragmentBinding> {
    private MutableLiveData<Course> mCourse = new MutableLiveData<>();
    private CourseViewModel mViewModel;

    @Override
    protected void init(CourseFragmentBinding courseFragmentBinding) {
        mCourse.observe(getViewLifecycleOwner(), course -> db.setCourse(course));
        mViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.course_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int cid = CourseFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getCid();
        mCourse.setValue(mViewModel.queryCourse(cid));
    }
}
