package com.shengmingji.custorview;

import android.graphics.Bitmap;



public class DoubleCache implements ImageCache{
    private MemoryCache mMemoryCache = new MemoryCache();
    private DiskCache mDisCache = new DiskCache();

    /**
     * 从缓存获取，没有就从SD卡获取
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDisCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap){
        mMemoryCache.put(url, bitmap);
        mDisCache.put(url, bitmap);
    }
}
