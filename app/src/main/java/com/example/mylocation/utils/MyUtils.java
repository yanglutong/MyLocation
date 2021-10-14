package com.example.mylocation.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mylocation.MainActivity;
import com.example.mylocation.basequery.Bean.NumberBean;
import com.example.mylocation.basequery.Retrofit.RetrofitFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MyUtils {
    private static Context context;
    private static boolean typeAppup = false;//是否强制更新

    public static void getPermissions(MainActivity mainActivity) {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mainActivity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
//            Toast.makeText(LoginActivity.this,"已经授权",Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(mainActivity, permissions, MY_PERMISSIONS_REQUEST_CALL_CAMERA);
        }
    }

    public MyUtils(Context context) {
        this.context = context;
    }

    //权限
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_CAMERA = 2;
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    public static List<String> mPermissionList = new ArrayList<>();
    public static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.GET_TASKS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
    };//申请的权限

    /**
     * 申请权限
     */

    @SuppressLint("NewApi")
    public static void setStatBar(MainActivity mainActivity) {//根据版本设置沉浸式状态栏
        View decorView = mainActivity.getWindow().getDecorView();
        int option =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        mainActivity.getWindow().setStatusBarColor(Color.TRANSPARENT
        );
    }
    //集合转String
    public static String listToString(List<Double> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Double string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public static List<Double> StringTolist(String str) {

        List list = Arrays.asList(str.split(","));
        List<Double> integerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
//            Integer.parseInt((String) list.get(i));
//            integerList.add(Integer.parseInt((String) list.get(i)));
            integerList.add(Double.parseDouble((String) list.get(i)));
        }
        return integerList;
    }
    //查询次数
    public static void getNumber(String appId) {
        RetrofitFactory.getInstence().API().NUMBER(appId).enqueue(new Callback<NumberBean>() {
            @Override
            public void onResponse(Call<NumberBean> call, Response<NumberBean> response) {
                if (response.body().getData() != null) {
                    ACacheUtil.putNumberMax(response.body().getData().getTotal() + "");//一共查询次数
                    ACacheUtil.putNumberremainder(response.body().getData().getRemainder() + "");
                    Log.d(TAG, "getNumberonResponse: " + "最多" + ACacheUtil.getNumberMax() + "剩余:" + ACacheUtil.getNumberremainder());
                }
            }

            @Override
            public void onFailure(Call<NumberBean> call, Throwable t) {

            }
        });
    }
    /**获取当前卡槽数量
     * @description
     * @param
     * @return
     * @author lutong
     * @time 2021/9/26 16:14
     */

    public static int  readSimState(Context context){
        int  s=0;
        SubscriptionManager mSubscriptionManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            mSubscriptionManager = SubscriptionManager.from(context);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            }else{
                s = mSubscriptionManager.getActiveSubscriptionInfoCount();//获取当前sim卡数量
                Log.i("ylt", "onCreate: "+s);
            }

        }
        Log.e("YangLuTong", "readSimState: "+s);
        return s;
    }
}
