package com.example.easymusic.models;

import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

public class Video extends BaseModel{
    @FieldInfo(columnName = MediaStore.Video.Media._ID)
    private String id;

    @FieldInfo(columnName = MediaStore.Video.Media.TITLE)
    private String name;

    @FieldInfo(columnName = MediaStore.Video.Media.DATA)
    private String data;

    public String getThumb() {
        return thumb;
    }

    @FieldInfo(columnName = MediaStore.Video.Thumbnails.DATA)
    private String thumb;

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public Uri getContentUri() {
        return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }
}
