package com.lorispinna.news.models;

import java.util.List;

public class ArticleList {
    public String status;
    public Integer totalResults;
    public List<Article> articles;

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
