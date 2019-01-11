package com.lorispinna.news.databases;

import com.lorispinna.news.dao.ArticleDao;
import com.lorispinna.news.databases.converter.SourceConverter;
import com.lorispinna.news.models.Article;
import com.lorispinna.news.models.Source;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Article.class, Source.class}, version = 3, exportSchema = false)
//@TypeConverters({SourceConverter.class})
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
