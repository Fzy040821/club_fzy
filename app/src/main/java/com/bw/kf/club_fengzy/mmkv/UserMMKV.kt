package com.bw.kf.club_fengzy.mmkv


import com.tencent.mmkv.MMKV

/**
 *@author Wcj
 *@description 存储用户信息
 *@date 2022/6/27 14:56
 */
object UserMMKV : MMKVOwner {

    override val mmkv: MMKV
        get() = MMKV.mmkvWithID(MMKVConfig.ID.USER)



    //是否绑定车辆
    var hasBindVehicle by MMKVProperty(false)
    var hasBindDevice by MMKVProperty(false)

    //保存当前车辆


    //自动播放视频
    var autoPlayVideo by MMKVProperty(true)

    var ifPassword by MMKVProperty(false)

    var focusNumberCache by MMKVProperty(0)




}