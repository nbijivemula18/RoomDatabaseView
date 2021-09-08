package com.ashish.roomwithlivedata.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ashish.roomwithlivedata.model.MusicInfo;

import java.util.List;

@Dao
public interface MusicInfoDao {
    @Query("SELECT * FROM MusicInfo")
    LiveData<List<MusicInfo>> loadAll();

    @Insert
    void insertMusic(MusicInfo person);

    @Query("UPDATE MusicInfo SET stats = :stats WHERE id = :id")
    void updateMusic(String stats,int id);



}
