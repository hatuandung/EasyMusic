package com.example.easymusic.ui.screen.music.artist.detail;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityDetailArtistBinding;
import com.example.easymusic.models.Artist;
import com.example.easymusic.models.Song;
import com.example.easymusic.service.MP3Service;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.screen.MediaListener;
public class ArtistDetailActivity extends BaseActivity<ActivityDetailArtistBinding, ArtistDetailViewModel> implements MediaListener<Song> {

    private BaseBindingAdapter<Song> adapter;
    private MP3Service service;

    public MP3Service getService() {
        return service;
    }

    @Override
    protected Class<ArtistDetailViewModel> getViewModeClass() {
        return ArtistDetailViewModel.class;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Artist artist = (Artist) intent.getSerializableExtra("artist");

        Intent intentService = new Intent(this,
                MP3Service.class);
        bindService(intentService, connection, Context.BIND_AUTO_CREATE);

        adapter = new BaseBindingAdapter<>(R.layout.item_detail_artist, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getData(this, artist));
        adapter.setListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_artist;
    }

    @Override
    public void onItemMediaClicked(Song item) {
        getService().setData(adapter.getData());
        getService().getController().create(adapter.getData().indexOf(item));
    }

    @Override
    public void onMenuClicked(Song item, View view) {

    }

    @Override
    public void onFavoriteClicked(Song item, View view) {

    }

    @Override
    public void onHiddenImageClicked(Song item, View view) {

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MP3Service.MP3Binder mp3Binder = (MP3Service.MP3Binder) binder;
            service = mp3Binder.getService();
            binding.controllerView.setService(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
