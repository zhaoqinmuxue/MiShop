package com.example.aoli_core.net.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

public abstract class BaseInterceptor implements Interceptor {

    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();
        final LinkedHashMap<String,String> urlParams = new LinkedHashMap<>();
        for (int i = 0;i < url.querySize();i++){
            urlParams.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return urlParams;
    }

    protected String getUrlParameter(Chain chain,String key){
        return chain.request().url().queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> bodyParams = new LinkedHashMap<>();
        int size = 0;
        if (formBody != null){
            size = formBody.size();
        }
        for (int i = 0;i < size;i++){
            bodyParams.put(formBody.name(i),formBody.value(i));
        }
        return bodyParams;
    }

    protected String getBodyParameter(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }
}
