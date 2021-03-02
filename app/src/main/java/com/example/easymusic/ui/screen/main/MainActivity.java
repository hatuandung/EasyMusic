package com.example.easymusic.ui.screen.main;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityMainBinding;
import com.example.easymusic.models.Song;
import com.example.easymusic.service.MP3Service;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.screen.ActivityListener;
import com.example.easymusic.ui.screen.MediaListener;
import com.example.easymusic.ui.screen.favorite.FavoriteActivity;
import com.example.easymusic.ui.screen.music.MusicActivity;
import com.example.easymusic.ui.screen.protect.password.PasswordActivity;
import com.example.easymusic.ui.screen.video.VideoActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, SongViewModel> implements MediaListener<Song>, ActivityListener, SearchView.OnQueryTextListener {

    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private MP3Service service ;

    private BaseBindingAdapter<Song> adapter;


    @Override
    protected Class<SongViewModel> getViewModeClass() {
        return SongViewModel.class;
    }


    public MP3Service getService() {
        return service;
    }

    @Override
    protected void init() {
        binding.setActivityListner(this);
        binding.search.setOnQueryTextListener(this);
        binding.txtNumSong.setText(String.valueOf(viewModel.getSong(MainActivity.this).size()) + " Bài hát");
        requestPermission(permissions, new PermissionListener() {
            @Override
            public void onResult(boolean isGranted) {
                if (isGranted) {
                    initSong();
                    Intent intent = new Intent(MainActivity.this,
                            MP3Service.class);
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initSong() {
        adapter = new BaseBindingAdapter<>(R.layout.item_song, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setListener(this);
        adapter.setData(viewModel.getSong(MainActivity.this));

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
    public void onItemMediaClicked(Song item) {
        getService().setData(adapter.getData());
        Toast.makeText(service, String.valueOf(adapter.getData().size()), Toast.LENGTH_SHORT).show();
        Toast.makeText(service, String.valueOf(item.getData()), Toast.LENGTH_SHORT).show();
        getService().getController().create(adapter.getData().indexOf(item));
        //Toast.makeText(service, String.valueOf(item.getFavorite()), Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }


    @Override
    public void onMusicListener() {
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);
    }

    @Override
    public void onVideoListener() {
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFavoriteListener() {
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivity(intent);


    }

    @Override
    public void onPrivateListener() {
        Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
        startActivity(intent);

    }

    @Override
    public void onRateListener() {

    }

    @Override
    public void onFeedBackListener() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(service, String.valueOf(viewModel.getSong(this).size()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return true;
    }
}