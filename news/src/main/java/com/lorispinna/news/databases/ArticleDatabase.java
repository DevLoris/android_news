package com.lorispinna.news.databases;

import com.lorispinna.news.dao.ArticleDao;
import com.lorispinna.news.models.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
