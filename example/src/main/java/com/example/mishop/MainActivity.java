package com.example.mishop;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.aoli_core.activities.ProxyActivity;
import com.example.aoli_core.delegates.AoliDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public AoliDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
