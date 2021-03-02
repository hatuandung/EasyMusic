package com.example.easymusic.ui.screen.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.SearchView;
import android.widget.TableLayout;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityMusicBinding;
import com.example.easymusic.service.MP3Service;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.screen.main.MainActivity;
import com.example.easymusic.ui.screen.music.album.AlbumFragment;
import com.example.easymusic.ui.screen.music.artist.ArtistFragment;
import com.example.easymusic.ui.screen.music.list.ListFragment;
import com.example.easymusic.ui.screen.music.song.SongFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MusicActivity extends BaseActivity<ActivityMusicBinding, MusicViewModel> {

    private MP3Service service;
    private SongFragment fmSong = new SongFragment();
    private AlbumFragment fmAlbum = new AlbumFragment();
    private ArtistFragment fmArtist = new ArtistFragment();
    private ListFragment fmList = new ListFragment();


    private MusicPagerAdapter adapter;

    @Override
    protected Class<MusicViewModel> getViewModeClass() {
        return MusicViewModel.class;
    }

    @Override
    protected void init() {

        adapter = new MusicPagerAdapter(getSupportFragmentManager(), fmSong, fmArtist, fmAlbum, fmList);
        binding.pagerMusic.setAdapter(adapter);
        binding.tabMusic.setupWithViewPager(binding.pagerMusic, true);

        Intent intent = new Intent(MusicActivity.this,
                MP3Service.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

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
    protected int getLayoutId() {
        return R.layout.activity_music;
    }

    public MP3Service getService() {
        return service;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

}