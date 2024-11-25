package com.bw.kf.club_fengzy.util;

import android.text.TextUtils;
import android.util.Log;

public class JLogUtil {
    public static final String TAG="JCZY/D";
    public static final String TAG_STEP = "Debug/Step";
    public static final String TAG_DOWNLOAD = "Debug/Download";

    public static void logDownload(String log){
        i(TAG_DOWNLOAD , log);
    }

    public static void e(String tag , String msg){
        if(TextUtils.isEmpty(msg)) return;
        Log.e(tag , msg);
    }

    public static void e(Throwable e){

    }

    public static void e(String tag , String msg , Throwable e){
        Log.e(tag,msg,e);
    }

    public static void w(String tag,String msg){
        Log.w(tag,msg);
    }

    public static void i(String tag , String message){

    }

    public static void d(String tag , String msg){

    }

    public static void http(String msg){
        String tag = "okHttp";
    }

    public static void v( String tag , String msg){

    }

    //无TAG方法
    public static void e(String msg){
        e(TAG , msg);
    }

    public static void e(String msg ,Throwable e){
        e(TAG , msg , e);
    }

    public static void w(String msg){
        w(TAG,msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void methodStart(String msg){
        w(TAG , "****************** Start" + msg + "*****************");
    }

    public static void methodStep(String msg) {
        w(TAG, "* --> " + msg);
    }

    public static void methodStepWithTime(String msg) {
        w(TAG_STEP, "[" + System.nanoTime() + "] --> " + msg);
    }

    public static void methodStepError(String msg) {
        e(TAG, "* !!! " + msg);
    }

    public static void methodEnd(String msg) {
        w(TAG, "******************** End " + msg + " ********************");
    }

    public static void onReceiveEvent(String msg) {
        w(TAG, "【EventBus-Receive】" + msg);
    }

    public static void postEvent(String msg) {
        w(TAG, "【EventBus-Post】" + msg);
    }
}
