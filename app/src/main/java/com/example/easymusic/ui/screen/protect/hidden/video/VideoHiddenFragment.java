package com.example.easymusic.ui.screen.protect.hidden.video;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentHiddenSoundBinding;
import com.example.easymusic.ui.base.BaseFragment;
import com.example.easymusic.ui.screen.protect.hidden.Sound.SoundHiddenViewModel;

public class VideoHiddenFragment extends BaseFragment<FragmentHiddenSoundBinding, SoundHiddenViewModel> {
    @Override
    protected Class<SoundHiddenViewModel> getViewModelClass() {
        return SoundHiddenViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hidden_sound;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
