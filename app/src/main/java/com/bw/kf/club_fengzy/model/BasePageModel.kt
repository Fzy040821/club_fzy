package com.bw.kf.club_fengzy.model

import com.google.gson.annotations.SerializedName

/**
 *@author Wcj
 *@description
 *@date 2022/8/27 10:42
 */
data class BasePageModel<T>(
    val current: Int,
    val pages: Int,
    @SerializedName("records")
    val items: MutableList<T>,
    val size: Int,
    val total: Int
)