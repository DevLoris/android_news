package com.lorispinna.news.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.lorispinna.news.R;
import com.lorispinna.news.databases.ArticleDatabase;
import com.lorispinna.news.fragments.NewsListFragment;
import com.lorispinna.news.utils.DatabaseListSingleton;
import com.lorispinna.news.utils.FragmentHelper;

import java.util.concurrent.Callable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;

public class MainActivity extends AppCompatActivity {
    private boolean isNightModeEnabled = false;
    private final String night_key = "NIGHT_KEY";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loading night mode
        this.preferences =  PreferenceManager.getDefaultSharedPreferences(this);
        this.isNightModeEnabled = preferences.getBoolean(night_key, false);
        setTheme((this.isNightModeEnabled) ? R.style.NightMode: R.style.DayMode);

        Task.callInBackground(new Callable<ArticleDatabase>() {
            @Override
            public ArticleDatabase call() {
                final ArticleDatabase db = Room.databaseBuilder(getApplicationContext(), ArticleDatabase.class, "article-db")
                        .fallbackToDestructiveMigration()
                        .build();
                DatabaseListSingleton.getInstance().setArticleDatabase(db);
                return db;
            }
        }).continueWith(new Continuation<ArticleDatabase, ArticleDatabase>() {
            @Override
            public ArticleDatabase then(final Task<ArticleDatabase> task) {
                FragmentHelper.replace(MainActivity.this, R.id.app_content, new NewsListFragment());
                return task.getResult();
            }
        });
    }


    /**
     * Get click to enable or disable night mode
     * @param v View
     */
    public void clickNightHandler(View v) {
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putBoolean(night_key, !this.isNightModeEnabled);
        editor.apply();

        this.isNightModeEnabled = !this.isNightModeEnabled;

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

        this.recreate();
    }
}
