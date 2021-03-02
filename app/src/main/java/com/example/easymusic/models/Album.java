package com.example.easymusic.models;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.provider.MediaStore;

public class Album extends BaseModel {

    @FieldInfo(columnName = MediaStore.Audio.Albums._ID)
    private int id;

    @FieldInfo(columnName = MediaStore.Audio.Albums.ALBUM)
    private String album;

    @FieldInfo(columnName = MediaStore.Audio.Albums.ARTIST)
    private String artist;

    @FieldInfo(columnName = MediaStore.Audio.Albums.ARTIST_ID)
    private String artistId;

    @FieldInfo(columnName = MediaStore.Audio.Albums.NUMBER_OF_SONGS)
    private int numOfSong;

    public int getId() {
        return id;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtistId() {
        return artistId;
    }

    public int getNumOfSong() {
        return numOfSong;
    }

    @Override
    public Uri getContentUri() {
        return MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    }
}
