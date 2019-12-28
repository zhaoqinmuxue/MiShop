package com.example.mishop;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.example.aoli_core.activities.ProxyActivity;
import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_ec.launcher.LauncherDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public AoliDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
