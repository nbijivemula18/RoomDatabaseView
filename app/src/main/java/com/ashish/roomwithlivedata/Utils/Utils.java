package com.ashish.roomwithlivedata.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;

public class Utils {
    public static final String[] permissionALL = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static boolean PermissionCheck(Activity activity, Context context, String[] permissions, int requestCode) {
        try {
            ArrayList<String> requestPermission = new ArrayList<>();
            for (String permission : permissions) {
                int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED)
                    requestPermission.add(permission);
            }
            if (requestPermission.size() <= 0) return false;
            ActivityCompat.requestPermissions(activity, requestPermission.toArray(new String[0]), requestCode);
        } catch (Exception e) {
        }
        return true;
    }
}
