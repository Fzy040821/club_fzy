package com.bw.kf.club_fengzy.profile

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.bw.kf.club_fengzy.BuildConfig
import com.bw.kf.club_fengzy.R
import com.bw.kf.club_fengzy.appContext
import com.bw.kf.club_fengzy.dialog.CustomUpdateDialog
import com.bw.kf.club_fengzy.mmkv.AuthMMKV
import com.bw.kf.club_fengzy.model.Image
import kotlin.random.Random

object ProfileMenu {
    // 设置中心
    const val CHANGE_PWD = 0
    const val BIND_WECHAT = 1
    const val BIND_PHONE = 2
    const val MSG_SWITCH = 3
    const val FEEDBACK = 4
    const val CLEAN_CACHE = 5
    const val DEL_ACCOUNT = 6
    const val ABOUT_US = 7

    // 关于我们
    const val PRIVACY_AGREEMENT = 8
    const val USER_AGREEMENT = 9
    const val VERSION_INTRO = 10
    const val CHECK_VERSION = 11

    // 新增-地址管理
    const val ADDRES_MGR = 12

    fun createProfileMenu(): List<ProfileMenuItem> {
        val list = ArrayList<ProfileMenuItem>()
        list.add(ProfileMenuItem(id = CHANGE_PWD, name = "修改密码", first = true))
        list.add(ProfileMenuItem(id = BIND_WECHAT, name = "微信绑定", subName = if (AuthMMKV.hasLogin()) "" else "未登录"))
        list.add(ProfileMenuItem(id = BIND_PHONE, name = "手机号", last = true))

        list.add(ProfileMenuItem(id = ADDRES_MGR, name = "收货地址", first = true))
        // 移动到首页-消息-右上角设置
        // list.add(ProfileMenuItem(id = MSG_SWITCH, name = "消息通知", last = true))

        list.add(ProfileMenuItem(id = FEEDBACK, name = "意见反馈", first = true))
        list.add(ProfileMenuItem(id = CLEAN_CACHE, name = "清理缓存", subName = ""))
        list.add(ProfileMenuItem(id = DEL_ACCOUNT, name = "注销账号"))
        list.add(ProfileMenuItem(id = ABOUT_US, name = "关于我们", subName = "版本${BuildConfig.VERSION_NAME}", last = true))
        return list
    }

    fun createAboutMenu(): List<ProfileMenuItem> {
        val list = ArrayList<ProfileMenuItem>()
        list.add(ProfileMenuItem(id = PRIVACY_AGREEMENT, name = "隐私条款"))
        list.add(ProfileMenuItem(id = USER_AGREEMENT, name = "用户协议"))
        list.add(ProfileMenuItem(id = VERSION_INTRO, name = "更新介绍"))
        list.add(
            ProfileMenuItem(
                id = CHECK_VERSION, name = "检查更新", last = true,
                hasNew = CustomUpdateDialog.hasNewVersion
            )
        )
        return list
    }

//    fun mockQuestionList(): List<FaqItem> {
//        val list = ArrayList<FaqItem>()
//        val count = Random.nextInt(10, 20)
//        for (i in 0..count) {
//            list.add(
//                FaqItem(
//                    name = RandomNameProvider.random(Random.nextInt(10, 30)),
//                    solution = RandomNameProvider.random(Random.nextInt(100, 200)),
//                )
//            )
//        }
//        return list
//    }

    fun mockContactList(): List<ContactServiceItem> {
        val list = ArrayList<ContactServiceItem>()
        list.add(
            ContactServiceItem(
                title = "联系热线1",
                desc = "秉承“以客为尊、专业服务”的宗旨，欢迎您随时随地与我们联系沟通联系",
                phone = "400-6666-8888",
            )
        )
        list.add(
            ContactServiceItem(
                title = "联系热线2",
                desc = "秉承“以客为尊、专业服务”的宗旨，欢迎您随时随地与我们联系沟通联系",
                phone = "400-8888-8888",
            )
        )
        return list
    }
}

/** 车辆&设备使用说明 */
//class DirectionUsageItem(
//    val directionsId: String = "",
//    val directionsType: Int? = 0,
//    val name: String? = "",
//    val modelsId: String? = "",
//    val modelsName: String? = "",// 富文本
//    val pdf: Image? = null,
//    val createTime: String? = "",
//    val updateTime: String? = "",
//    var last: Boolean = false
//) {
//    fun requiredName(): String {
//        return if (directionsType == 1) modelsName ?: ""
//        else name ?: ""
//    }
//}

open class FaqTypeItem(
    var id: String = "",
    var name: String? = "",
    var countNum: Int = 0,
    var checked: Boolean = false
)

//class CommonTabItem(
//    var informationTypeId: Long = 0L,
//    var knowledgeBaseTypeId: String? = null,
//    var typeName: String = "",
//    var typeSort: Int = 0
//) : FaqTypeItem() {
//    fun formatName(): String {
//        return name?.subTabString() ?: "-"
//    }
//}

//data class AppShareItem(
//    val styleId: String? = "",
//    val styleImg: Image? = null,
//    var checked: Boolean = false
//)

//data class FaqItem(
//    val manualId: String = "",
//    val typeId: String? = "",
//    val typeName: String? = "",
//    val name: String? = "",
//    val solution: String? = "",// 富文本
//    val createTime: String? = "",
//    val updateTime: String? = "",
//    var last: Boolean = false,
//    val pic: String? = RandomPicProvider.pic()
//)

//data class WikiItem(
//    val knowledgeBaseId: String = "",
//    val knowledgeBaseTypeId: String? = "",
//    val typeName: String? = "",
//    val title: String? = "",
//    val content: String? = "",// 富文本
//    val createTime: String? = "",
//    val updateTime: String? = "",
//    var last: Boolean = false,
//    val hotFlag: Int = 0,
//    val status: Int = 0,
//    val coverShow: Int = 0,
//    val coverUrl: Image? = null
//) {
//    fun findPicUrl(): String {
//        return coverUrl?.url ?: ""
//    }
//
//    fun findThumb(): String {
//        return coverUrl?.thumbnailUrl ?: ""
//    }
//}

object UserMemberEnum {
    fun genderList() = arrayListOf(
        CommonBottomItem(1, "男"),
        CommonBottomItem(0, "女"),
        CommonBottomItem(404, "取消")
    )

    fun jobList() = arrayListOf(
        CommonBottomItem(1, "自由职业者"),
        CommonBottomItem(2, "待业人士"),
        CommonBottomItem(3, "退休人员"),
        CommonBottomItem(4, "上班族"),
        CommonBottomItem(5, "在校学生"),
        CommonBottomItem(404, "取消")
    )

    fun usageList() = arrayListOf(
        CommonBottomItem(1, "上班上学通勤"),
        CommonBottomItem(2, "外出购物"),
        CommonBottomItem(3, "工作工具"),
        CommonBottomItem(4, "旅游聚会"),
        CommonBottomItem(5, "其他"),
        CommonBottomItem(404, "取消")
    )

    val professionals = listOf(
        "",
        "自由职业者",
        "待业人士",
        "退休人员",
        "上班族",
        "在校学生",
    )

    val vehicleUsage = listOf(
        "",
        "上班上学通勤",
        "外出购物",
        "工作工具",
        "旅游聚会",
        "其他"
    )
}

class UserMemberInfoItem(
    val memberId: String = "",
    val cellphone: String? = "",
    val headImg: String? = null,
    val nickName: String? = "",
    val realName: String? = "",
    val gender: Int? = 2,
    val birthday: String? = "",
    val idCard: String? = "",
    val address: String? = "",
    val wxOpenid: String? = "",
    val wxName: String? = "",
    val wxAppOpenid: String? = "",
    // 职业 1自由职业者2待业人士3退休人员4上班族5在校学生
    val professional: Int? = 0,
    // 车辆用途 1上班上学通勤2外出购物3工作工具4旅游聚会5其他
    val vehicleUse: Int? = 0,
    val hobby: String? = "",
    val emergencyName: String? = "",
    val emergencyPhone: String? = "",
    val ifPassword: Boolean? = null
) {
    fun requiredAvatar(): String = headImg ?: ""
    fun requiredUsage(): String {
        if (vehicleUse in 1..5)
            return UserMemberEnum.vehicleUsage[vehicleUse!!]
        return ""
    }

    fun requiredJob(): String {
        if (professional in 1..5)
            return UserMemberEnum.professionals[professional!!]
        return ""
    }

    fun requiredGender(): String {
        return when (gender) {
            0 -> "女"
            1 -> "男"
            else -> ""
        }
    }
}

// 流量充值
data class FlowRechargeItem(
    val recordId: String = "",
    val memberId: String? = null,
    val nickName: String? = null,
    val phone: String? = null,
    val licensePlate: String? = null,
    val vehicleModel: String? = null,
    val vin: String? = null,
    val deviceNumber: String? = null,
    val vehicleId: String? = null,
    val serialNumber: String? = null,
    val remark: String? = null,
    val successTime: String? = null,
    val createTime: String? = null,
    val card: String? = null,
    val imei: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val topUpCycle: Int? = null,
    val payType: Int? = null,
    val state: Int? = null,
    val payAmount: Float? = null,
    val price: Float? = null,
    //优惠券
    val discountAmount: Double? = 0.0,
    val couponName: String? = "",
) {

    fun showCoupon(): Boolean {
        return !couponName.isNullOrEmpty()
    }

    //流量优惠券
    fun formatCouponName(): CharSequence {
        val amount = String.format("-¥%.2f", discountAmount)
        val content = "$couponName $amount"
        val ssb = SpannableStringBuilder(content)
        val start = content.indexOf(amount)
        val end = start + amount.length
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#FFD7000F")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }

    fun requiredNameAndLicense(): String {
        return "$vehicleModel    ${licensePlate ?: ""}"
    }

//    fun requiredAmount() = "¥${payAmount?.formatDecimal() ?: "0.00"}"
//
//    private fun requiredPrice() = "¥${price?.formatDecimal() ?: payAmount?.formatDecimal() ?: "0.00"}"

    fun requiredPayType(): String {
        return when (payType) {
            1 -> "微信"
            2 -> "支付宝"
            else -> "其他"
        }
    }

    fun requiredCycle(): String {
        return when (topUpCycle) {
            1 -> "1年"
            2 -> "6个月"
            3 -> "3个月"
            4 -> "1个月"
            5 -> "2年"
            else -> "--"
        }
    }

//    fun requiredCycleWithPrice(): String {
//        return requiredCycle() + " " + requiredPrice()
//    }
}

// 预约试驾
class BookingInfoItem(
    val id: String = "",
    val memberId: String? = "",
    val memberAccount: String? = "",
    val memberName: String? = "",
    val memberPhone: String? = "",
    val province: String? = "",
    val city: String? = "",
    val modelName: String? = "",
    val modelNickname: String? = "",
    val carImg: Image? = null,
    val status: Int? = 0,// 试驾状态 1：待试驾 2：已试驾 3:已过期
    var appointTime: String? = "",// 试驾时间
    val createTime: String? = "",// 申请时间
) {
    fun provinceCity(): String? {
        // 如果是直辖市（比如：北京市），只返回城市的名字
        return if (city != province) (province + city)
        else city
    }

    fun formatAppointTime(): String {
        return appointTime?.split(" ")?.firstOrNull() ?: "--"
    }
}

/** 失窃上报 */
class StolenInfoItem(
    val id: String = "",
    val address: String? = "",
    val modelNickname: String? = "",
    val carModelId: String? = "",
    val dealContent: String? = "",
    val dealStatus: Int? = 0,// 状态 1：待处理 2：已处理 3：已找回
    val licenseNumber: String? = "",
    val memberId: String? = "",
    val phone: String? = "",
    val city: String? = "",
    val province: String? = "",
    val vin: String? = "",
    val loseTime: String? = "",
    val updateTime: String? = "",
    val createTime: String? = "",
) {
    fun requiredLicenseNumber(): String {
        return if (licenseNumber.isNullOrEmpty()) "--" else licenseNumber
    }

    fun requiredModelNickname(): String {
        return if (modelNickname.isNullOrEmpty()) "--" else modelNickname
    }

    fun provinceCity(): String? {
        return if (city != province) (province + city)
        else city
    }

    fun requiredDealContent() = if (dealContent.isNullOrEmpty()) "暂无" else dealContent
}

class FlowConfigItem(
    var configId: String,
    var createTime: String = "",
    var linePrice: Float = 0f,
    var price: Float = 0f,
    var topUpCycle: Int = 0,// 充值周期1-1年2-半年3-3个月4-1个月
    var state: Int = 0,// 上下架1上0下
    var checked: Boolean = false
) {
    fun requiredCycle(): String {
        return when (topUpCycle) {
            1 -> "1年"
            2 -> "6个月"
            3 -> "3个月"
            4 -> "1个月"
            5 -> "2年"
            else -> "其他"
        }
    }

    fun intPrice() = "¥${price.toInt()}"

//    fun floatPrice(): String {
//        val pf = price * 100 % 100 / 100f
//        return pf.formatDecimal().replace("0.", ".")
//    }

    fun intLinePrice() = "¥${linePrice.toInt()}"

//    fun floatLinePrice(): String {
//        val pf = linePrice * 100 % 100 / 100f
//        return pf.formatDecimal().replace("0.", ".")
//    }
}

// 骑行徽章
class RideMedalItem(
    var recordId: String? = "",
    var configId: String? = "",
    var memberId: String? = "",
    var medalName: String = "",
    var createTime: String? = "",
    var medalImg: Image? = null,
    var threshold: Int? = 0,
    var isLightUp: Int? = 0,// 1已获得 0未获得
) {
    fun needGrayIcon() = isLightUp == 0
//    fun lightDate(): SpannableString? {
//        if (createTime == null && BuildConfig.DEBUG)
//            createTime = "2022-09-15 22:05:41"
//        if (!createTime.isNullOrEmpty() && isLightUp == 1) {
//            val formatDate = createTime!!.split(" ").first()
//            val yyyyMMdd = formatDate.split("-")
//            val formatDateCN = "${yyyyMMdd[0]}年${yyyyMMdd[1]}月${yyyyMMdd[2]}日"
//            val src = "你已在${formatDateCN}获得"
//            return JTextUtils.highlight(src, formatDateCN, ContextCompat.getColor(App.getAppContext(), R.color.color_d8000f))
//        }
//        return null
//    }

    fun tips(): String = "骑行里程达到${threshold}KM可获得"
}

// 售后支持
class AfterSalesInfoItem(
    var city: String? = "",
    var content: String? = "",
    var createTime: String? = "",
    var dealContent: String? = "",
    var dealStatus: Int? = 0,// 处理状态 1：待处理 2：已处理
    var dealTime: String? = "",
    var id: String = "",
    var imgs: String? = null,
    var imgsList: List<Image>? = null,
    var memberId: String? = "",
    var memberName: String? = "",
    var memberPhone: String? = "",
    var province: String? = ""
) {
    fun hasPic(): Boolean = !(imgsList.isNullOrEmpty() && imgs.isNullOrEmpty())

    fun requiredPicList(): ArrayList<String> {
        val list = ArrayList<String>()
        if (!imgsList.isNullOrEmpty()) {
            val temp = imgsList!!.map { it.url }
            list.addAll(temp)
        } else if (!imgs.isNullOrEmpty()) {
            list.addAll(imgs!!.split(","))
        }

        return list
    }

    fun requiredState(): String {
        return if (dealStatus == 2) "已处理"
        else "待处理"
    }

    fun provinceCity(): String? {
        return if (city != province) (province + city)
        else city
    }
}

data class ContactServiceItem(
    val serviceId: String = "",
    var title: String? = "",
    var desc: String? = "",
    val phone: String? = "",
    val serviceType: Int? = null,// 1 APP客服 2：售后客服 3 客服微信 4 留言反馈
) {
    fun requirePrefixIcon(): Drawable? {
        return when (serviceType) {
            3 -> ContextCompat.getDrawable(appContext, R.mipmap.icon_share_wechat)
            4 -> ContextCompat.getDrawable(appContext, R.mipmap.icon_callback_tittle)
            else -> ContextCompat.getDrawable(appContext, R.mipmap.icon_customer_service_title)
        }
    }

    fun requireSuffixIcon(): Drawable? {
        return when (serviceType) {
            3 -> ContextCompat.getDrawable(appContext, R.mipmap.icon_service_qr_code)
            4 -> ContextCompat.getDrawable(appContext, R.mipmap.icon_callback)
            else -> ContextCompat.getDrawable(appContext, R.mipmap.icon_call)
        }
    }

    fun requireDesc(): String? {
        return when (serviceType) {
            3, 4 -> desc
            else -> phone
        }
    }

}

class CommonBottomItem(
    val key: Int = 0,
    val name: String = ""
) {
    fun ifCancel() = key == 404
}

class CommonBottomCheckItem(
    val key: String? = "",
    val name: String? = "",
    var checked: Boolean = false
)

class ProfileMenuItem(
    val id: Int,
    var name: String,
    var subName: String = "",
    val icon: Int = 0,
    var first: Boolean = false,
    var last: Boolean = false,
    var hasNew: Boolean = false,
) {
    fun splitLineVis(): Int = if (last) View.GONE else View.VISIBLE
}


// 预约试驾
class BookingStoreMockItem(
    val id: String = "",
    val memberId: String? = "",
    val memberAccount: String? = "",
    val memberName: String? = "",
    val memberPhone: String? = "",
    val province: String? = "",
    val city: String? = "",
    val modelName: String? = "",
    val modelNickname: String? = "",
    val carImg: Image? = null,
    val status: Int? = 0,// 试驾状态 1：待试驾 2：已试驾 3:已过期
    var appointTime: String? = "",// 试驾时间
    val createTime: String? = "",// 申请时间
) {
    fun provinceCity(): String? {
        // 如果是直辖市（比如：北京市），只返回城市的名字
        return if (city != province) (province + city)
        else city
    }

    fun formatAppointTime(): String {
        return appointTime?.split(" ")?.firstOrNull() ?: "--"
    }
}

//售后数量
data class AfterSaleInfoModel(val postsInfoList: List<PostAfterSaleModel>? = null, val articleInfoList: List<ArticleAfterSaleModel>? = null)

data class PostAfterSaleModel(val postsId: String, val createTime: String? = null)
data class ArticleAfterSaleModel(val articleId: String, val createTime: String? = null)
