package com.example.mishop;

import android.app.Application;
import com.example.aoli_core.app.Aoli;
import com.example.aoli_ec.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Aoli.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://www.baidu.com")
                .configure();
    }
}
