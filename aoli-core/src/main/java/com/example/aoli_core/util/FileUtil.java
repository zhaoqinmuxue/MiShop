package com.example.aoli_core.util;

import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        FileOutputStream fout = null;
        try {
            bin = new BufferedInputStream(in);
            fout = new FileOutputStream(file);
            bout = new BufferedOutputStream(fout);
            byte data[] = new byte[1024 * 4];
            int count;
            while ((count = bin.read(data)) != -1){
                bout.write(data,0,count);
            }
            bout.flush();
            fout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bin != null){
                    bin.close();
                }
                if (bout != null){
                    bout.close();
                }
                if (fout != null){
                    fout.close();
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
}
