package com.example;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import androidx.multidex.MultiDex;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.mylocation.service.LocationService;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;

public class DemoApplication extends Application {
    public static LocationService locationService;
    public Vibrator mVibrator;
    private static Context contexts;
    public BMapManager mBMapManager = null;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Toast
        ToastUtils.init(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        // 初始化MultiDex
        MultiDex.install(this);
        contexts=this;
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext   
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
    public static Context getContexts(){
        return contexts;
    }
    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    public static class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetPermissionState(int iError) {
            // 非零值表示key验证未通过
            if (iError != 0) {
                // 授权Key错误：
//                Toast.makeText(AppLication.getInstance().getApplicationContext(),
//                        "请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: " + iError, Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(AppLication.getInstance().getApplicationContext(), "key认证成功", Toast.LENGTH_LONG)
//                        .show();
            }
        }
    }
}