package com.example.easymusic.ui.screen.protect.hidden;

import android.content.Intent;
import android.view.View;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityHiddenBinding;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.screen.protect.hidden.video.choosevideo.ChooseVideoActivity;
import com.example.easymusic.ui.screen.protect.hidden.image.ImageHiddenFragment;
import com.example.easymusic.ui.screen.protect.hidden.image.choosefolder.ChooseFolderActivity;
import com.example.easymusic.ui.screen.protect.hidden.Sound.SoundHiddenFragment;
import com.example.easymusic.ui.screen.protect.hidden.video.VideoHiddenFragment;

public class HiddenActivity extends BaseActivity<ActivityHiddenBinding, HiddenViewModel> {

    private HiddenPagerAdapter adapter;
    private ImageHiddenFragment fmImage = new ImageHiddenFragment();
    private SoundHiddenFragment fmSound = new SoundHiddenFragment();
    private VideoHiddenFragment fmVideo = new VideoHiddenFragment();


    @Override
    protected Class<HiddenViewModel> getViewModeClass() {
        return HiddenViewModel.class;
    }

    @Override
    protected void init() {
        adapter = new HiddenPagerAdapter(getSupportFragmentManager(), fmImage, fmSound, fmVideo);
        binding.pagerHidden.setAdapter(adapter);
        binding.tabHidden.setupWithViewPager(binding.pagerHidden, true);

        binding.ftbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(binding.tabHidden.getSelectedTabPosition());
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hidden;
    }

    private void openActivity(int tab) {
        switch (tab) {
            case 0:
                Intent intent = new Intent(HiddenActivity.this, ChooseFolderActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent( HiddenActivity.this, ChooseVideoActivity.class);
                startActivity(intent1);
                break;
            case 2:
                break;
        }
    }
}