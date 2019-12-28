package com.example.aoli_ec.launcher;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_core.util.PreferenceUtil;
import com.example.aoli_core.util.timer.BaseTimerTask;
import com.example.aoli_core.util.timer.ITimeListener;
import com.example.aoli_ec.R;
import com.example.aoli_ec.R2;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends AoliDelegate implements ITimeListener {

    private int mCount = 3;

    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        mTimer.cancel();
        checkIsShowScroll();
    }

    private Timer mTimer;

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask,new Date(),1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                mCount--;
                if (mCount < 0) {
                    mTimer.cancel();
                    checkIsShowScroll();
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTimer.cancel();
    }

    private void checkIsShowScroll(){
        if (PreferenceUtil.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()) == false){
            getFragmentManager().beginTransaction()
                    .replace(R.id.delegate_container,new LauncherScrollDelegate())
                    .commit();
        }else{

        }
    }
}
