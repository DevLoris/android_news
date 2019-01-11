package com.lorispinna.news.utils;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static boolean hasPermission(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }
    public static boolean hasPermissionRefused(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
    public static void requestPermission(Activity activity, String permission, Integer key) {
        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                key);
    }
}
