package com.example.easymusic.ui.screen.music.artist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentArtistBinding;
import com.example.easymusic.models.Artist;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.base.BaseFragment;
import com.example.easymusic.ui.screen.music.artist.detail.ArtistDetailActivity;

public class ArtistFragment extends BaseFragment<FragmentArtistBinding, ArtistViewModel> implements ArtistListener<Artist> {

    private BaseBindingAdapter<Artist> adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BaseBindingAdapter<>(
                R.layout.item_artist, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getArtist(getContext()));
        adapter.setListener(this);
    }

    @Override
    protected Class<ArtistViewModel> getViewModelClass() {
        return ArtistViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_artist;
    }

    @Override
    public void onArtistItemListener(Artist item) {

        Intent intent = new Intent(getActivity(), ArtistDetailActivity.class);
        intent.putExtra("artist", item);
        startActivity(intent);
    }

}
