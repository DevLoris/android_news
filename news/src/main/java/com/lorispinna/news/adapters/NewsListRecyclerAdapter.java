package com.lorispinna.news.adapters;

import android.util.Log;
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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
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
        holder.bindItem(articles, listener);
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    static class ViewHandler extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView descript;
        private TextView author_name;
        private ImageView imageView;
        private TextView sourceName;
        private ImageButton imageButton;
        private ImageButton like_button;
        private View v;

        ViewHandler(View v) {
            super(v);
            title = v.findViewById(R.id.news_title);
            imageView = v.findViewById(R.id.news_thumb);
            descript = v.findViewById(R.id.news_description);
            author_name = v.findViewById(R.id.author_name);
            sourceName = v.findViewById(R.id.source_name);
            imageButton = v.findViewById(R.id.share_button);
            like_button = v.findViewById(R.id.like_button);
            this.v = v;
        }

        public void bindItem(List<Article> articles, final NewsDetailsClickListener listener) {
            final Article article = articles.get(getAdapterPosition());
            title.setText(article.getTitle());
            descript.setText(article.getDescription());
            author_name.setText(article.getAuthor());
            sourceName.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(imageView);

            DrawableCompat.setTint(like_button.getDrawable(), ContextCompat.getColor(v.getContext(), article.isFavorite() ? R.color.colorAccent : R.color.white));


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShareClick(article);
                }
            });

            like_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLikeListener(article, getAdapterPosition());
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
