package com.example.easymusic.ui.screen.video;

import android.content.Context;

import com.example.easymusic.models.Song;
import com.example.easymusic.models.Video;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.util.ArrayList;

public class VideoViewModel extends BaseViewModel {

    private ArrayList<Video> videos;
    private Context context;

    public ArrayList<Video> getVideos(Context context) {
        if (videos == null) {
            SystemData data = new SystemData(context);
            videos = data.getData(Video.class);
        }
        return videos;
    }

}
