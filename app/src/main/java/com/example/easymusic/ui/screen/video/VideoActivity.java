package com.example.easymusic.ui.screen.video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityVideoBinding;
import com.example.easymusic.models.Video;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public class VideoActivity extends BaseActivity<ActivityVideoBinding, VideoViewModel> implements VideoListener<Video> {

    private BaseBindingAdapter<Video> adapter;

    @Override
    protected Class<VideoViewModel> getViewModeClass() {
        return VideoViewModel.class;
    }

    @Override
    protected void init() {
        adapter = new BaseBindingAdapter<>(
                R.layout.item_video, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getVideos(VideoActivity.this));
        adapter.setListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void onItemVideoClicked(Video item) {
        Intent intent = new Intent(VideoActivity.this, PlayVideoActivity.class);
        intent.putExtra("video", item);
        startActivity(intent);
    }
}