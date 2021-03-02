package com.example.easymusic.ui.screen.music.album;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentAlbumBinding;
import com.example.easymusic.models.Album;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.base.BaseFragment;
import com.example.easymusic.ui.screen.music.album.detail.AlbumDetailActivity;

public class AlbumFragment extends BaseFragment<FragmentAlbumBinding,AlbumViewModel> implements AlbumListener<Album>  {


    private BaseBindingAdapter<Album> adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseBindingAdapter<>(
                R.layout.item_album, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getAlbums(getContext()));
        adapter.setListener(this);

    }

    @Override
    protected Class<AlbumViewModel> getViewModelClass() {
        return AlbumViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_album;
    }


    @Override
    public void onAlbumItemListener(Album item) {
        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
        intent.putExtra("album", item);
        startActivity(intent);
    }
}
