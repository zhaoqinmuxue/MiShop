package com.example.aoli_core.net;

import android.content.Context;

import com.example.aoli_core.net.callback.IError;
import com.example.aoli_core.net.callback.IFailure;
import com.example.aoli_core.net.callback.IRequest;
import com.example.aoli_core.net.callback.ISuccess;
import com.example.aoli_core.ui.AoliLoader;
import com.example.aoli_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private String mUrl;
    private WeakHashMap<String,Object> mParams;
    private File mFile;
    private String mDownload_dir;
    private String mExtension;
    private String mName;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url) {
        mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams = params;
        return this;
    }

    public final RestClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        mFile = new File(file);
        return this;
    }

    public final RestClientBuilder download_dir(String download_dir) {
        mDownload_dir = download_dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        mName = name;
        return this;
    }

    public final RestClientBuilder params(String key,Object value) {
        if (mParams == null){
            mParams = new WeakHashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public final RestClientBuilder row(String raw) {
        mBody = RequestBody.Companion.create
                (raw,MediaType.Companion.parse("application/json;charset=UTF-8"));
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        mContext = context;
        mLoaderStyle = AoliLoader.getDefaultLoaderStyle();
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mFile, mDownload_dir,mExtension,mName,
                mIRequest, mISuccess, mIFailure, mIError, mBody,mLoaderStyle,mContext);
    }
}
