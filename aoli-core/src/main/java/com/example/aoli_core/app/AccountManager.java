package com.example.aoli_core.app;

import com.example.aoli_core.util.PreferenceUtil;

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    public static void setSignState(Boolean state){
        PreferenceUtil.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return PreferenceUtil.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker userChecker){
        if (isSignIn()){
            userChecker.onSignIn();
        }else {
            userChecker.onNotSignIn();
        }
    }
}
