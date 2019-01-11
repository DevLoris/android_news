package com.lorispinna.news.listeners;

import com.lorispinna.news.models.Article;

public interface NewsDetailsClickListener  extends ShareListener {
     void onClick(Article article);
}
