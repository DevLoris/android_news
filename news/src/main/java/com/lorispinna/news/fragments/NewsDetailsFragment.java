package com.lorispinna.news.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorispinna.news.R;
import com.lorispinna.news.adapters.NewsListRecyclerAdapter;
import com.lorispinna.news.listeners.OpenUrlListener;
import com.lorispinna.news.models.Article;
import com.lorispinna.news.viewmodels.ArticleViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class NewsDetailsFragment extends CommonFragment {
    TextView title;
    TextView descript;
    ImageView imageView;
    TextView content;
    Button button_viewmore;
    private ArticleViewModel model;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news_details, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE && x2 > x1)
                            Toast.makeText(v.getContext(), "swipe", Toast.LENGTH_SHORT).show ();
                        break;
                }
                return true;
            }
        });

        title = view.findViewById(R.id.news_title);
        descript = view.findViewById(R.id.news_description);
        imageView = view.findViewById(R.id.news_thumb);
        content = view.findViewById(R.id.news_content);
        button_viewmore = view.findViewById(R.id.view_button);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model.getSelectedArticle().observe(this, new Observer<Article>() {
            @Override
            public void onChanged(Article article) {
                title.setText(article.getTitle());
                descript.setText(article.getDescription());
                content.setText(article.getContent());
                Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(imageView);
                button_viewmore.setOnClickListener(new OpenUrlListener(article.getUrl()));
            }
        });
    }

}
