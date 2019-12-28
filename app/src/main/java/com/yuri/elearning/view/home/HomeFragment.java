package com.yuri.elearning.view.home;

import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.yuri.elearning.R;
import com.yuri.elearning.util.base.BaseFragment;
import com.yuri.elearning.view.home.category.CategoryFragment;
import com.yuri.elearning.view.home.recommend.RecommendFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends BaseFragment {
    private final int NUM_TAB = 3;
    private CategoryFragment mCategoryFragment;
    private RecommendFragment mRecommendFragment;
    private TimelineFragment mTimelineFragment;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View root) {
        initialFragments();
        initialTabLayout(root);
    }

    private void initialFragments() {
        Log.d(TAG, "initialFragments() called");
        mCategoryFragment = new CategoryFragment();
        mRecommendFragment = new RecommendFragment();
        mTimelineFragment = new TimelineFragment();
    }

    private void initialTabLayout(View root) {
        Log.d(TAG, "initialTabLayout() called with: root = [" + root + "]");
        TabLayout tabLayout = root.findViewById(R.id.tabLayout);
        HomeFragmentPagerAdapter pagerAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        String[] tabNames = getResources().getStringArray(R.array.tab);


        public HomeFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            Log.d(TAG, "HomeFragmentPagerAdapter() called with: fm = [" + fm + "]");
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "getPageTitle() called with: position = [" + position + "]");
            return tabNames[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem() called with: position = [" + position + "]");
            switch (position) {
                case 0:
                    return mCategoryFragment;
                case 2:
                    return mTimelineFragment;
            }
            return mRecommendFragment;
        }

        @Override
        public int getCount() {
            Log.d(TAG, "getCount() called");
            return NUM_TAB;
        }
    }

}
