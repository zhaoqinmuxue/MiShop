package com.example.aoli_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Configurator {
    private static final HashMap<String,Object> AOLI_CONFIGS = new HashMap<>();

    private static List<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator(){
        AOLI_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    private static final class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<String,Object> getAoliConfigs(){
        return AOLI_CONFIGS;
    }

    public void configure(){
        initIcons();
        AOLI_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        AOLI_CONFIGS.put(ConfigType.API_HOST.name(),host);
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

    private void checkConfiguration(){
        final boolean isReady = (boolean)AOLI_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    final <T> T getConfiguration(ConfigType key){
        checkConfiguration();
        return (T)AOLI_CONFIGS.get(key);
    }
}
