package com.yuri.elearning.view.course;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.yuri.elearning.R;
import com.yuri.elearning.databinding.CourseFragmentBinding;
import com.yuri.elearning.databinding.LessonMessageItemBinding;
import com.yuri.elearning.model.CourseDetailInfo;
import com.yuri.elearning.model.LessonBriefInfo;
import com.yuri.elearning.util.base.DataBindingFragment;
import com.yuri.elearning.util.recycler.DataBindingRecyclerAdapter;
import com.yuri.elearning.util.recycler.DataBindingRecyclerViewHolder;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.yuri.elearning.data.network.NetTool.RESOURCE_URL;

public class CourseFragment extends DataBindingFragment<CourseFragmentBinding> {
    private MutableLiveData<CourseDetailInfo> mCourse;
    private CourseViewModel mViewModel;
    private RecyclerView mRecyclerView;

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
    }

    @Override
    protected void init(CourseFragmentBinding courseFragmentBinding) {
        mViewModel = ViewModelProviders.of(requireActivity()).get(CourseViewModel.class);

        courseFragmentBinding.setHandler(mViewModel);
        mViewModel.getBought().observe(getViewLifecycleOwner(), bought -> {
            int visibility = bought ? View.INVISIBLE : View.VISIBLE;
            courseFragmentBinding.buy.setVisibility(visibility);
        });

        mViewModel.getNeedLogin().observe(getViewLifecycleOwner(), integer ->
                Toast.makeText(getContext(), "没有登录", Toast.LENGTH_SHORT).show());

        mViewModel.getNoMoney().observe(getViewLifecycleOwner(), integer ->
                Toast.makeText(getContext(), "没有钱", Toast.LENGTH_SHORT).show());

        mViewModel.getNeedBuy().observe(getViewLifecycleOwner(), integer ->
                Toast.makeText(getContext(), "需要购买", Toast.LENGTH_SHORT).show() );


        mRecyclerView = courseFragmentBinding.recyclerView;
        LessonAdaptor lessonAdaptor = new LessonAdaptor(
                Objects.requireNonNull(getActivity()).getLayoutInflater(),
                null,
                R.layout.lesson_message_item,
                com.yuri.elearning.BR.lesson_brief_info,
                mViewModel
        );
        mCourse = mViewModel.getCourseDetail();
        mCourse.observe(getViewLifecycleOwner(), course -> {
            db.setCourse(course);
            lessonAdaptor.setDataList(course.getLessonBriefInfos());
            Glide.with(this)
                    .load(RESOURCE_URL + course.getCourse().cover)
                    .centerCrop()
                    .into(courseFragmentBinding.courseCover);
        });
        mRecyclerView.setAdapter(lessonAdaptor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        CollapsingToolbarLayout collapsingToolbarLayout = courseFragmentBinding.collapsingToolbar;
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    protected int getLayout() {
        return R.layout.course_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int cid = CourseFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getCid();
        mViewModel.setCourseDetail(cid);
    }

    private static class LessonAdaptor extends DataBindingRecyclerAdapter<LessonMessageItemBinding, LessonBriefInfo> {
        private LessonMessageItemBinding mLessonMessageItemBinding;
        private final CourseViewModel mCourseViewModel;

        public LessonAdaptor(LayoutInflater layoutInflater, List<LessonBriefInfo> dataList, int layoutId, int brId, CourseViewModel courseViewModel) {
            super(layoutInflater, dataList, layoutId, brId);
            mCourseViewModel = courseViewModel;
        }

        @Override
        protected void initViewHolder(LessonMessageItemBinding lessonMessageItemBinding) {
            mLessonMessageItemBinding = lessonMessageItemBinding;
            lessonMessageItemBinding.setHandler(mCourseViewModel);
        }

        @Override
        protected void afterBindVH(DataBindingRecyclerViewHolder holder, int position) {
            mLessonMessageItemBinding.setLessonBriefInfo(dataList.get(position));
            mLessonMessageItemBinding.executePendingBindings();

            holder.itemView.setBackgroundColor(
                    (position % 2 == 0) ?
                    Color.parseColor("#999999") :
                    Color.parseColor("#cccccc")
            );
        }
    }
}
