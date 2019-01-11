package com.lorispinna.news.events;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentActivity;

public abstract class ClickEvent  implements View.OnClickListener{
    public FragmentActivity activity;
    public ClickEvent(FragmentActivity activity) {
        this.activity = activity;
    }
    @Override
    public abstract void onClick(View v);
}
