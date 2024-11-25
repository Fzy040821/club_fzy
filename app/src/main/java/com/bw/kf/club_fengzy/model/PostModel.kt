package com.bw.kf.club_fengzy.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.appContext
import com.bw.kf.club_fengzy.mmkv.UserMMKV


/**
 *@author Wcj
 *@description
 *@date 2022/7/12 10:11
 */
data class PostModel(
    val avatar: Image? = null,
    val categoryStr: String? = null,
    val content: String? = null,
    val createBy: String = "",
    val hotSort: Int? = null,
    val ifAudit: Int? = null,
    val ifHot: Int? = null,
    var ifLike: Int? = null,
    var ifCollect: Int? = null,
    val ifMy: Int? = null,
    val ifRecommend: Int? = null,
    val ifSensitive: Int? = null,
    val ifTipOff: String? = null,
    val ifUp: Int? = null,
    val ifUpStr: String? = null,
    val img: List<Image>? = null,
    val lat: Double? = null,
    val location: String? = null,
    val lon: Double? = null,
    val postsId: String,
    val recommendSort: Int? = null,
    val tipOffNumber: Int? = null,
    val topicStr: String? = null,
    val topics: List<Topic>? = null,
    val updateTime: String? = null,
    val userName: String? = null,
    val vehicleModelId: String? = null,
    val weightScore: Double? = null,
) : MotoCircleModel() {
    // 获取反收藏状态,用于点击收藏按钮时使用
    fun reverseIfCollect(): Int {
        return if (ifCollect == 1) 0 else 1
    }

    fun requireCollectIcon(context: Context): Drawable? {
        return if (ifCollect == 1)
            ContextCompat.getDrawable(context, R.mipmap.icon_collection_checked)
        else
            ContextCompat.getDrawable(context, R.mipmap.icon_collection)

    }

    //如果为官方发布，需要自己在内容后添加话题
    fun requireContent(): String {
        if (channel == 1 && !topics.isNullOrEmpty()) {
            val sb = StringBuilder()
            sb.append(content)
            topics.forEach {
                sb.append("#${it.topicName}#")
            }
            return sb.toString()
        }
        return content ?: ""
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PostModel)
            return false
        return this.avatar == other.avatar &&
                this.modelName == other.modelName &&
                super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (avatar?.hashCode() ?: 0)
        result = 31 * result + (categoryStr?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + createBy.hashCode()
        result = 31 * result + (hotSort ?: 0)
        result = 31 * result + (ifAudit ?: 0)
        result = 31 * result + (ifHot ?: 0)
        result = 31 * result + (ifLike ?: 0)
        result = 31 * result + (ifCollect ?: 0)
        result = 31 * result + (ifMy ?: 0)
        result = 31 * result + (ifRecommend ?: 0)
        result = 31 * result + (ifSensitive ?: 0)
        result = 31 * result + (ifTipOff?.hashCode() ?: 0)
        result = 31 * result + (ifUp ?: 0)
        result = 31 * result + (ifUpStr?.hashCode() ?: 0)
        result = 31 * result + (img?.hashCode() ?: 0)
        result = 31 * result + (lat?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (lon?.hashCode() ?: 0)
        result = 31 * result + postsId.hashCode()
        result = 31 * result + (recommendSort ?: 0)
        result = 31 * result + (tipOffNumber ?: 0)
        result = 31 * result + (topicStr?.hashCode() ?: 0)
        result = 31 * result + (topics?.hashCode() ?: 0)
        result = 31 * result + (updateTime?.hashCode() ?: 0)
        result = 31 * result + (userName?.hashCode() ?: 0)
        result = 31 * result + (vehicleModelId?.hashCode() ?: 0)
        result = 31 * result + (weightScore?.hashCode() ?: 0)
        return result
    }


}

/**
 * 统一处理展示官方的问题
 */
open class MotoCircleModel(
    val channel: Int = 0,
    val nickName: String = "",
    val modelName: String = "",
    val modelNickname: String = "",
    val channelStr: String = "",
    val virtualLikeNumber: Int = 0,
    val virtualShareNumber: Int = 0,
    val virtualCollectionNumber: Int = 0,
    var likeNumber: Int = 0,
    var collectNumber: Int = 0,
    val shareNumber: Int = 0,
    var commentNumber: Int = 0,
    val listShowTime: String = "",

    //售后状态
    val aftersaleStatus: Int? = null,
    //是否已转售后
    val ifAftersale: Int? = null,
    // 关注状态
    var relation: Int = 0,// 好友关系(0:无关系 1:粉丝 2:已关注 3:相互关注)
    // 是否需要展示关注状态，某些特定页面是不展示的
    var needShowFollowState: Boolean? = null
) {
    fun hasFollowed(): Boolean {
        return relation in listOf(
            UserRelationEnum.FOCUS.relation,
            UserRelationEnum.FRIENDS.relation
        )
    }

    fun showFollowState(): Boolean {
        if (needShowFollowState == null)
            needShowFollowState = true

        // 非官方 非自己 非关注列表 才需要显示关注关系
        return channel != 1 && needShowFollowState == true && relation != UserRelationEnum.MYSELF.relation
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
        relation = when (relation) {
            // 没有关系 --> 已关注
            UserRelationEnum.NONE.relation -> {
                UserRelationEnum.FOCUS.relation
            }
            // 粉丝 --> 互相关注
            UserRelationEnum.FANS.relation -> {
                UserRelationEnum.FRIENDS.relation
            }
            else -> {
                UserRelationEnum.FOCUS.relation
            }
        }
        UserMMKV.focusNumberCache = UserMMKV.focusNumberCache + 1
    }

    /** 取消关注 */
    fun onCancelFollow() {
        relation = when (relation) {
            // 已关注 --> 没有关系
            UserRelationEnum.FOCUS.relation -> {
                UserRelationEnum.NONE.relation
            }
            // 互相关注 --> 粉丝
            UserRelationEnum.FRIENDS.relation -> {
                UserRelationEnum.FANS.relation
            }
            else -> {
                UserRelationEnum.NONE.relation
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
                "关注"
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

    //是否为官方发布
    open fun isOfficial() = channel == 1

    //显示名称  官方-显示赛科龙官方 其他-显示nikeName
    fun requireName(context: Context): String {
        return if (isOfficial()) {
            context.getString(R.string.string_cyclone_official)
        } else {
            nickName
        }
    }

    //显示颜色 官方-显示主题色 其他-显示黑色
    fun requireNameColor(context: Context): Int {
        return if (isOfficial()) {
            ContextCompat.getColor(context, R.color.color_d8000f)
        } else {
            ContextCompat.getColor(context, R.color.color_101010)
        }
    }

    //显示subName  官方-显示赛科龙官方发布 其他-显示modelName
    fun requireSubName(context: Context): String {
        return if (isOfficial()) {
            context.getString(R.string.string_cyclone_official_publish)
        } else {
            modelNickname
        }
    }

    //格式化点赞数显示
    fun formatLikeNumber(): String {
        val total = likeNumber + virtualLikeNumber
        return if (total < 10_000) {
            total.toString()
        } else if (total > 10_000) {
            String.format("%sw+", total / 10_000)
        } else {
            "1w"
        }
    }

    //格式化收藏数显示
    fun formatCollectionNumber(): String {
        val total = collectNumber + virtualCollectionNumber
        return if (total < 10_000) {
            total.toString()
        } else if (total > 10_000) {
            String.format("%sw+", total / 10_000)
        } else {
            "1w"
        }
    }

    //格式化分享数显示
    fun formatShareNumber(): String {
        val total = shareNumber + virtualShareNumber
        return if (total < 10_000) {
            total.toString()
        } else if (total > 10_000) {
            String.format("%sw+", total / 10_000)
        } else {
            "1w"
        }
    }

    //格式化评论数
    fun formatCommentNumber(): String {
        return if (commentNumber < 10_000) {
            commentNumber.toString()
        } else if (commentNumber > 10_000) {
            String.format("%sw+", commentNumber / 10_000)
        } else {
            "1w"
        }
    }

    //是否处于售后状态
    fun inAfterSaleStatus(): Boolean {
        return ifAftersale == 1
    }

    fun requireAfterSaleLabel(): Drawable? {
        if (ifAftersale != 1) return null
        return if (aftersaleStatus == 2) {
            //已处理
            ContextCompat.getDrawable(appContext, R.mipmap.icon_after_sale_finished_label)
        } else {
            ContextCompat.getDrawable(appContext, R.mipmap.icon_after_sale_label)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is MotoCircleModel) {
            return (this.nickName == other.nickName &&
                    this.shareNumber == other.shareNumber &&
                    this.virtualShareNumber == other.virtualShareNumber &&
                    this.commentNumber == other.commentNumber &&
                    this.likeNumber == other.likeNumber &&
                    this.virtualLikeNumber == other.virtualLikeNumber &&
                    this.modelName == other.modelName &&
                    this.listShowTime == other.listShowTime &&
                    this.ifAftersale == other.ifAftersale &&
                    this.collectNumber == other.collectNumber &&
                    this.virtualCollectionNumber == other.virtualCollectionNumber )
        }
        return false
    }

    override fun hashCode(): Int {
        var result = channel
        result = 31 * result + nickName.hashCode()
        result = 31 * result + modelName.hashCode()
        result = 31 * result + modelNickname.hashCode()
        result = 31 * result + channelStr.hashCode()
        result = 31 * result + virtualLikeNumber
        result = 31 * result + virtualShareNumber
        result = 31 * result + virtualCollectionNumber
        result = 31 * result + likeNumber
        result = 31 * result + collectNumber
        result = 31 * result + shareNumber
        result = 31 * result + commentNumber
        result = 31 * result + listShowTime.hashCode()
        result = 31 * result + (aftersaleStatus ?: 0)
        result = 31 * result + (ifAftersale ?: 0)
        result = 31 * result + relation
        result = 31 * result + (needShowFollowState?.hashCode() ?: 0)
        return result
    }


}


data class Image(
    val contentLength: Int = 0,
    val contentType: String = "",
    val filename: String = "",
    val firstImage: String = "",
    val imageHeight: Int = 0,
    val imageWidth: Int = 0,
    val thumbnailUrl: String? = "",
    val url: String
)


data class Topic(
    val topicId: String,
    val topicName: String
)
