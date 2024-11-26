package com.bw.kf.club_fengzy.util


/**
 * Created by ZengCS on 2022/7/28.
 * E-mail:zengcs@vip.qq.com
 * Add:中国成都
 * Desc: 防止快速连点
 */
object OnClickHelper {
    private var lastClickTime = 0L

    fun canClick(): Boolean {
        val gap = System.currentTimeMillis() - lastClickTime
        if (gap > 500) {
            lastClickTime = System.currentTimeMillis()
            return true
        }
        JLogUtil.logDownload("Forbidden fast click")
        return false
    }
}