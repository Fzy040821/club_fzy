package com.bw.kf.club_fengzy.net

import com.bw.kf.club_fengzy.BuildConfig


object API {
    const val BASE_URL = "https://iovapicyclone.zonsenmotor.com/"
//    const val BASE_MALL_URL = BuildConfig.MALL_API_URL

    //ceshi
    const val AUTH_LOGIN = "club-auth/login"
    const val AUTH_REFRESH = "club-auth/refresh"
    const val AUTH_LOGOUT = "club-auth/logout"
    const val REST_PSD = "club-auth/resetPwd"
    const val SMS_CODE_LOGIN = "club-system/sms/sendLoginCode"
    const val SMS_CODE_REST = "club-system/sms/sendForgetPwdCode"
    const val SMS_CODE_BIND = "club-system/sms/getBindingMobileCode"
    const val BIND_PHONE = "club-system/app/member/bindingPhone"

    //获取推荐车型
    const val GET_RECOMMEND_MODELS = "club-system/app/modelsRecommend/list"

    /** 预约试驾车型 */
    const val PAGE_BOOKING_MODELS = "moto/app/vehicle/model"

    /** 已注册用户绑定微信 */
    const val BIND_WX_OPENID = "club-system/app/member/bindingOpenId"

    const val MINE = "club-moto/app/member/getInfo"
    const val GET_USER_INFO_BY_ID = "club-moto/app/member/getByMemberIdInfo"

    //举报
    const val REPORT = "club-moto/app/tipOff/save"

    /**帖子*/
    // const val POST_PAGE = "club-moto/app/postsInfo"
    // 使用新接口
    const val POST_PAGE = "club-moto/app/postsInfo"
    const val POST_INFO_BY_ID = "club-moto/app/postsInfo/getByPostsId"
    const val POST_COMMENTS_BY_PAGE = "club-moto/app/postsInfo/pageComment"
    const val POST_AFTER_SALE_PAGE ="club-moto/app/postsInfo/palePage"

    //帖子点赞
    const val POST_LIKE_OR_CANCEL = "club-moto/app/postsInfo/giveLike"

    // TODO 帖子收藏
    const val POST_COLLECTION_OR_CANCEL = "club-moto/app/collect/giveCollect"
    /** 批量删除收藏 */
    const val COLLECTION_BATCH_DELETE = "club-moto/app/collect/batchDelete"

    //帖子校验
    const val POST_VALIDATION = "club-moto/app/postsInfo/giveValidationLike"
    const val INFORMATION_VALIDATION = "club-moto/app/information/giveValidationLike"

    const val DELETE_POST = "club-moto/app/postsInfo/deletePosts"
    const val PUBLISH_POST = "club-moto/app/postsInfo/savePosts"
    const val SHARE_POST = "club-moto/app/postsInfo/giveShare"
    const val SEARCH_HOT_LIST = "club-moto/app/search/searchHotList"
    const val SEARCH_KEYWORD = "club-moto/app/search/searchKeywordList"

    //帖子发布评论
    const val SAVE_POST_COMMENT = "club-moto/app/postsInfo/saveComment"

    const val SAVE_INFO_COMMENT = "club-moto/app/information/saveComment"

    //校验帖子留言
    const val POST_COMMENT_VALIDATION = "club-moto/app/postsInfo/saveValidationComment"

    //删除帖子评论
    const val DELETE_POST_COMMENT = "club-moto/app/postsInfo/deleteComment"
    const val DELETE_INFORMATION_COMMENT = "club-moto/app/information/deleteComment"

    //点赞帖子留言
    const val LIKE_POST_COMMENT = "club-moto/app/commentLike/giveLike"

    /**文章*/
    //发布文章
    const val PUBLISH_ARTICLE = "club-moto/app/articleInfo/saveArticle"
    const val ARTICLE_PAGE = "club-moto/app/articleInfo"
    const val ARTICLE_INFO_BY_ID = "club-moto/app/articleInfo/getByArticleId"
    const val ARTICLE_AFTER_SALE_PAGE ="club-moto/app/articleInfo/palePage"
    //文章点赞
    const val ARTICLE_LIKE_OR_CANCEL = "club-moto/app/articleInfo/giveLike"
    const val ARTICLE_VALIDATION = "club-moto/app/articleInfo/giveValidationLike"

    // 删除文章
    const val DELETE_ARTICLE = "club-moto/app/articleInfo/deleteRoute"

    //分享文章
    const val SHARE_ARTICLE = "club-moto/app/articleInfo/giveShare"

    //文章留言
    const val ARTICLE_COMMENTS_BY_PAGE = "club-moto/app/articleInfo/pageComment"

    //校验文章留言
    const val ARTICLE_COMMENT_VALIDATION = "club-moto/app/articleInfo/saveValidationComment"

    //文章发布评论
    const val SAVE_ARTICLE_COMMENT = "club-moto/app/articleInfo/saveComment"
    const val DELETE_ARTICLE_COMMENT = "club-moto/app/articleInfo/deleteComment"

    /**指南*/
    const val GUIDE_PAGE = "club-moto/app/guideInfo"
    const val GUIDE_TAG = "club-moto/app/guideInfo/findGuideTag"

    //指南点赞
    const val GUIDE_LIKE_OR_CANCEL = "club-moto/app/guideInfo/giveLike"
    const val GUIDE_VALIDATION = "club-moto/app/guideInfo/giveValidationLike"
    const val DYNAMIC_VALIDATION = "club-moto/app/dynamicInfo/giveValidationLike"
    const val ROUTE_VALIDATION = "club-moto/app/routeInfo/giveValidationLike"
    const val TEAM_VALIDATION = "club-moto/app/teamInfo/giveValidationLike"

    //分享指南
    const val SHARE_GUIDE = "club-moto/app/guideInfo/giveShare"

    //指南留言分页
    const val GUIDE_COMMENTS_BY_PAGE = "club-moto/app/guideInfo/pageComment"

    //指南详情
    const val GUIDE_INFO_BY_ID = "club-moto/app/guideInfo/getByGuideId"

    //指南留言
    const val SAVE_GUIDE_COMMENT = "club-moto/app/guideInfo/saveComment"
    const val SAVE_DYNAMIC_COMMENT = "club-moto/app/dynamicInfo/saveComment"
    const val SAVE_ROUTE_COMMENT = "club-moto/app/routeInfo/saveComment"
    const val SAVE_TEAM_COMMENT = "club-moto/app/teamInfo/saveComment"

    //校验指南留言
    const val GUIDE_COMMENT_VALIDATION = "club-moto/app/guideInfo/saveValidationComment"
    const val DYNAMIC_COMMENT_VALIDATION = "club-moto/app/dynamicInfo/saveValidationComment"
    const val ROUTE_COMMENT_VALIDATION = "club-moto/app/routeInfo/saveValidationComment"
    const val TEAM_COMMENT_VALIDATION = "club-moto/app/teamInfo/saveValidationComment"

    //删除指南留言
    const val DELETE_GUIDE_COMMENT = "club-moto/app/guideInfo/deleteComment"
    const val DELETE_DYNAMIC_COMMENT = "club-moto/app/dynamicInfo/deleteComment"
    const val DELETE_ROUTE_COMMENT = "club-moto/app/routeInfo/deleteComment"
    const val DELETE_TEAM_COMMENT = "club-moto/app/teamInfo/deleteComment"

    const val BIND_MOTO = "moto/app/vehicle/bind"
    const val GET_ALL_BIND_MOOT = "moto/app/vehicle"
    const val SEND_CONTROL_COMMEND = "moto/app/vehicle/contorl/command"

    //摩圈帖子，文章售后
    const val QUERY_AFTER_SALE_COUNT = "club-moto/app/motorcycle/circle/paleCount"
    const val SAVE_AFTER_SALE_INFO_MESSAGE = "club-moto/app/motorcycle/circle/readTime"

    //唤醒设备
    const val WAKE_DEVICE = "moto/app/vehicle/contorl/awakenDevice"
    const val GET_RT_DATA = "moto/app/vehicle/{id}"
    const val GET_RT_DATA2 = "moto/app/vehicle/body/{id}"
    const val GET_CONTROL_LIST = "moto/app/vehicle/contorl/{id}"
    const val GET_SDK_LIST = "moto/app/vehicle/contorl/{id}/4"
    const val GET_CHECK_VEHICLE_DATA = "moto/app/vehicle/contorl/checkCar/{id}"

    //摩圈上传文件大小
    const val GET_FILE_SIZE_LIMIT = "club-system/app/dict/data/getIvSizeLimit"


    //开始体检
    const val CREATE_EXAM = "moto/app/vehicle/fault/createRecord"

    //体检前检测
    const val CHECK_EXAM = "moto/app/vehicle/fault/check"

    //获取上次体检数据
    const val GET_LAST_EXAM = "moto/app/vehicle/fault/parentRecord/{id}"

    //获取当前体检状态
    const val GET_EXAM_STATUS = "moto/app/vehicle/fault/record/{id}"

    //获取分页体检记录
    const val GET_EXAM_RECORD_BY_PAGE = "moto/app/vehicle/fault"

    //我的
    //查询我的评论和评论我的
    const val GET_COMMENTS_IN_POST = "club-moto/app/my/myPagePostsComment"
    const val GET_COMMENTS_IN_INFORMATION = "club-moto/app/my/myPageInformationComment"
    const val GET_COMMENTS_IN_ARTICLE = "club-moto/app/my/myPageArticleComment"
    const val GET_COMMENTS_IN_GUIDE = "club-moto/app/my/myPageGuideComment"
    const val GET_COMMENTS_IN_DYNAMIC = "club-moto/app/my/myPageDynamicComment"
    const val GET_COMMENTS_IN_ROUTE = "club-moto/app/my/myPageRouteComment"
    const val GET_COMMENTS_IN_TEAM = "club-moto/app/my/myPageTeamComment"

    //未读评论
    const val GET_UNREAD_COMMENTS = "club-moto/app/my/myPostsCommentNumber"
    const val GET_UNREAD_LIKE = "club-moto/app/my/myPostsLikeCommentNumber"

    //查询我点的赞和我收到的赞
    //我收的的赞
    const val GET_POST_LIKE_I_GET = "club-moto/app/my/myGotPostsLike"
    const val GET_ARTICLE_LIKE_I_GET = "club-moto/app/my/myGotArticleLike"
    const val GET_GUIDE_LIKE_I_GET = "club-moto/app/my/myGotGuideLike"
    const val GET_DYNAMIC_LIKE_I_GET = "club-moto/app/my/myGotDynamicLike"
    const val GET_ROUTE_LIKE_I_GET = "club-moto/app/my/myGotRouteGuideLike"
    const val GET_TEAM_LIKE_I_GET = "club-moto/app/my/myGotTeamLike"
    const val GET_INFORMATION_LIKE_I_GET = "club-moto/app/my/myGotInformationLike"

    //我点的赞
    const val GET_POST_LIKE_I_GIVE = "club-moto/app/my/myPagePostsLike"
    const val GET_ARTICLE_LIKE_I_GIVE = "club-moto/app/my/myPageArticleLike"
    const val GET_GUIDE_LIKE_I_GIVE = "club-moto/app/my/myPageGuideLike"

    //切换默认车辆
    const val SWITCH_DEFAULT_VEHICLE = ""
    const val QUERY_CITY = "club-system/area/getAreaByParent"

    const val GET_SECURITY_SETTINGS = "club-moto/app/rule/{vehicleId}"
    const val UPDATE_SECURITY_SETTINGS = "club-moto/app/rule/update"
    const val UPDATE_SECURITY_SETTINGS_DEFAULT = "club-moto/app/rule/updateDefault"

    //爱车记忆
    const val GET_VEHICLE_SETTINGS = "moto/app/vehicle/memory/list/{vehicleId}"

    //获取详情
    const val GET_DFT_VEHICLE_SETTINGS_DETAILS = "moto/app/vehicle/memory/{vehicleId}"
    const val GET_VEHICLE_SETTINGS_DETAILS = "moto/app/vehicle/memory/{vehicleId}/{id}"

    //新增
    const val NEW_VEHICLE_SETTINGS = "moto/app/vehicle/memory/saveMemory"

    //修改
    const val UPDATE_VEHICLE_SETTINGS = "moto/app/vehicle/memory/updateMemory"

    //绑定极光Id
    const val BIND_JPUSH_REG_ID = "club-system/app/jPushReg/bindRid"

    //车辆修改(车牌号、绑定设备号)
    const val UPDATE_VEHICLE = "moto/app/vehicle/update"

    //历史轨迹
    const val QUERY_TRA = "moto/app/vehicle/trail"

    //历史轨迹详情
    const val QUERY_TRA_DETAILS = "moto/app/vehicle/{vehicleId}/detail/{date}"

    //里程排行榜
    const val GET_MILEAGE_RANK = "club-moto/app/rank"

    //获取用户在排行榜的数据
    const val GET_MILEAGE_RANK_SELF = "club-moto/app/rank/self"

    const val GET_MILEAGE_RANK_SHARE_INDEX = "club-moto/app/rank/share"

    //排行榜点赞或取消
    const val UPDATE_LIKE_STATUS = "club-moto/app/rank/updateLike"

    //骑行统计数据
    const val GET_STATISTIC_DATE = "club-moto/app/statistic/{type}/{id}"

    //点亮中国
    const val GET_LIGHT_CHINA_CITY_LIST = "club-moto/app/statistic/chinaList"

    //消息中心
    const val MESSAGE_CENTRE = "club-system/app/message/"

    //获取消息通知
    const val GET_MESSAGE_PAGE = "club-system/app/message/page"
    const val GET_SYS_MESSAGE_INFO = "club-system/app/message/info"

    //获取设置清单
    const val GET_MESSAGE_SETTINGS_LIST = "club-system/app/memberNotify/info"

    //设置通知是否开启
    const val UPDATE_MESSAGE_SETTINGS = "club-system/app/memberNotify/set"

    //删除消息
    const val DELETE_MESSAGE = "club-system/app/message/delete"

    //删除所有消息
    const val DELETE_ALL_MESSAGE = "club-system/app/message/deleteAll"

    //更新已读状态
    const val UPDATE_MESSAGE_STATUS = "club-system/app/message/upReadStatus"

    //电池信息
    const val GET_BATTERY_INFO = "club-moto/app/batteryRec/info"

    //耗电历史
    const val GET_CONSUME_POWER_HISTORY = "club-moto/app/batteryRec/consumePower"

    //分页获取版本
    const val GET_VERSION_INTRO_BY_PAGE = "club-moto/app/appVersion"
    const val GET_VERSION_INTRO = "club-moto/app/appVersion/info"

    //我是游客
    const val I_AM_TOURIST = "club-system/app/member/addMemberTourists"

    //获取举报类型
    const val GET_REPORT_TYPE = "club-moto/app/tipOff/list"

    //仪表里程
    const val GET_MILE_DATA = "moto/app/vehicle/odoHistory"

    //微信验证码校验
    const val VALIDATION_CODE = "club-system/app/member/validationCode"

    //动态获取节能模式配置信息
    const val GET_POWER_MODE_CONFIG = "club-moto/console/device/threshold/vehicleThresholdSet"

    //提交注销请求

    const val APPLY_DEL_ACCOUNT = "club-system/app/memberLogout/saveMemberLogout"
    const val CANCEL_APPLY_DEL_ACCOUNT = "club-system/app/memberLogout/cancel"
    const val APPLY_DEL_ACCOUNT_LIST = "club-system/app/memberLogout/selectBylist"

    const val BLOCK_CONTENT = "club-moto/app/shield/save"

    const val BG_VIDEO_URL = "club-system/app/dict/data/getVehicleVideo"


    /**设备OTA升级*/

    //查询是否待存在的升级任务
    const val QUERY_NOT_START_OTA_TASK = "moto/app/vehicle/contorl/getTaskstoUpgraded"

    //查询OTA任务状态
    const val QUERY_OTA_TASK_STATUS = "moto/app/vehicle/contorl/listOTATaskByJob"

    //开始升级
    const val START_OTA_TASK = "moto/app/vehicle/contorl/otaConfirmUpgrade"
    const val START_ECU_OTA_TASK = "moto/app/vehicle/contorl/ecuConfirmUpgrade"

    //重新开始升级
    const val RESTART_OTA_TASK = "moto/app/vehicle/contorl/reupgradeOTATask"
    //取消ECU-OTA升级

    const val CANCEL_ECU_OTA_TASK = "moto/app/vehicle/contorl/cancelUpgrade"

    //获取虚拟车车型图片

    const val GET_VIRTUAL_VEHICLE_IMAGE = "club-system/app/dict/data/getVirtualExperienceCar"

    /****************车辆共享相关***********************/
    //获取车辆的共享用户
    const val VEHICLE_SHARE_USER_LIST = "moto/app/vehicle/share/findShareUserList"

    //车辆共享设置
    const val VEHICLE_SHARE_SETTING = "moto/app/vehicle/share/shareSetUp"

    //添加共享用户
    const val VEHICLE_SHARE_ADD_MEMBER = "moto/app/vehicle/share/addUser"

    //添加共享用户，发送验证码
    const val VEHICLE_SHARE_ADD_MEMBER_SMS_CODE = "moto/app/vehicle/share/sendVehicleShareMessage"

    //移除共享用户
    const val VEHICLE_SHARE_REMOVE_MEMBER = "moto/app/vehicle/share/deleteUser"

    //校验用户是否注册
    const val  CHECK_PHONE_IS_REGISTERED = "club-system/app/member/checkPhoneRegister"

    //导航栏
    const val MOTO_CIRCLE_NAVIGATION = "club-moto/app/navigation"

}