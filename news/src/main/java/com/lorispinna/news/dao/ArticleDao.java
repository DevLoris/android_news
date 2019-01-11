package com.lorispinna.news.dao;

import com.lorispinna.news.models.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE url LIKE :url")
    Article findByUrl(String url);

    @Query("SELECT * FROM article WHERE title LIKE :title")
    Article findByTitle(String title);

    @Insert
    void insertAll(List<Article> articles);

    @Insert
    void insertAll(Article... articles);

    @Insert
    void insert(Article article);

    @Delete
    void delete(Article article);
}
