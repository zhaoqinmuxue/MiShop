package com.example.aoli_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Aoli {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getAoliConfigs();
    }
}
