package com.example.easymusic.ui.screen.protect.hidden.image;

import android.content.Context;

import com.example.easymusic.models.Image;
import com.example.easymusic.ui.base.BaseViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageHiddenViewModel extends BaseViewModel {

//    public ArrayList<Image> getImages (Context context){
//        ArrayList<Image> images = new ArrayList<>();
//        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null, null, null);
//        cursor.moveToFirst();
//
//        int indexName = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
//        int indexImage = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//
//
//        while (cursor.isAfterLast() == false) {
//            Image image = new Image();
//            //album.setImage(cursor.getLong(indexImage));
//            image.setImageName(cursor.getString(indexName));
//            image.setImagePath(cursor.getString(indexImage));
//
//            images.add(image);
//            cursor.moveToNext();
//        }
//        return images;
//    }
    public ArrayList<File> getImages(Context context, File file){
        ArrayList<File> images = new ArrayList<>();
        File[] files = file.listFiles();

        for (File child : files) {
            if (child != null && child.getName().toLowerCase().endsWith(".jpg")){
                images.add(child);
            }
            if (child.isDirectory()){
                getImages(context, child);
            }
        }
        return images;
    }
}
