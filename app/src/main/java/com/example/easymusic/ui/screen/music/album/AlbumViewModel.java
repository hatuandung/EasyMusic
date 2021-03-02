package com.example.easymusic.ui.screen.music.album;

import android.content.Context;

import com.example.easymusic.models.Album;
import com.example.easymusic.models.Artist;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;

public class AlbumViewModel extends BaseViewModel {
    private ArrayList<Album> albums;

    public ArrayList<Album> getAlbums(Context context) {
        if (albums == null) {
            SystemData data = new SystemData(context);
            albums = data.getData(Album.class);
        }
        return albums;
    }
}
