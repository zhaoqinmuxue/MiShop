package com.example.aoli_core.wechat.template;

import com.example.aoli_core.wechat.AoliWeChat;
import com.example.aoli_core.wechat.BaseWXEntryActivity;

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String useInfo) {
        AoliWeChat.getInstance().getSignInCallback().onSignInSuccess(useInfo);
    }
}
