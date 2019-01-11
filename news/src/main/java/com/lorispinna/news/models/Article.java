package com.lorispinna.news.models;

import java.io.Serializable;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {@ForeignKey(entity = Source.class, parentColumns = "id_src", childColumns = "source_id")},
        indices = {@Index("source_id")}
)
public class Article  implements Serializable {
    /*@PrimaryKey(autoGenerate = true)
    public int id;*/

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "urlToImage")
    public String urlToImage;

    @ColumnInfo(name = "content")
    public String content;

    public int source_id;

    @Ignore
    public Source source;

    public boolean favorite;

    public Article() {
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content != null ? content : " no content ";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
