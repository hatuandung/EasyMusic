package com.example.easymusic.ui.screen.music.song;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentSongBinding;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.base.BaseFragment;
import com.example.easymusic.ui.screen.MediaListener;
import com.example.easymusic.ui.screen.main.SongViewModel;
import com.example.easymusic.ui.screen.music.MusicActivity;

public class SongFragment extends BaseFragment<FragmentSongBinding, SongViewModel> implements MediaListener<Song> {

    private BaseBindingAdapter<Song> adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseBindingAdapter<>(
                R.layout.item_song, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getSong(getContext()));
        adapter.setListener(this);

    }

    @Override
    protected Class<SongViewModel> getViewModelClass() {
        return SongViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_song;
    }

    @Override
    public void onItemMediaClicked(Song item) {
        MusicActivity musicActivity = (MusicActivity) getActivity();
        musicActivity.getService().setData(adapter.getData());
        musicActivity.getService().getController().create(adapter.getData().indexOf(item));
    }

    @Override
    public void onMenuClicked(Song item, View view) {
        adapter.createMenu(view.getContext(), view, item);
    }

    @Override
    public void onFavoriteClicked(Song item, View view) {

    }

    @Override
    public void onHiddenImageClicked(Song item, View view) {

    }


}
