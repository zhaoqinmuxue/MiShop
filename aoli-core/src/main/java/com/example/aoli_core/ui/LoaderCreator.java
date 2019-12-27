package com.example.aoli_core.ui;

import android.content.Context;

import com.example.aoli_core.R;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.Map;
import java.util.WeakHashMap;

public class LoaderCreator {
    private static final Map<LoaderStyle, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context,LoaderStyle loaderStyle) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(loaderStyle) == null) {
            final Indicator indicator = getIndicator(loaderStyle);
            LOADING_MAP.put(loaderStyle, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(loaderStyle));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(LoaderStyle loaderStyle) {
        String name = loaderStyle.name();
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
