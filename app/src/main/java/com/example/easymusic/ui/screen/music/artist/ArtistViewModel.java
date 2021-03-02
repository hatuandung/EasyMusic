package com.example.easymusic.ui.screen.music.artist;

import android.content.Context;

import com.example.easymusic.models.Artist;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;

public class ArtistViewModel extends BaseViewModel {

    private ArrayList<Artist> artists;

    public ArrayList<Artist> getArtist(Context context) {
        if (artists == null) {
            SystemData data = new SystemData(context);
            artists = data.getData(Artist.class);
        }
        return artists;
    }
}
