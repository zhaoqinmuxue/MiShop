package com.example.aoli_ec.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_core.wechat.AoliWeChat;
import com.example.aoli_core.wechat.callbacks.IWeChatSignInCallback;
import com.example.aoli_ec.R;
import com.example.aoli_ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends AoliDelegate {

    @BindView(R2.id.edit_sign_in_name)
    TextInputEditText mName;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
            if (getActivity() != null && getActivity() instanceof ISignListener){
                ((ISignListener) getActivity()).onSignInSuccess();
            }
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLinkSignUp(){
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.entry_normal,R.anim.exit_normal)
                .replace(R.id.delegate_container,new SignUpDelegate())
                .commit();
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        AoliWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                Toast.makeText(getContext(),"登录成功",Toast.LENGTH_LONG).show();
            }
        }).signIn();
    }

    private boolean checkForm(){
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()){
            mName.setError("请输入用户名");
            isPass = false;
        }else {
            mName.setError(null);
        }
        if (password.isEmpty() || password.length() < 6){
            mPassword.setError("请设定最少6位数密码");
            isPass = false;
        }else {
            mPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
