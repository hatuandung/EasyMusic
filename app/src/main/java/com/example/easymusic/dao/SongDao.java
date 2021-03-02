package com.example.easymusic.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.easymusic.models.Song;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SongDao {
    @Query("SELECT * FROM Song")
//    LiveData<List<Song>> getAll();
    List<Song> getAll();

    @Query("SELECT * FROM Song WHERE favorite = '1'")
    List<Song> getFavorite();

    @Insert
    void insert(Song... song);

    @Update
    void update(Song... song);

    @Delete
    void delete(Song... song);

    @Query("DELETE FROM Song")
    void deleteAll();
}