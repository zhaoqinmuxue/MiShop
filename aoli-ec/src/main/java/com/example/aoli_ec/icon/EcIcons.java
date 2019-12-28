package com.example.aoli_ec.icon;

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon {
    icon_vip('\ue8bf');

    private char character;

    EcIcons(char character){
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
