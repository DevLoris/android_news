package com.lorispinna.news.utils;

import com.lorispinna.news.dao.ArticleDao;
import com.lorispinna.news.databases.ArticleDatabase;

public class DatabaseListSingleton {
    private ArticleDatabase articleDatabase;

    private static final DatabaseListSingleton ourInstance = new DatabaseListSingleton();

    public static DatabaseListSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseListSingleton() {
    }

    public ArticleDatabase getArticleDatabase() {
        return articleDatabase;
    }
    public ArticleDao  getArticleDao() {
        return this.getArticleDatabase().articleDao();
    }

    public void setArticleDatabase(ArticleDatabase articleDatabase) {
        this.articleDatabase = articleDatabase;
    }
}
