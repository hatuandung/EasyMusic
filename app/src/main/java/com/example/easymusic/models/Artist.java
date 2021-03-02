package com.example.easymusic.models;

import android.net.Uri;
import android.provider.MediaStore;

public class Artist extends BaseModel {
    @FieldInfo(columnName = MediaStore.Audio.Artists._ID)
    private String id;

    @FieldInfo(columnName = MediaStore.Audio.Artists.ARTIST)
    private String artist;

    @FieldInfo(columnName = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)
    private String numberOfAlbum;

    @FieldInfo(columnName = MediaStore.Audio.Artists.NUMBER_OF_TRACKS)
    private String numberOfSong;


    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getNumberOfAlbum() {
        return numberOfAlbum;
    }

    public String getNumberOfSong() {
        return numberOfSong;
    }

    @Override
    public Uri getContentUri() {
        return MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
    }
}
