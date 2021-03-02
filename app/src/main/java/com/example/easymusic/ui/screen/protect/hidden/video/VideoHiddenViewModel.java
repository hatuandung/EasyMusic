package com.example.easymusic.ui.screen.protect.hidden.video;

import android.content.Context;

import com.example.easymusic.ui.base.BaseViewModel;

import java.io.File;
import java.util.ArrayList;

public class VideoHiddenViewModel extends BaseViewModel {

    public ArrayList<File> getImages(Context context, File file){
        ArrayList<File> videos = new ArrayList<>();
        File[] files = file.listFiles();

        for (File child : files) {
            if (child != null && child.getName().toLowerCase().endsWith(".mp4")){
                videos.add(child);
            }
            if (child.isDirectory()){
                getImages(context, child);
            }
        }
        return videos;
    }

}
