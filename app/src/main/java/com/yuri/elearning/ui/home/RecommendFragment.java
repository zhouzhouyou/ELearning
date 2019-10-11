package com.yuri.elearning.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.yuri.elearning.R;
import com.yuri.elearning.databinding.CourseRecyclerItemBinding;
import com.yuri.elearning.datasource.database.entity.Course;
import com.yuri.elearning.ui.MyHandler;
import com.yuri.elearning.util.base.ViewModelFragment;
import com.yuri.elearning.util.recycler.DataBindingRecyclerAdapter;
import com.yuri.elearning.viewmodel.CourseViewModel;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends ViewModelFragment<CourseViewModel> {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;

    @Override
    protected void initVM() {
        Log.i(TAG, "initVM: ");
        mVM = ViewModelProviders.of(this).get(CourseViewModel.class);
        Log.i(TAG, "initVM: finish");
    }

    @Override
    protected void afterCreateVM(View root) {
        recyclerView = root.findViewById(R.id.recycler_view);
        courseAdapter = new CourseAdapter(
                Objects.requireNonNull(getActivity()).getLayoutInflater(),
                null,
                R.layout.course_recycler_item,
                com.yuri.elearning.BR.course);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mVM.getAllCourses().observe(this, courses -> courseAdapter.setDataList(courses));
    }

    @Override
    protected int getLayout() {
        return R.layout.recommend_fragment;
    }

    private class CourseAdapter extends DataBindingRecyclerAdapter<CourseRecyclerItemBinding, Course> {
        public CourseAdapter(LayoutInflater layoutInflater, List<Course> dataList, int layoutId, int brId) {
            super(layoutInflater, dataList, layoutId, brId);
        }

        @Override
        protected void initViewHolder(CourseRecyclerItemBinding courseRecyclerItemBinding) {
            Log.i(TAG, "initViewHolder: ");
            courseRecyclerItemBinding.setHandler(new MyHandler());
        }
    }
}
