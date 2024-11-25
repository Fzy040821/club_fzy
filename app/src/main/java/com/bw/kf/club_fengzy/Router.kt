package com.bw.kf.club_fengzy

object Router {
    /**俱乐部相关**/
    const val PKG_CLUB = "/ui/club/"
    /**
     *俱乐部-活动相关
     */
    const val PKG_ACT = "/ui/club_act/"
    //俱乐部管理
    const val PKG_CLUB_MGR = "/ui/club_mgr/"

    /** 个人中心 */
    const val PKG_PROFILE = "/ui/profile/"

    /** 商城相关 */
    const val PKG_MALL = "/ui/mall/"

    /** 俱乐部v2 */
    const val PKG_CLUB_V2 = "/ui/club_v2/"

    /** 俱乐部-活动V2相关 */
    const val PKG_ACT_V2 = "/ui/club_act_v2/"

    object Ui {
        const val GuideActivity = "/ui/guide/GuideActivity"

        const val AdActivity = "/ui/main/AdActivity"
        const val MainActivity = "/ui/main/MainActivity"
        const val VXActivity = "/ui/main/VXActivity"
        const val LoginActivity = "/ui/login/LoginActivity"
        const val ReportActivity = "/ui/main/ReportActivity"
        const val BindActivity = "/ui/bind/BindActivity"
        const val ScanActivity = "/ui/bind/ScanActivity"
        const val UnbindActivity = "/ui/bind/UnbindActivity"
        const val BindPlateNumberActivity = "/ui/bind/BindPlateNumberActivity"
        const val BindDeviceNumActivity = "/ui/bind/BindDeviceNumActivity"
        const val BindBatteryNumActivity = "/ui/bind/BindBatteryNumActivity"

        const val SearchActivity = "/ui/search/SearchActivity"
        const val SearchRetActivity = "/ui/search/SearchRetActivity"
        const val PublishPostActivity = "/ui/publish/PublishPostActivity"
        const val PublishArticleActivity = "/ui/publish/PublishArticleActivity"

        const val PostDetailsActivity = "/ui/details/PostDetailsActivity"
        const val ArticleDetailsActivity = "/ui/details/ArticleDetailsActivity"
        const val GuideDetailsActivity = "/ui/details/GuideDetailsActivity"
        const val TopicActivity = "/ui/topic/TopicActivity"

        //摩圈-我的发布
        const val MyPublishActivity = "/ui/mypublish/MyPublishActivity"

        //摩圈-我的评论
        const val MyCommentsActivity = "/ui/mypublish/MyCommentsActivity"

        //摩圈-我的点赞
        const val MyLikeActivity = "/ui/mypublish/MyLikeActivity"

        //个人主页
        const val UserPageActivity = "/ui/user/UserPageActivity"

        const val SettingsActivity = "/ui/settings/SettingsActivity"
        const val ExaminationActivity = "/ui/control/exam/ExaminationActivity"
        const val RecordActivity = "/ui/control/exam/RecordActivity"
        const val RecordDetailsActivity = "/ui/control/exam/RecordDetailsActivity"

        const val MessageCenterActivity = "/ui/message/MessageCenterActivity"
        const val SysMessageListActivity = "/ui/message/SysMessageListActivity"
        const val DynamicMessageListActivity = "/ui/message/DynamicMessageListActivity"
        const val ClubMessageListActivity = "/ui/message/ClubMessageListActivity"
        const val VehicleMessageListActivity = "/ui/message/VehicleMessageListActivity"
        const val ServiceMessageListActivity = "/ui/message/ServiceMessageListActivity"
        const val MallOrderMessageListActivity = "/ui/message/MallOrderMessageListActivity"
        const val MessageSettingsActivity = "/ui/message/MessageSettingsActivity"
        const val SysMessageActivity = "/ui/message/SysMessageActivity"
        const val ClubMessageActivity = "/ui/message/ClubMessageActivity"

        const val RtDataActivity = "/ui/rt/RtDataActivity"
        const val MileDataActivity = "/ui/rt/MileDataActivity"
        const val MileDataFilteringActivity = "/ui/rt/MileDataFilteringActivity"
        const val SecuritySettingsActivity = "/ui/rt/SecuritySettingsActivity"

        // 视频播放
        const val VideoPlayerActivity = "/ui/palyer/VideoPlayerActivity"

        //寻车
        const val FindMotoActivity = "/ui/navi/FindMotoActivity"

        //骑行数据
        const val RideDataActivity = "/ui/rideData/RideDataActivity"

        //驾驶行为
        const val DrivingBehaviorActivity = "/ui/rideData/DrivingBehaviorActivity"

        //历程排行
        const val MileageRankActivity = "/ui/rideData/MileageRankActivity"
        const val MileageRankV2Activity = "/ui/rideData/MileageRankV2Activity"

        //里程排行分享
        const val MileRankShareActivity = "/ui/rideData/MileRankShareActivity"
        const val RankShareActivity = "/ui/rideData/RankShareActivity"

        //历史轨迹
        const val TrajectoryActivity = "/ui/trajectory/TrajectoryActivity"

        //轨迹筛选
        const val TraFilteringActivity = "/ui/trajectory/TraFilteringActivity"

        //轨迹详情
        const val TraDetailsActivity = "/ui/trajectory/TraDetailsActivity"

        //轨迹分享
        const val TraShareActivity = "/ui/trajectory/TraShareActivity"

        //轨迹播放
        const val TraPlayActivity = "/ui/trajectory/TraPlayActivity"

        //点亮中国
        const val LightChinaActivity = "/ui/lightChina/LightChinaActivity"


        // ********** 俱乐部相关 **********
        const val BlankListActivity = "${PKG_CLUB}BlankListActivity"
        const val TestClubHomeActivity = "${PKG_CLUB}TestClubHomeActivity"
        const val ClubPaymentWebActivity = "${PKG_CLUB}PaymentWebActivity"

        const val CommonWebActivity = "/ui/CommonWebActivity"

        const val BlankActivity = "${PKG_CLUB}BlankActivity"
        const val ClubDetailHomeActivity = "${PKG_CLUB}ClubDetailHomeActivity"

        // 俱乐部详情
        const val MultiFragmentActivity = "${PKG_CLUB}MultiFragmentActivity"

        // 搜索俱乐部
        const val SearchClubActivity = "${PKG_CLUB}SearchClubActivity"

        // 用户-订单搜索
        const val MemberSearchOrderActivity = "${PKG_CLUB}MemberSearchOrderActivity"

        // 俱乐部评分
        const val ClubRatingActivity = "${PKG_CLUB}ClubRatingActivity"

        // 申请加入俱乐部
        const val ApplyJoinClubActivity = "${PKG_CLUB}ApplyJoinClubActivity"

        // 发布动态
        const val PublishTimelineActivity = "${PKG_CLUB}PublishTimelineActivity"

        // 动态详情
        const val TimelineDetailActivity = "${PKG_CLUB}TimelineDetailActivity"

        // 发布活动
        const val PublishActActivity = "${PKG_ACT}PublishActActivity"

        // 活动预览-创建的时候用的
        const val ActPreviewActivity = "${PKG_ACT}ActPreviewActivity"

        // 活动报名
        const val ActSignUpActivity = "${PKG_ACT}ActSignUpActivity"

        // 活动支付
        const val ActPaymentActivity = "${PKG_ACT}ActPaymentActivity"

        // 报名结果
        const val ActSingResultActivity = "${PKG_ACT}ActSingResultActivity"

        // 定位选择
        const val AddressSelectorActivity = "/ui/map/AddressSelectorActivity"

        // 地图选点
        const val MapPointSelectorActivity = "/ui/map/MapPointSelectorActivity"

        // ********** 俱乐部管理相关 **********
        // 俱乐部管理首页
        const val ClubMgrIndexActivity = "${PKG_CLUB_MGR}ClubMgrIndexActivity"

        // 我加入的俱乐部
        const val MyJoinedClubActivity = "${PKG_CLUB_MGR}MyJoinedClubActivity"

        // 我创建的俱乐部
        const val MyCreatedClubActivity = "${PKG_CLUB_MGR}MyCreatedClubActivity"

        // 俱乐部会员信息
        const val ClubMemberInfoActivity = "${PKG_CLUB_MGR}ClubMemberInfoActivity"

        // 俱乐部管理>我加入的俱乐部>我的活动
        const val MyActListActivity = "${PKG_ACT}MyActActivity"

        // 俱乐部管理>我加入的俱乐部>活动动态
        const val MyActTimelineActivity = "${PKG_ACT}MyActTimelineActivity"

        // 俱乐部管理>我加入的俱乐部>动态点赞
        const val MyLikedListActivity = "${PKG_ACT}MyLikedListActivity"

        // 俱乐部管理>我加入的俱乐部>活动评论
        const val MyCommentListActivity = "${PKG_ACT}MyCommentListActivity"

        // 俱乐部管理>我加入的俱乐部>报名订单
        const val MyActOrderListActivity = "${PKG_ACT}MyActOrderListActivity"

        // 订单详情-我加入的
        const val OrderDetailActivity = "${PKG_ACT}OrderDetailActivity"

        // 创建俱乐部
        const val CreateClubActivity = "${PKG_CLUB_MGR}CreateClubActivity"
        const val EditClubActivity = "${PKG_CLUB_MGR}EditClubActivity"
        const val PreviewClubInfoActivity = "${PKG_CLUB_MGR}PreviewClubInfoActivity"
        const val EditCoverActivity = "${PKG_CLUB_MGR}EditCoverActivity"

        // 退出俱乐部
        const val ExitClubActivity = "${PKG_CLUB_MGR}ExitClubActivity"

        // 俱乐部审核进度
        const val ClubExamineProgressActivity = "${PKG_CLUB_MGR}ClubExamineProgressActivity"
        const val ActExamineProgressActivity = "${PKG_CLUB_MGR}ActExamineProgressActivity"

        // 俱乐部审核进度
        const val ClubReviewActivity = "${PKG_CLUB_MGR}ClubReviewActivity"

        // 订单搜索
        const val SearchOrderActivity = "${PKG_CLUB_MGR}SearchOrderActivity"

        // 俱乐部相关的搜索页面
        const val ClubMgrSearchActivity = "${PKG_CLUB_MGR}MgrActSearchListActivity"

        // 订单搜索结果
        const val SearchOrderResultActivity = "${PKG_CLUB_MGR}SearchOrderResultActivity"

        // 俱乐部活动管理 - 4个Fragment(活动/会员/订单/俱乐部)
        const val MyClubActActivity = "${PKG_CLUB_MGR}MyClubActActivity"

        // 创建活动
        const val CreateActActivity = "${PKG_ACT}CreateActActivity"

        // 修改活动
        const val EditActActivity = "${PKG_CLUB_MGR}EditActActivity"

        // 活动审核进度
        const val ActReviewActivity = "${PKG_CLUB_MGR}ActReviewActivity"

        // 俱乐部活动详情 - 3个Fragment(详情/动态/参与人)
        const val ClubActDetailActivity = "${PKG_CLUB_MGR}ClubActDetailActivity"

        // 俱乐部管理
        const val ClubManagementActivity = "${PKG_CLUB_MGR}ClubManagementActivity"

        // 我的俱乐部详情-已通过的俱乐部-首页-含4个Fragment
        const val ClubHomeMgrActivity = "${PKG_CLUB_MGR}MyClubDetailHomeActivity"

        // 报名费用
        const val ActFundListActivity = "${PKG_CLUB_MGR}ActFundListActivity"

        // 申请提现
        const val ApplyWithdrawalActivity = "${PKG_CLUB_MGR}ApplyWithdrawalActivity"

        /** 个人中心相关页面-1 */
        const val UserInfoActivity = "${PKG_PROFILE}UserInfoActivity"
        const val ClipAvatarActivity = "${PKG_PROFILE}ClipAvatarActivity"
        const val StolenApplyActivity = "${PKG_PROFILE}StolenApplyActivity"
        const val StolenListActivity = "${PKG_PROFILE}StolenListActivity"
        const val StolenDetailInfoActivity = "${PKG_PROFILE}StolenDetailInfoActivity"
        const val AfterSalesActivity = "${PKG_PROFILE}AfterSalesActivity"
        const val AfterSalesApplyHistoryActivity = "${PKG_PROFILE}AfterSalesApplyHistoryActivity"
        const val AfterSalesInfoActivity = "${PKG_PROFILE}AfterSalesInfoActivity"
        const val BookingDriverActivity = "${PKG_PROFILE}BookingDriverActivity"
        const val CareServiceActivity = "${PKG_PROFILE}CareServiceActivity"
        const val CareServiceInfoActivity = "${PKG_PROFILE}CareServiceInfoActivity"
        const val CareServiceRecordActivity = "${PKG_PROFILE}CareServiceRecordActivity"
        const val CareManifestActivity = "${PKG_PROFILE}CareManifestActivity"
        const val FixOnlineActivity = "${PKG_PROFILE}FixOnlineActivity"
        const val FixOnlineRecordActivity = "${PKG_PROFILE}FixOnlineRecordActivity"
        const val FixOnlineInfoActivity = "${PKG_PROFILE}FixOnlineInfoActivity"
        const val OneClickRescueActivity = "${PKG_PROFILE}OneClickRescueActivity"
        const val RescueRecordActivity = "${PKG_PROFILE}RescueRecordActivity"
        const val RescueInfoActivity = "${PKG_PROFILE}RescueInfoActivity"
        const val StoreListActivity = "${PKG_PROFILE}StoreListActivity"
        const val StoreSearchActivity = "${PKG_PROFILE}StoreSearchActivity"
        const val StoreInfoActivity = "${PKG_PROFILE}StoreInfoActivity"
        const val StoreEvaluateActivity = "${PKG_PROFILE}StoreEvaluateActivity"
        const val MyModelListActivity = "${PKG_PROFILE}MyModelListActivity"
        const val HelperCenterActivity = "${PKG_PROFILE}HelperCenterActivity"
        const val MessageSwitchActivity = "${PKG_PROFILE}MessageSwitchActivity"
        const val DelAccountActivity = "${PKG_PROFILE}DelAccountActivity"
        const val FeedbackActivity = "${PKG_PROFILE}FeedbackActivity"
        const val ResolventActivity = "${PKG_PROFILE}ResolventActivity"
        const val ProfileFaqActivity = "${PKG_PROFILE}ProfileFaqActivity"
        const val WikiActivity = "${PKG_PROFILE}WikiActivity"
        const val WikiPageActivity = "${PKG_PROFILE}WikiPageActivity"
        const val WikiDetailActivity = "${PKG_PROFILE}WikiDetailActivity"
        const val WikiSearchActivity = "${PKG_PROFILE}WikiSearchActivity"
        const val WikiSearchResultActivity = "${PKG_PROFILE}WikiSearchResultActivity"
        const val ProfileFaqSearchActivity = "${PKG_PROFILE}ProfileFaqSearchActivity"
        const val ProfileCarUsageActivity = "${PKG_PROFILE}ProfileCarUsageActivity"
        const val ChangePhoneNumberActivity = "${PKG_PROFILE}ChangePhoneNumberActivity"
        const val MyGarageActivity = "${PKG_PROFILE}MyGarageActivity"
        const val APPShareActivity = "${PKG_PROFILE}APPShareActivity"
        const val PDFViewerActivity = "${PKG_PROFILE}PDFViewerActivity"
        const val BindWeChatActivity = "${PKG_PROFILE}BindWeChatActivity"
        const val BookingInfoActivity = "${PKG_PROFILE}BookingInfoActivity"
        const val BookingVehicleListActivity = "${PKG_PROFILE}BookingVehicleListActivity"
        const val BookingVehicleListV2Activity = "${PKG_PROFILE}BookingVehicleListV2Activity"
        const val BookingListActivity = "${PKG_PROFILE}BookingListActivity"
        const val ChangePwdActivity = "${PKG_PROFILE}ChangePwdActivity"
        const val AboutUsActivity = "${PKG_PROFILE}AboutUsActivity"
        const val VersionIntroActivity = "${PKG_PROFILE}VersionIntroActivity"
        const val FlowRechargeActivity = "${PKG_PROFILE}FlowRechargeActivity"
        const val FlowPaymentWebActivity = "${PKG_PROFILE}FlowPaymentWebActivity"
        const val FlowRechargeListActivity = "${PKG_PROFILE}FlowRechargeListActivity"
        const val FlowRechargeInfoActivity = "${PKG_PROFILE}FlowRechargeInfoActivity"
        const val SubmitResultActivity = "${PKG_PROFILE}SubmitResultActivity"
        const val ContactServiceActivity = "${PKG_PROFILE}ContactServiceActivity"

        //个人中心优惠券
        const val MallCouponActivity = "${PKG_MALL}MallCouponActivity"

        /** 个人中心相关页面-2 */

        // ********************************** 商城相关1 ***********************************
        // 商城搜索
        const val MallSearchActivity = "${PKG_MALL}MallSearchActivity"

        // 商城搜索-结果
        const val MallSearchResultActivity = "${PKG_MALL}MallSearchResultActivity"

        // 分组商品列表
        const val MallGroupGoodsListActivity = "${PKG_MALL}MallGroupGoodsListActivity"

        // 地址管理
        const val AddressMgrActivity = "${PKG_MALL}AddressMgrActivity"

        // 编辑地址
        const val EditAddressActivity = "${PKG_MALL}AddAddressActivity"

        // 商城订单
        const val MallOrderActivity = "${PKG_MALL}MallOrderActivity"

        // 商城订单-Search
        const val MallOrderSearchActivity = "${PKG_MALL}MallOrderSearchActivity"

        // 购物车
        const val ShoppingCarActivity = "${PKG_MALL}ShoppingCarActivity"

        // 限时秒杀列表
        const val SeckillListActivity = "${PKG_MALL}SeckillListActivity"

        // 热门活动列表
        const val ActListActivity = "${PKG_MALL}ActListActivity"

        // 商品详情
        const val GoodsDetailActivity = "${PKG_MALL}GoodsDetailActivity"
        const val SeckillGoodsDetailActivity = "${PKG_MALL}SeckillGoodsDetailActivity"

        // 车型详情
        const val MallMotorSeriesDetailActivity = "${PKG_MALL}MallMotorSeriesDetailActivity"
        const val MallHomeDataActivity = "${PKG_MALL}MallHomeDataActivity"

        // 活动详情
        const val ActDetailActivity = "${PKG_MALL}ActDetailActivity"

        // 全部评论列表
        const val EvaluationListActivity = "${PKG_MALL}EvaluationListActivity"

        // 发布评论
        const val CreateEvaluationActivity = "${PKG_MALL}CreateEvaluationActivity"

        // 确认订单
        const val OrderConfirmActivity = "${PKG_MALL}OrderConfirmActivity"
        const val DouOrderConfirmActivity = "${PKG_MALL}DouOrderConfirmActivity"
        const val CartOrderConfirmActivity = "${PKG_MALL}CartOrderConfirmActivity"

        // 下单结果
        const val OrderResultActivity = "${PKG_MALL}OrderResultActivity"

        // 我的-收藏
        const val MallFavoriteListActivity = "${PKG_MALL}MallFavoriteListActivity"

        // 商城订单详情
        const val MallOrderDetailActivity = "${PKG_MALL}MallOrderDetailActivity"
        const val DouOrderDetailActivity = "${PKG_MALL}DouOrderDetailActivity"

        // 申请退款
        const val ApplyRefundActivity = "${PKG_MALL}ApplyRefundActivity"

        // 申请售后
        const val ApplyAfterSalesActivity = "${PKG_MALL}ApplyAfterSalesActivity"

        // 售后详情
        const val AfterSalesDetailActivity = "${PKG_MALL}AfterSalesDetailActivity"

        // 物流信息
        const val ExpressTimeLineActivity = "${PKG_MALL}ExpressTimeLineActivity"
        const val ExpressTimeLineVpActivity = "${PKG_MALL}ExpressTimeLineVpActivity"

        // 物流公司列表
        const val MallExpressCompanyListActivity = "${PKG_MALL}MallExpressCompanyListActivity"

        // 输入运单号
        const val MallInputExpressInfoActivity = "${PKG_MALL}MallInputExpressInfoActivity"

        // 龙豆主页
        const val LongDouHomeActivity = "${PKG_MALL}LongDouHomeActivity"

        // 龙豆历史记录
        const val LongDouHistoryActivity = "${PKG_MALL}LongDouHistoryActivity"

        // 龙豆任务
        const val LongDouTaskActivity = "${PKG_MALL}LongDouTaskActivity"

        // 龙豆订单
        @Deprecated("use MallOrderDetailActivity instead")
        const val LongDouOrderDetailActivity = "${PKG_MALL}LongDouOrderDetailActivity"

        // 龙豆虚拟订单
        @Deprecated("use MallOrderDetailActivity instead")
        const val LongDouVirtualDetailActivity = "${PKG_MALL}LongDouVirtualDetailActivity"
        // ********************************** 商城相关2 ***********************************
        // ********************************** 路线攻略 ***********************************
        /**路线攻略列表*/
        const val RouteGuideListActivity = "/ui/route/RouteListActivity"

        /**路线攻略搜索*/
        const val RouteGuideSearchActivity = "/ui/route/RouteSearchActivity"

        /**路线攻略搜索结果*/
        const val RouteGuideResultActivity = "/ui/route/RouteGuideResultActivity"

        /**创建路线攻略*/
        const val RouteGuideCreateActivity = "/ui/route/RouteGuideCreateActivity"

        /**编辑路线攻略*/
        const val RouteGuideEditActivity = "/ui/route/RouteGuideEditActivity"

        /**路线攻略编辑*/
        const val EditRouteCoverActivity = "/ui/route/EditRouteCoverActivity"


        /**路线编辑*/
        const val RouteEditActivity = "/ui/route/RouteEditActivity"

        /**路线详情*/
        const val RouteInfoActivity = "/ui/route/RouteInfoActivity"

        /**路径规划*/
        const val RouteCalculateActivity = "/ui/route/RouteCalculateActivity"
        // ********************************** 路线攻略 ***********************************

        // ********************************** IM自定义 ***********************************
        const val SelectChatActivity = "/ui/im/SelectChatActivity"
        const val MyContactSelectorActivity = "/ui/im/MyContactSelectorActivity"
        // ********************************** IM自定义 ***********************************

        // ********************************** 好友-1 ***********************************
        // 朋友/粉丝/关注
        const val FriendsActivity = "/ui/friends/FriendsActivity"

        // 我的朋友
        const val MyFriendsListActivity = "/ui/friends/MyFriendsListActivity"

        // 附件的人
        const val NearbyListActivity = "/ui/friends/NearbyListActivity"

        // 推荐朋友
        const val RecommendFriendsListActivity = "/ui/friends/RecommendFriendsListActivity"

        // 我的收藏
        const val MultiCollectionActivity = "/ui/collection/MultiCollectionActivity"

        // 资讯详情
        const val InformationDetailActivity = "/ui/collection/InformationDetailActivity"

        // 我的足迹
        const val MallHistoryListActivity = "${PKG_MALL}MallHistoryListActivity"
        // ********************************** 好友-2 ***********************************

        // ********************************** 俱乐部v2 ***********************************
        const val ClubListActivity = "${PKG_CLUB_V2}ClubListActivity"

        // ********************************** 俱乐部活动v2 ***********************************

        //俱乐部活动列表
        const val ClubActivityListActivity = "${PKG_ACT_V2}ClubActivityListActivity"

        //俱乐部活动搜索页面
        const val ClubActivitySearchActivity = "${PKG_ACT_V2}ClubActivitySearchActivity"

        //俱乐部活动详情页面
        const val ClubActivityDetialActivity = "${PKG_ACT_V2}ClubActivityDetialActivity"


        // ********************************** 团油加油服务 ***********************************
        //团油加油服务WebView
        const val TYH5GasWebActivity = "/ui/TYH5GasWebActivity"
    }

}