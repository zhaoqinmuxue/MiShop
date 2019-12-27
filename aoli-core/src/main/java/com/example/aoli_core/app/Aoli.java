package com.example.aoli_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Aoli {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<ConfigType,Object> getConfigurations(){
        return Configurator.getInstance().getAoliConfigs();
    }

    public static <T> T getConfiguration(ConfigType type){
        return (T)getConfigurations().get(type);
    }

    public static Context getApplicationContext(){
        return (Context) getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
}
