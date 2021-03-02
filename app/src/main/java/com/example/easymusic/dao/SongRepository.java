package com.example.easymusic.dao;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.easymusic.models.Song;

import java.util.List;

public class SongRepository {
    private SongDao songDao;
    private LiveData<List<Song>> allFavorite;
    private LiveData<List<Song>> allSong;

    public SongRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        songDao = database.getSongDao();

        //allFavorite = songDao.getFavorite();
        //allSong = songDao.getAll();
    }

    public LiveData<List<Song>> getAllFavorite() {
        return allFavorite;
    }

    public LiveData<List<Song>> getAllSong() {
        return allSong;
    }

    public void update(Song song) {
        new updateAsyncTask(songDao).execute(song);
    }

    public void insert(Song song) {
        new insertAsyncTask(songDao).execute(song);
    }

    private static class updateAsyncTask extends AsyncTask<Song, Void, Void> {

        private SongDao asyncTaskDao;

        updateAsyncTask(SongDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Song... params) {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Song, Void, Void> {
        private SongDao asyncTaskDao;

        insertAsyncTask(SongDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected Void doInBackground(Song... songs) {
            asyncTaskDao.insert(songs[0]);
            return null;
        }
    }
}
