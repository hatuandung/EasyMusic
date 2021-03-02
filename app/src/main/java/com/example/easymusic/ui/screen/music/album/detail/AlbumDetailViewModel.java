package com.example.easymusic.ui.screen.music.album.detail;

import android.content.Context;

import com.example.easymusic.models.Album;
import com.example.easymusic.models.Artist;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;

public class AlbumDetailViewModel extends BaseViewModel {
    private ArrayList<Song> data = new ArrayList<>();
    private ArrayList<Song> songs;

    public ArrayList<Song> getData(Context context, Album album) {

        if (songs == null) {
            SystemData systemData = new SystemData(context);
            songs = systemData.getData(Song.class);
        }

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getAlbum().equals(album.getAlbum()))
                data.add(songs.get(i));
        }
        return data;
    }
}
