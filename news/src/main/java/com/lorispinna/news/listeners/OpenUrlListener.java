package com.lorispinna.news.listeners;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.lorispinna.news.events.ClickEvent;

import androidx.fragment.app.FragmentActivity;

public class OpenUrlListener extends ClickEvent {
    String url;
    public OpenUrlListener(String url) {
        super(null);
        this.url = url;
    }

    @Override
    public void onClick(View v) {

        Uri path = Uri.parse(this.url);
        Intent intent = new Intent(Intent.ACTION_VIEW, path);
        v.getContext().startActivity(intent);
    }
}
