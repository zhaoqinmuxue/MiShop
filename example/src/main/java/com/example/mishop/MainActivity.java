package com.example.mishop;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.aoli_core.activities.ProxyActivity;
import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_ec.launcher.ILauncherListener;
import com.example.aoli_ec.launcher.IsSignedTag;
import com.example.aoli_ec.launcher.LauncherDelegate;
import com.example.aoli_ec.sign.ISignListener;
import com.example.aoli_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ILauncherListener, ISignListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public AoliDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onLauncherFinished(IsSignedTag tag) {
        switch (tag){
            case SIGNED:
                break;
            case NOT_SIGNED:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.delegate_container,new SignUpDelegate())
                        .commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {

    }
}
