package com.example.aoli_core.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

public class Configurator {

    private static final HashMap<ConfigType,Object> AOLI_CONFIGS = new HashMap<>();
    private static List<IconFontDescriptor> ICONS = new ArrayList<>();
    private static List<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        AOLI_CONFIGS.put(ConfigType.CONFIG_READY,false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    private static final class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<ConfigType,Object> getAoliConfigs(){
        return AOLI_CONFIGS;
    }

    public void configure(){
        initIcons();
        AOLI_CONFIGS.put(ConfigType.ICON,ICONS);
        AOLI_CONFIGS.put(ConfigType.CONFIG_READY,true);
    }

    public final Configurator withApiHost(String host){
        AOLI_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }

    private void initIcons(){
        if (ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1;i < ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        AOLI_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        AOLI_CONFIGS.put(ConfigType.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        AOLI_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET,appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        AOLI_CONFIGS.put(ConfigType.ACTIVITY,activity);
        return this;
    }

    public final Configurator withInterceptors(List<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        AOLI_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean)AOLI_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    final <T> T getConfiguration(ConfigType key){
        checkConfiguration();
        return (T)AOLI_CONFIGS.get(key.name());
    }
}
