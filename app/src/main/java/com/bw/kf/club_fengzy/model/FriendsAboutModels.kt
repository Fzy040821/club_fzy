package com.bw.kf.club_fengzy.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.mmkv.UserMMKV


/** enum */
/** 好友关系(0:无关系 1:粉丝 2:已关注 3:相互关注 4:我自己) */
enum class UserRelationEnum(val relation: Int, val typeName: String) {
    NONE(0, "无关系"),
    FANS(1, "粉丝"),
    FOCUS(2, "已关注"),
    FRIENDS(3, "互相关注"),
    MYSELF(4, "我自己"),
}

enum class CollectionTabEnum(val type: Int, val typeName: String) {
    POST(0, "帖子"),
    INFORMATION(1, "资讯"),
    DYNAMIC(2, "动态"),
    ROUTE(3, "路线"),
    MALL(4, "商品")
}

enum class FriendsTabEnum(val state: String, val typeName: String, val relation: Int) {
    FRIENDS("friends", "朋友", 3),
    FANS("fans", "粉丝", 1),
    FOLLOWED("followed", "关注", 2)
}

/**
 * 类型(1:帖子 2:文章 3:指南 4:话题 5:动态: 6:资讯 7:路线 8:组队 9:商品)
 */
enum class CollectionTypeEnum(val type: Int, typeName: String) {
    POST(1, "帖子"),
    ARTICLE(2, "文章"),
    GUIDE(3, "指南"),
    TOPIC(4, "话题"),
    DYNAMIC(5, "动态"),
    INFORMATION(6, "资讯"),
    ROUTE(7, "路线"),
    TEAM(8, "组队"),
    GOODS(9, "商品")
}

/** data class */
/** 朋友/关注/粉丝 */
data class FriendsItem(
    val memberId: String? = null,
    val cellphone: String? = null,
    val headImg: Image? = null,
    val nickName: String? = null,
    val pinyinNickName: String? = null,
    val realName: String? = null,
    val hobby: String? = null,
    val gender: Int? = null,// 性别：0-女，1男，2未知
    // 好友关系
    var relation: Int = 0,// (0:无关系 1:粉丝 2:已关注 3:相互关注)
    var checked: Boolean = false,
    var isAdded: Boolean = false,
) {
    fun findAvatar(): String {
        return headImg?.url ?: ""
    }

    fun hasFollowed(): Boolean {
        return relation in listOf(
            UserRelationEnum.FOCUS.relation,
            UserRelationEnum.FRIENDS.relation
        )
    }

    fun canFollow(): Boolean {
        return relation in listOf(UserRelationEnum.NONE.relation, UserRelationEnum.FANS.relation)
    }

    fun showAddIcon(): Boolean {
        return relation <= UserRelationEnum.FANS.relation
    }

    fun showMutualIcon(): Boolean {
        return relation == UserRelationEnum.FRIENDS.relation
    }

    /** 添加关注 */
    fun onFollow() {
        when (relation) {
            // 没有关系 --> 已关注
            UserRelationEnum.NONE.relation -> {
                relation = UserRelationEnum.FOCUS.relation
            }
            // 粉丝 --> 互相关注
            UserRelationEnum.FANS.relation -> {
                relation = UserRelationEnum.FRIENDS.relation
            }
        }
        UserMMKV.focusNumberCache = UserMMKV.focusNumberCache + 1
    }

    /** 取消关注 */
    fun onCancelFollow() {
        when (relation) {
            // 已关注 --> 没有关系
            UserRelationEnum.FOCUS.relation -> {
                relation = UserRelationEnum.NONE.relation
            }
            // 互相关注 --> 粉丝
            UserRelationEnum.FRIENDS.relation -> {
                relation = UserRelationEnum.FANS.relation
            }
        }
        UserMMKV.focusNumberCache = UserMMKV.focusNumberCache - 1
    }

    fun followStateName(): String {
        return when (relation) {
            UserRelationEnum.NONE.relation -> {
                "关注"
            }
            UserRelationEnum.FANS.relation -> {
                "回关"
            }
            UserRelationEnum.FOCUS.relation -> {
                "已关注"
            }
            UserRelationEnum.FRIENDS.relation -> {
                "互相关注"
            }
            else -> {
                ""
            }
        }
    }

    fun requireSelectIcon(context: Context): Drawable?{
      return  if(checked) ContextCompat.getDrawable(context, R.mipmap.icon_checked) else ContextCompat.getDrawable(context, R.mipmap.icon_uncheck)
    }
}

data class NearbyUserQueryParam(
    var pageNum: Int = 1,
    var pageSize: Int = 20,
    var gender: Int = -1,
    var order: String = "ASC"
)
/** class */
