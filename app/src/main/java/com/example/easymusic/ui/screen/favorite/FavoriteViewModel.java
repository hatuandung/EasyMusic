package com.example.easymusic.ui.screen.favorite;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.easymusic.dao.AppDatabase;
import com.example.easymusic.dao.SongRepository;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;
import java.util.List;

public class FavoriteViewModel extends BaseViewModel {

    private List<Song> songs;
    public ArrayList<Song> getSong(Context context) {
        if (songs == null) {
            //songs = AppDatabase.getInstance(context).getSongDao().getFavorite();
            songs = AppDatabase.getInstance(context).getSongDao().getAll();
        }
        return (ArrayList<Song>) songs;
    }
}
