package com.bw.kf.club_fengzy.mmkv

import com.tencent.mmkv.MMKV

object AppMMKV: MMKVOwner {

    override val mmkv: MMKV
        get() = MMKV.mmkvWithID(MMKVConfig.ID.APP)

    //用户隐私协议
    var splashAgreement by MMKVProperty(false)
    //app引导页
    var appGuide by MMKVProperty(true)
    var lastVersionCode by MMKVProperty(0L)
    var lastLaunchTime by MMKVProperty(0L)



}