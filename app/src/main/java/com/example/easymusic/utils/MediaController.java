package com.example.easymusic.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.example.easymusic.models.Song;
import com.example.easymusic.models.Video;

import java.util.ArrayList;
import java.util.List;


public class MediaController implements MediaPlayer.OnCompletionListener {
    private List<Song> songs;
    private Context context;
    private MediaPlayer player;
    public int index;

    public MediaController(List<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }
    

    public void create(int index) {
        release();
        this.index = index;
        Uri uri = Uri.parse(songs.get(index).getData());
        player = MediaPlayer.create(context,uri);
        start();
        player.setOnCompletionListener(this);
    }

    public void start() {
        if (player != null) {
            player.start();
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public void release() {
        if (player != null) {
            player.release();
        }
    }

    public void loop(boolean isLooping) {
        if (player != null) {
            player.setLooping(isLooping);
        }
    }

    public void seek(int position) {
        if (player != null) {
            player.seekTo(position);
        }
    }

    public String getSongArtist(){return songs.get(index).getArtist();}

    public String getSongName() {
        return songs.get(index).getTitle();
    }

    public int getImage(){
        return songs.get(index).getAlbumId();
    }

    public boolean isFavorite(){
        return songs.get(index).getFavorite() == 1 ? true : false;
    }

    public boolean isPlaying() {
        return player == null ? false : player.isPlaying();
    }

    public int getDuration() {
        return player == null ? 0 : player.getDuration();
    }

    public int getPosition() {
        return player == null ? 0 : player.getCurrentPosition();
    }

    // 1: next
    // -1: prev
    public void change(int value) {
        index += value;
        if (index < 0) {
            index = songs.size() - 1;
        } else if (index >= songs.size()) {
            index = 0;
        }
        create(index);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        change(1);
    }
}
