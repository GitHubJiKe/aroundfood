package com.ypf.aroundfood;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * Created by Administrator on 2015/11/9.
 */
public class TApplication extends Application {
    @Override
    public void onCreate() {
        JuheSDKInitializer.initialize(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
    }
}
