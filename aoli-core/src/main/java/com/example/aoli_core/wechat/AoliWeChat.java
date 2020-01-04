package com.example.aoli_core.wechat;

import android.app.Activity;

import com.example.aoli_core.app.Aoli;
import com.example.aoli_core.app.ConfigType;
import com.example.aoli_core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class AoliWeChat {
    static final String APP_ID = Aoli.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    static final String APP_SECRET = Aoli.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback;

    private static final class Holder{
        private static final AoliWeChat INSTANCE = new AoliWeChat();
    }

    public static AoliWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private AoliWeChat(){
        final Activity activity = Aoli.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public AoliWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
