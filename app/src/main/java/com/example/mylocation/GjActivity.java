package com.example.mylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.MapView;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;

import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.example.DemoApplication;

public class GjActivity extends AppCompatActivity {

    private MapView mapView;
    private Button bt_gjK;
    private Button bt_gjG;
    private OnTraceListener mTraceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gj);
        findView();
        initData();
    }

    private void initData() {
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);
        // 初始化轨迹服务监听器
        mTraceListener = new OnTraceListener() {
            @Override
            public void onBindServiceCallback(int i, String s) {
                Log.i("杨路通", "i: "+i+"------s"+s);
            }

            // 开启服务回调
            @Override
            public void onStartTraceCallback(int status, String message) {
                Log.i("杨路通", "开启服务回调:   status: "+status+"----      --message"+message);
            }
            // 停止服务回调
            @Override
            public void onStopTraceCallback(int status, String message) {
                Log.i("杨路通", "停止服务回调:   status: "+status+"------message"+message);
            }
            // 开启采集回调
            @Override
            public void onStartGatherCallback(int status, String message) {
                Log.i("杨路通", "开启采集回调:   status: "+status+"------message"+message);
            }
            // 停止采集回调
            @Override
            public void onStopGatherCallback(int status, String message) {
                Log.i("杨路通", "停止采集回调:   status: "+status+"------message"+message);
            }
            // 推送回调
            @Override
            public void onPushCallback(byte messageNo, PushMessage message) {
                Log.i("杨路通", "推送回调:   messageNo: "+messageNo+"------message"+message);
            }

            @Override
            public void onInitBOSCallback(int i, String s) {
                Log.i("杨路通", "i: "+i+"------s"+s);
            }
        };

        bt_gjK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 开启服务
                mTraceClient.startTrace(mTrace, mTraceListener);
                // 开启采集
                mTraceClient.startGather(mTraceListener);
            }
        });
    }

    private void findView() {
        bt_gjG = findViewById(R.id.bt_gjG);
        bt_gjK = findViewById(R.id.bt_gjK);
        mapView = findViewById(R.id.mapView);
    }

    // 轨迹服务ID
    long serviceId = 229745;
    // 设备标识
    String entityName = "myTrace";
    // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
    boolean isNeedObjectStorage = false;
    // 初始化轨迹服务
    Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
    // 初始化轨迹服务客户端
    LBSTraceClient mTraceClient = new LBSTraceClient(DemoApplication.getContexts());
    // 定位周期(单位:秒)
    int gatherInterval = 5;
    // 打包回传周期(单位:秒)
    int packInterval = 10;

}