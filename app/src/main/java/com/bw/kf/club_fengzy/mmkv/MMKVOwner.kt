package com.bw.kf.club_fengzy.mmkv

import com.tencent.mmkv.MMKV

interface MMKVOwner {
    val mmkv: MMKV
        get() = MMKV.defaultMMKV()

    companion object{
        private val defaultMMKV = MMKV.defaultMMKV()
    }

}