package com.example.aoli_core.net;

import android.content.Context;


import com.example.aoli_core.net.callback.IError;
import com.example.aoli_core.net.callback.IFailure;
import com.example.aoli_core.net.callback.IRequest;
import com.example.aoli_core.net.callback.ISuccess;
import com.example.aoli_core.net.callback.RequestCallBacks;
import com.example.aoli_core.net.download.DownloadHandler;
import com.example.aoli_core.ui.AoliLoader;
import com.example.aoli_core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private final String URL;
    private final WeakHashMap<String,Object> PARAMS;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADERSTYLE;
    private final Context CONTEXT;

    RestClient(String url,
              WeakHashMap<String,Object> params,
              File file,
              String download_dir,
              String extension,
              String name,
              IRequest request,
              ISuccess success,
              IFailure failure,
              IError error,
              RequestBody body,
              LoaderStyle loaderStyle,
              Context context) {
        URL = url;
        PARAMS = params;
        FILE = file;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        BODY = body;
        LOADERSTYLE = loaderStyle;
        CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    public void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null){
            AoliLoader.showLoading(CONTEXT,LOADERSTYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.Companion.create
                        (FILE, MediaType.Companion.parse(MultipartBody.FORM.toString()));
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = service.upload(URL,body);
                break;
            default:
                break;
        }
        if (call != null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADERSTYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("Params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("Params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownloadHandler(URL, PARAMS,  REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }
}
