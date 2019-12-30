package com.example.aoli_ec.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_core.util.PreferenceUtil;
import com.example.aoli_ec.R;
import com.example.aoli_ec.R2;
import com.example.aoli_ec.sign.SignUpDelegate;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherScrollDelegate extends AoliDelegate {

    @BindView(R2.id.banner_scroll_launcher)
    Banner banner_launcher_scroll;

    @OnClick(R2.id.btn_jump)
    void onClickJump(){
        PreferenceUtil.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.entry_normal,R.anim.exit_normal)
                .replace(R.id.delegate_container,new SignUpDelegate())
                .commit();
    }

    private static final List<Integer> INTEGERS = new ArrayList<>();

    static {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
    }

    private void initBanner(){
        banner_launcher_scroll.setImageLoader(new NativeImageLoader())
                .setImages(INTEGERS)
                .isAutoPlay(false)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    class NativeImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher_scroll;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }
}
