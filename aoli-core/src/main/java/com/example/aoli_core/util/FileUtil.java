package com.example.aoli_core.util;

import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.example.aoli_core.app.Aoli;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";

    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();

    private static String getTimeFormatName(String timeFormatHeader){
        final Date date = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat( timeFormatHeader + TIME_FORMAT);
        return dateFormat.format(date);
    }

    private static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    private static File createDir(String sdcardDirName){
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        final File file = new File(dir);
        if (!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    private static File createFile(String sdcardDirName,String fileName){
        return new File(createDir(sdcardDirName),fileName);
    }

    public static String getExtension(String filePath){
        String suffix = "";
        final int index = filePath.lastIndexOf('.');
        if (index > 0){
            suffix = filePath.substring(index + 1);
        }
        return suffix;
    }

    public static String getMimeType(String filePath){
        final String extension = getExtension(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    public static File writeToDisk(InputStream in,String dir,String name){
        final File file = createFile(dir,name);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            bis = new BufferedInputStream(in);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte data[] = new byte[1024 * 4];
            int count;
            while ((count = bis.read(data)) != -1){
                bos.write(data,0,count);
            }
            bos.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bis != null){
                    bis.close();
                }
                if (bos != null){
                    bos.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File writeToDisk(InputStream in,String dir,String prefix,String extension){
        return writeToDisk(in,dir,getFileNameByTime(prefix,extension));
    }

    public static String getRawFile(int id) {
        final InputStream is = Aoli.getApplicationContext().getResources().openRawResource(id);
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder stringBuilder = new StringBuilder();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
