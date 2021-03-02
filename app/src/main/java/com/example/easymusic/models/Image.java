package com.example.easymusic.models;

import android.net.Uri;
import android.provider.MediaStore;

import androidx.room.ColumnInfo;

public class Image extends BaseModel{

    @FieldInfo(columnName = MediaStore.Images.Media.DISPLAY_NAME)
    private String imageName;

    @FieldInfo(columnName = MediaStore.Images.Media.DATA)
    private String imagePath;


    private  String imageSize;
    private  String imageUri;
    private Boolean selected = false;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public Uri getContentUri() {
        return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }
}
