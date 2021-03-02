package com.example.easymusic.ui.screen.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.MediaController;

import androidx.annotation.NonNull;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityPlayVideoBinding;
import com.example.easymusic.models.Video;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseViewModel;

public class PlayVideoActivity extends BaseActivity<ActivityPlayVideoBinding, BaseViewModel>{

    private Video video;
    private MediaController controller;

    @Override
    protected Class<BaseViewModel> getViewModeClass() {
        return BaseViewModel.class;
    }

    @Override
    protected void init() {
        controller = new MediaController(this);
        controller.setAnchorView(binding.vdv);
        Intent intent = getIntent();
        video = (Video) intent.getSerializableExtra("video");
        Uri uri = Uri.parse(video.getData());
        binding.vdv.requestFocus();
        binding.vdv.setMediaController(controller);
        binding.vdv.setVideoURI(uri);
        binding.vdv.start();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_video;
    }

}