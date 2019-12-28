package com.example.aoli_core.util.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {
    private ITimeListener mITimeListener;

    public BaseTimerTask(ITimeListener iTimeListener){
        mITimeListener = iTimeListener;
    }

    @Override
    public void run() {
        if (mITimeListener != null){
            mITimeListener.onTimer();
        }
    }
}
