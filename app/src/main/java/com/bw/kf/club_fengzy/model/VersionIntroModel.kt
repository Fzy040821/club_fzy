package com.bw.kf.club_fengzy.model

import com.google.gson.annotations.SerializedName

/**
 *@author Wcj
 *@description
 *@date 2022/12/1 16:58
 */
data class VersionIntroModel(
    @SerializedName("verId")
    val id: String,
    @SerializedName("verTime")
    val updateTime: String,
    @SerializedName("verContent")
    val content: String,
    @SerializedName("verNum")
    val num: String,
    @SerializedName("verTitle")
    val title: String
)