package com.lorispinna.news.utils;

import com.lorispinna.news.models.ArticleList;


public class ArticleSingleton {
    private static final ArticleSingleton ourInstance = new ArticleSingleton();
    private ArticleList articles;

    public static ArticleSingleton getInstance() {
        return ourInstance;
    }

    private ArticleSingleton() {
    }

    public ArticleList getArticles() {
        return articles;
    }

    public void setArticles(ArticleList articles) {
        this.articles = articles;
    }
}
