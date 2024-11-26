package com.bw.kf.club_fengzy.dialog

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.bw.kf.club_fengzy.R

/**
 * Created by ZengCS on 2022/7/28.
 * E-mail:zengcs@vip.qq.com
 * Add:中国成都
 * Desc: 自定义弹出基类
 */
open class BaseCustomDialog {
    open fun cancelAble() = false

    fun showCustomDialog(context: Context, customView: View): AlertDialog? {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogWithDim)
        builder.setView(customView).setCancelable(cancelAble())
        return builder.show()
    }

    fun dismissDialog(alertDialog: AlertDialog?) {
        try {
            if (alertDialog != null && alertDialog.isShowing) alertDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}