package com.ashish.roomwithlivedata.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MUSICINFO")
public class MusicInfo {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String ditails;
    String stats;


    @Ignore
    public MusicInfo(String name, String ditails, String stats) {
        this.name = name;
        this.ditails = ditails;
        this.stats = stats;
    }

    public MusicInfo(int id, String name, String ditails, String stats) {
        this.id = id;
        this.name = name;
        this.ditails = ditails;
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDitails() {
        return ditails;
    }

    public void setDitails(String ditails) {
        this.ditails = ditails;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
