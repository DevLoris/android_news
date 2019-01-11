package com.a73.hugo.duval.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.a73.hugo.duval.calculator.R;

import java.util.Locale;

class CommonActivity extends Activity {
    private boolean isNightModeEnabled = false;
    private final String night_key = "NIGHT_KEY";
    private String lang = "fr";
    private final String lang_key = "LANG_KEY";
    private SharedPreferences preferences;

    /**
     * Set locale of application
     * @param lang
     * @param recreate
     */
    public void setLocale(String lang, Boolean recreate) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        if(recreate)
            this.recreate();
    }

    public void saveAndSetLocale(String lang){
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putString(lang_key, lang);
        editor.apply();
        this.lang = lang;
        this.setLocale(lang, true);
    }

    /**
     * Get preferences of user
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.preferences =  PreferenceManager.getDefaultSharedPreferences(this);
        this.isNightModeEnabled = preferences.getBoolean(night_key, false);
        this.lang = preferences.getString(lang_key, "fr");

        setLocale(this.lang, false);
        setTheme((this.isNightModeEnabled) ? R.style.WinterMode : R.style.SummerMode);

        super.onCreate(savedInstanceState);
    }

    /**
     * Assign menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.scientific, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Test for menu options
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_history:
                if(this instanceof MainActivity) {
                    Intent intent = new Intent(this, HistoryActivity.class);
                    startActivity(intent);
                }
                return true;
            case R.id.action_simple:
                if(this instanceof HistoryActivity) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        this.recreate();
    }

    /**
     * Get click to open Android Native Options Menu
     * @param v
     */
    public void clickMenuHandler(View v){
        this.openOptionsMenu();
    }
}
