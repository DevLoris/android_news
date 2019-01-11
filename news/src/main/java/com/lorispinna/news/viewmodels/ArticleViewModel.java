package com.lorispinna.news.viewmodels;

import android.util.Log;

import com.lorispinna.news.callback.NewsHTTPCallback;
import com.lorispinna.news.models.Article;
import com.lorispinna.news.models.ArticleList;
import com.lorispinna.news.utils.Api;
import com.lorispinna.news.utils.DatabaseListSingleton;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bolts.Continuation;
import bolts.Task;
import retrofit2.Response;

public class ArticleViewModel extends ViewModel {
    private MutableLiveData<List<Article>> articles;
    private MutableLiveData<Article> selectedArticle = new MutableLiveData<>();
    public LiveData<List<Article>> getArticles() {
        if(articles == null) {
            articles = new MutableLiveData<>();
            loadArticles();
        }
        return articles;
    }

    public void setSelectedArticle(MutableLiveData<Article> selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public MutableLiveData<Article> getSelectedArticle() {
        return selectedArticle;
    }

    private void loadArticles() {
        //Get From API
        getFromSql();

        Api.getNews("pokemon", new NewsHTTPCallback() {
            @Override
            public void call(Response<ArticleList> l)  {
                articles.postValue(l.body().getArticles());
                saveToSql(l.body().getArticles());
            }
        });
    }

    /**
     * get articles from SQL
     */
    private void getFromSql() {
        Log.d("SQL_DB", "beforeCallDb: ");

        Task.callInBackground(new Callable<Void>() {
            private static final String TAG = "SQL_DB";
            @Override
            public Void call() {
                List<Article> get = DatabaseListSingleton.getInstance().getArticleDao().getAll();
                Log.d(TAG, "callDb: " + get.size());

                articles.postValue(get);
                return null;
            }
        });
    }

    /**
     * save SQL to database in another thread
     * @param articles
     */
    private void saveToSql(final List<Article> articles) {
        Task.callInBackground(new Callable<Void>() {
            private static final String TAG = "SQL_DB";
            @Override
            public Void call() {
                Log.d(TAG, "call: " + articles.size());
                DatabaseListSingleton.getInstance().getArticleDao().insertAll(articles);
                return null;
            }
        }).continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) {
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }
}
