package com.example.contentarticle.helper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.contentarticle.dao.ContentDao;
import com.example.contentarticle.model.room.Content;

@Database(entities = (Content.class), version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContentDao contentDao();
}
