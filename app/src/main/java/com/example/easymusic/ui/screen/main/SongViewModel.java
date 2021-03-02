package com.example.easymusic.ui.screen.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.easymusic.dao.AppDatabase;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.ui.screen.music.MusicActivity;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;


public class SongViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<Song>> data;
    private ArrayList<Song> songs;

    public ArrayList<Song> getSong(Context context) {
        if (songs == null) {
            SystemData data = new SystemData(context);
            songs = data.getData(Song.class);
        }
        return songs;
    }

//    public MutableLiveData<ArrayList<Song>> getData(Context context){
//        if (data == null){
//            SystemData systemData = new SystemData(context);
//            data = systemData.getData(Song.class);
//        }
//        return data;
//    }

}
