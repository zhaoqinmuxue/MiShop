package com.example.aoli_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.aoli_core.app.Aoli;
import com.example.aoli_core.net.callback.IRequest;
import com.example.aoli_core.net.callback.ISuccess;
import com.example.aoli_core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String download_dir = (String)objects[0];
        String extension = (String)objects[1];
        String name = (String)objects[2];
        final ResponseBody body = (ResponseBody)objects[3];
        final InputStream in = body.byteStream();
        if (download_dir == null || download_dir.isEmpty()){
            download_dir = "down_loads";
        }
        if (extension == null || extension.isEmpty()){
            extension = "";
        }
        if (name == null){
            FileUtil.writeToDisk(in,download_dir,extension.toUpperCase(),extension);
        }else{
            FileUtil.writeToDisk(in,download_dir,name);
        }
        return null;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Aoli.getApplicationContext().startActivity(install);
        }
    }
}
