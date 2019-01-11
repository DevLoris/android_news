package com.lorispinna.test.listeners;

import android.content.Intent;
import android.view.View;

public class ShareListener implements View.OnClickListener {
    private String body;
    private String name;

    public ShareListener(String name, String body) {
        this.body = body;
        this.name = name;
    }

    @Override
    public void onClick(View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.name);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, this.body);
        v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}
