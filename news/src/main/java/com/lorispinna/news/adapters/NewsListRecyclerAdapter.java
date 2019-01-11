package com.lorispinna.news.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorispinna.news.R;
import com.lorispinna.news.listeners.NewsDetailsClickListener;
import com.lorispinna.news.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListRecyclerAdapter extends RecyclerView.Adapter<NewsListRecyclerAdapter.ViewHandler>{

    private List<Article> articles;
    private NewsDetailsClickListener listener;
    public NewsListRecyclerAdapter( NewsDetailsClickListener listener) {
        this.listener = listener;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsListRecyclerAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list_row, parent, false);
        ViewHandler vh = new ViewHandler(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListRecyclerAdapter.ViewHandler holder, int position) {
        holder.bindItem(articles.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    static class ViewHandler extends RecyclerView.ViewHolder {
        TextView title;
        TextView descript;
        ImageView imageView;
        ImageButton imageButton;
        View v;

        ViewHandler(View v) {
            super(v);
            title = v.findViewById(R.id.news_title);
            imageView = v.findViewById(R.id.news_thumb);
            descript = v.findViewById(R.id.news_description);
            imageButton = v.findViewById(R.id.share_button);
            this.v = v;
        }

        public void bindItem(final Article article, final NewsDetailsClickListener listener) {
            title.setText(article.getTitle());
            descript.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(imageView);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShareClick(article);
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(article);
                }
            });
        }
    }
}
