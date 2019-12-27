package com.example.aoli_core.net.callback;

import com.example.aoli_core.app.Aoli;
import com.example.aoli_core.ui.AoliLoader;
import com.example.aoli_core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        LOADERSTYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else if (ERROR != null){
            ERROR.onError(response.code(),response.message());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (LOADERSTYLE != null){
            AoliLoader.stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null){
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (LOADERSTYLE != null){
            AoliLoader.stopLoading();
        }
    }
}
