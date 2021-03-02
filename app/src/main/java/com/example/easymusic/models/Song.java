package com.example.easymusic.models;

import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song extends BaseModel {

//    @NonNull
//    @PrimaryKey(autoGenerate = true)
//    private int songId;

    @FieldInfo(columnName = MediaStore.Audio.Media._ID)
    @NonNull
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "data")
    @FieldInfo(columnName = MediaStore.Audio.Media.DATA)
    private String data;

    @ColumnInfo(name = "size")
    @FieldInfo(columnName = MediaStore.Audio.Media.SIZE)
    private int size;

    @ColumnInfo(name = "title")
    @FieldInfo(columnName = MediaStore.Audio.Media.TITLE)
    private String title;

    @ColumnInfo(name = "duration")
    @FieldInfo(columnName = MediaStore.Audio.Media.DURATION)
    private int duration;

    @ColumnInfo(name = "artist")
    @FieldInfo(columnName = MediaStore.Audio.Media.ARTIST)
    private String artist;

    @ColumnInfo(name = "album")
    @FieldInfo(columnName = MediaStore.Audio.Media.ALBUM)
    private String album;

    @ColumnInfo(name = "albumId")
    @FieldInfo(columnName = MediaStore.Audio.Media.ALBUM_ID)
    private int albumId;

    @ColumnInfo(name = "favorite")
    private int favorite;


    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

//    public void setSongId(@NonNull int songId) {
//        this.songId = songId;
//    }
//
//    @NonNull
//    public int getSongId() {
//        return songId;
//    }

    public int getFavorite() {
        return favorite;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getAlbumId() {
        return albumId;
    }

    @Override
    public Uri getContentUri() {
        return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }
}
