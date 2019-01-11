package com.lorispinna.test.listeners;

import android.Manifest;
import android.app.Activity;
import android.view.View;

import com.lorispinna.test.PermissionUtils;

public class PhoneListener implements View.OnClickListener {
    public static Integer CALL_PHONE_KEY = 2049;

    public PhoneListener() {
    }

    @Override
    public void onClick(View v) {

        if (!PermissionUtils.hasPermission((Activity) v.getContext(), Manifest.permission.CALL_PHONE)) {
            PermissionUtils.requestPermission((Activity) v.getContext(), Manifest.permission.CALL_PHONE, CALL_PHONE_KEY);
        }

    }
}
