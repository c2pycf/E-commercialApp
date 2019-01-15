package com.example.fang.walmartproject;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static int SIGN_FLAG = 0;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private  static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if(mImageLoader == null){
            //Using default cache with size 10 implement customize cache later.
            mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap>mCache = new LruCache<>(10);
                @Override
                public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url,bitmap);

                }
            });
        }
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request request, String tag){
        request.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }

    public int getSignFlag(){
        return SIGN_FLAG;
    }

    public void setSignFlag(){
        SIGN_FLAG = 1;
    }

    public void unSetSignFlag(){ SIGN_FLAG = 0;}
}
