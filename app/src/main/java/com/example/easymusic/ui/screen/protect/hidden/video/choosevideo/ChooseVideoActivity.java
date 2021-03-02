package com.example.easymusic.ui.screen.protect.hidden.video.choosevideo;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityChooseVideoBinding;
import com.example.easymusic.models.Video;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;


import java.util.ArrayList;

public class ChooseVideoActivity extends BaseActivity<ActivityChooseVideoBinding, ChooseVideoViewModel> implements HiddenVideoListener<Video> {

    BaseBindingAdapter<Video> adapter;
    ArrayList<String> files;

    @Override
    protected Class<ChooseVideoViewModel> getViewModeClass() {
        return ChooseVideoViewModel.class;
    }

    @Override
    protected void init() {
        files = new ArrayList<>();
        adapter = new BaseBindingAdapter<>(
                R.layout.item_choose_video, getLayoutInflater());
        binding.setAdapter(adapter);
        binding.setAdd(this);
        adapter.setData(viewModel.getVideos(this));
        adapter.setListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_video;
    }

    @Override
    public void onVideoHiddenClicked(Video item) {
        files.add(item.getData());
        binding.btnAdd.setBackgroundColor(Color.parseColor("#00BCD4"));
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddVideoClicked() {
        for (int i = 0; i < files.size() ; i++) {
            viewModel.new AsyncVideo(this, files.get(i)).execute(files.get(i));
            Log.e("onAddVideoClicked: ", files.get(i));
        }
        Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
    }

}