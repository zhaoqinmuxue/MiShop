package com.example.aoli_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.aoli_core.app.Aoli;

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Aoli.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Aoli.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
