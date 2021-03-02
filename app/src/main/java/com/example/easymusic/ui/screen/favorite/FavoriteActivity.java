package com.example.easymusic.ui.screen.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityFavoriteBinding;
import com.example.easymusic.models.Song;
import com.example.easymusic.service.MP3Service;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.screen.MediaListener;
import com.example.easymusic.ui.screen.main.MainActivity;
import com.example.easymusic.ui.screen.main.SongViewModel;

import java.util.List;

public class FavoriteActivity extends BaseActivity<ActivityFavoriteBinding, FavoriteViewModel> implements MediaListener<Song> {

    public BaseBindingAdapter<Song> adapter;
    private MP3Service service;

    public MP3Service getService() {
        return service;
    }



    @Override
    protected Class<FavoriteViewModel> getViewModeClass() {
        return FavoriteViewModel.class;
    }

    @Override
    protected void init() {
        adapter = new BaseBindingAdapter<>(R.layout.item_favorite, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setData(viewModel.getSong(this));
        adapter.setListener(this);

        Intent intent = new Intent(FavoriteActivity.this,
                MP3Service.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favorite;
    }

    @Override
    public void onItemMediaClicked(Song item) {
        getService().setData(adapter.getData());
        getService().getController().create(adapter.getData().indexOf(item));
        Toast.makeText(service, String.valueOf(item.getFavorite()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuClicked(Song item, View view) {

    }

    @Override
    public void onFavoriteClicked(Song item, View view) {
        adapter.createFavoriteMenu(view.getContext(), view, item);
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