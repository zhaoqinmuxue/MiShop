package com.example.aoli_ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.aoli_core.delegates.AoliDelegate;
import com.example.aoli_ec.R;
import com.example.aoli_ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends AoliDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;

    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;

    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;

    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;

    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){
            /*
            RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();

             */
            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
            if (getActivity() != null && getActivity() instanceof ISignListener){
                ((ISignListener) getActivity()).onSignUpSuccess();
            }
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinkSignIn(){
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.entry_normal,R.anim.exit_normal)
                .replace(R.id.delegate_container,new SignInDelegate())
                .commit();
    }

    private boolean checkForm(){
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String password = mPassword.getText().toString();
        String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()){
            mName.setError("请输入用户名");
            isPass = false;
        }else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("邮箱格式错误");
            isPass = false;
        }else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || password.length() < 6){
            mPassword.setError("请设定最少6位数密码");
            isPass = false;
        }else {
            mPassword.setError(null);
        }
        if (rePassword.isEmpty() || rePassword.length() < 6 || rePassword != password){
            mRePassword.setError("密码验证错误");
            isPass = false;
        }else {
            mRePassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
