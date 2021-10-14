package com.example.mylocation.utils;

import android.widget.Toast;

import com.example.DemoApplication;

/**
 * @author: 小杨同志
 * @date: 2021/9/23
 */
public class MyToast {
    public static void showToast(String msg){
        Toast.makeText(DemoApplication.getContexts(), ""+msg, Toast.LENGTH_SHORT).show();
    }
}
