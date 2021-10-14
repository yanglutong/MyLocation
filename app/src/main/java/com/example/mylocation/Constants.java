package com.example.mylocation;

/**
 * @author: 小杨同志
 * @date: 2021/9/23
 */
public class Constants {
    public static boolean MAXTA = false;//最大ta圈
    public static boolean MINTA = false;//最小ta圈
    public static boolean UNIFORMTA = true;//平均圈
    public static boolean FIRSTTYPE = false;//判断是否刚进入界面判断


    /**
     * 作者：南志强 2016/11/14 14:39
     * 作用：配置各个页面联网地址
     */
    public static int TIMEOUT=10;

    public static String BASE_URL = "http://192.168.10.204:1235";

    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL+"/json/HOME_URL.json";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/img";

    public static boolean isCell=true;//是邻小区或历史记录
    public static boolean isRuing=true;//是邻小区或历史记录
}
