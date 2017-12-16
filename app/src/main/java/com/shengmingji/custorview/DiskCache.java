package com.shengmingji.custorview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskCache implements ImageCache{
    static String cacheDir ="sdcard/cache";
    /**
     * 从缓存中获取图片
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    @Override
    public void put(String url,Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(cacheDir));
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
