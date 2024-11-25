package com.bw.kf.club_fengzy.base

data class BaseModel<T>(
    val code: Int,
    val msg:String,
    val data:T,
    val errorCode: String?=null
)