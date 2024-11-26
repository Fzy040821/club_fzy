package com.bw.kf.club_fengzy.model

/**
 *@author Wcj
 *@description
 *@date 2022/6/27 16:17
 */
class UpdateModel(
    var latestVersion: String,
    var downloadUrl: String?,
    var forceUpGrade: Int,//1.强制升级，2非强制升级
    var message: String,
    var appLevelId: Int,
    var platform: String,
    var createTime: String?,
    var silence: Boolean = false
) {
    fun requiredVersionName() = "版本号：V${latestVersion}"
    fun requiredPublishTime() = "发布时间：${createTime ?: "未知"}"
    fun ifForce() = forceUpGrade == 1
}