package com.bw.kf.club_fengzy.util;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
    private static Toast mToast;

    private static void cancel() {
        // 新的Toast进来的时候 取消上次的Toast
        if (mToast != null) {
            mToast.cancel();
        }
    }

    private static void showToast(Context mContext, String text, int duration) {
        cancel();// 新的Toast进来的时候 取消上次的Toast
        int height = mContext.getResources().getDisplayMetrics().heightPixels;
        mToast = Toast.makeText(mContext, text, duration);
        //  mToast.setGravity(Gravity.CENTER, 0, height / 6);
        mToast.show();
    }

    private static void showToast(Context mContext, int resId, int duration) {
        cancel();// 新的Toast进来的时候 取消上次的Toast
        int height = mContext.getResources().getDisplayMetrics().heightPixels;
        mToast = Toast.makeText(mContext, resId, duration);
        mToast.setGravity(Gravity.TOP, 0, height / 6);
        mToast.show();
    }

    public static void showLong(final Context activity, final String message) {
        if (message.isEmpty()) return;
        if (activity != null && !TextUtils.isEmpty(message))
            showToast(activity.getApplicationContext(), message, LENGTH_LONG);
    }

    public static void showShort(final Context activity, final String message) {
        if (message.isEmpty()) return;
        if (activity != null && !TextUtils.isEmpty(message))
            showToast(activity.getApplicationContext(), message, LENGTH_SHORT);
    }

    public static void showShort(final Context activity, final int msgRes) {
        showToast(activity, msgRes, LENGTH_SHORT);
    }

    public static void showLong(final Context activity, final int msgRes) {
        showToast(activity, msgRes, LENGTH_LONG);
    }
}