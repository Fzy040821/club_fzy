package com.bw.kf.club_fengzy.model

import com.google.gson.annotations.SerializedName

/**
 *@author Wcj
 *@description
 *@date 2022/7/12 9:48
 */

data class PostPageModel(
    val current: Int,
    val pages: Int,
    @SerializedName("records")
    val posts: List<PostModel>,
    val size: Int,
    val total: Int
)

//data class PostCommentAboutMePageModel(
//    val current: Int,
//    val pages: Int,
//    @SerializedName("records")
//    val records: List<PostCommentsAboutMeModel>,
//    val size: Int,
//    val total: Int
//)