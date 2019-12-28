package com.yuri.elearning.view.lesson;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.yuri.elearning.R;
import com.yuri.elearning.databinding.LessonFragmentBinding;
import com.yuri.elearning.model.Lesson;
import com.yuri.elearning.util.base.DataBindingFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

public class LessonFragment extends DataBindingFragment<LessonFragmentBinding> {
    private MutableLiveData<Lesson> mLesson;
    private LessonViewModel mViewModel;
    private PlayerView mPlayerView;
    private ExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void init(LessonFragmentBinding lessonFragmentBinding) {
        mViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
        mPlayerView = lessonFragmentBinding.videoPlayer;
        mLesson = mViewModel.getLesson();
        mLesson.observe(getViewLifecycleOwner(), lesson -> {
            db.setLesson(lesson);
            initializePlayer();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.lesson_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = LessonFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getId();
        mViewModel.setLesson(id);
    }

    private void initializePlayer() {
        String videoUri = mLesson.getValue().video;
        if (videoUri == null) {
            mPlayerView.setVisibility(View.INVISIBLE);
            return;
        }

        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    getContext(),
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());

            mPlayerView.setPlayer(player);

            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }


        Uri uri = Uri.parse(videoUri);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, false, true);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DefaultHttpDataSourceFactory dataSourceFactory =
                new DefaultHttpDataSourceFactory("user-agent");

        ExtractorMediaSource videoSource =
                new ExtractorMediaSource.Factory(dataSourceFactory).
                        createMediaSource(uri);

        ExtractorMediaSource audioSource =
                new ExtractorMediaSource.Factory(dataSourceFactory).
                        createMediaSource(uri);

        return new ConcatenatingMediaSource(audioSource, videoSource);
    }
}
