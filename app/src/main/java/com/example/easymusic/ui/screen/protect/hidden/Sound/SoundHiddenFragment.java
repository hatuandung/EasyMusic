package com.example.easymusic.ui.screen.protect.hidden.Sound;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentHiddenSoundBinding;
import com.example.easymusic.ui.base.BaseFragment;

public class SoundHiddenFragment extends BaseFragment<FragmentHiddenSoundBinding, SoundHiddenViewModel> {
    @Override
    protected Class<SoundHiddenViewModel> getViewModelClass() {
        return SoundHiddenViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hidden_sound;
    }
}
