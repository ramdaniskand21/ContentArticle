package com.example.contentarticle.dao;

import android.arch.persistence.room.Insert;

import com.example.contentarticle.model.room.Content;

public interface ContentDao {

    @Insert
    void insert (Content content);
}
