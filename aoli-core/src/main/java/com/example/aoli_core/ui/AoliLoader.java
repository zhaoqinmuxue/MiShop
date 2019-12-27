package com.example.aoli_core.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.aoli_core.R;
import com.example.aoli_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class AoliLoader {

    private static final int LOADER_SIZE_SCALE = 8;

    private static ArrayList<Dialog> LOADERS = new ArrayList<>();

    private static LoaderStyle DEFAULT_LOADERSTYLE = LoaderStyle.BallSpinFadeLoaderIndicator;

    public static void showLoading(Context context, LoaderStyle loaderStyle){
        final Dialog dialog = new Dialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context,loaderStyle);
        dialog.setContentView(avLoadingIndicatorView);
        dialog.setCanceledOnTouchOutside(false);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height = deviceHeight/LOADER_SIZE_SCALE;
            lp.gravity = Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context,DEFAULT_LOADERSTYLE);
    }

    public static void stopLoading(){
        for (Dialog dialog : LOADERS){
            dialog.cancel();
        }
    }

    public static LoaderStyle getDefaultLoaderStyle(){
        return DEFAULT_LOADERSTYLE;
    }
}
