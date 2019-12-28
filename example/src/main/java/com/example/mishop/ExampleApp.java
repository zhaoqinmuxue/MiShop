package com.example.mishop;

import android.app.Application;
import com.example.aoli_core.app.Aoli;
import com.example.aoli_core.net.interceptors.DebugInterceptor;
import com.example.aoli_ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Aoli.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
