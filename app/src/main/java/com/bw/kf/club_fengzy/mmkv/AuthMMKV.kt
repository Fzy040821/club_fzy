package com.bw.kf.club_fengzy.mmkv

import com.tencent.mmkv.MMKV

object AuthMMKV : MMKVOwner{
    override val mmkv: MMKV
        get() = MMKV.mmkvWithID(MMKVConfig.ID.AUTH)

    //token
    var accessToken by MMKVProperty("")
    fun hasLogin() = accessToken.isNotEmpty()


}