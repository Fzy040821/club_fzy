package com.bw.kf.club_fengzy.profile


import com.bw.kf.club_fengzy.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

/**
 *@author Wcj
 *@description
 *@date 2022/6/17 16:24
 */
open class ProfileRepository @Inject constructor() {
    @Inject
    lateinit var apiService: ApiService


    fun uploadImages(files: List<File>) = flow {
        val partList = mutableListOf<MultipartBody.Part>()
        files.forEach {
            val body = RequestBody.create("image/*".toMediaTypeOrNull(), it)
            val part: MultipartBody.Part = MultipartBody.Part.createFormData("file", it.name, body)
            partList.add(part)
        }
        emit(apiService.upLoadFile(partList))
    }

//    /** 根据父级编码查询子级-2022年08月26日 */
//    fun getAreaByParent(parent: String = "") = flow {
//        emit(apiService.getAreaByParent(parent))
//    }
//
//    fun getMinePageData() = flow {
//        emit(apiService.getMinePageData())
//    }.flowOn(Dispatchers.IO)

    fun updateApp() = flow {
        emit(apiService.appUpdate())
    }

//    fun getAllBindMoto() = flow {
//        emit(apiService.getAllBindMoto())
//    }
//
//    /** 更新信息 */
//    fun updateVehicleInfo(body: VehicleUpdateBody) = flow {
//        emit(apiService.updateVehicleInfo(body.toRequestBody()))
//    }
//
//    /** 设置默认 */
//    fun updateDftVehicle(body: VehicleUpdateBody) = flow {
//        emit(apiService.updateDftVehicle(body.toRequestBody()))
//    }
//
//    /** 发送解绑验证码 */
//    fun sendVehicleUnBindSms(body: VehicleUnbindBody) = flow {
//        emit(apiService.sendVehicleUnBindSms(body.memberId ?: "", body.vehicleId ?: ""))
//    }
//
//    /** 解除绑定 */
//    fun vehicleUnbind(body: VehicleUnbindBody) = flow {
//        emit(apiService.vehicleUnbind(body.toRequestBody()))
//    }
//
//    /** 修改密码 */
//    fun updatePwd(body: UpdateAccountInfoBody) = flow {
//        emit(apiService.updatePwd(body.toRequestBody()))
//    }
//
//    /** 修改手机号验证码 */
//    fun sendUpdatePhoneSMS(phone: String) = flow {
//        emit(apiService.sendUpdatePhoneSMS(phone))
//    }
//
//    /** 修改手机号验证码 */
//    fun sendBindSmsCode(phone: String) = flow {
//        emit(apiService.getBindSmsCode(phone))
//    }
//
//    /** 校验验证码*/
//    fun validationCode(phone: String, code: String) = flow {
//        val map = mapOf("cellphone" to phone, "smsCode" to code)
//        emit(
//            apiService.validationCode(
//                RequestBody.create(
//                    "application/json".toMediaType(),
//                    GSON.toJson(map)
//                )
//            )
//        )
//    }
//
//    /** 验证验证码 */
//    fun checkUpdatePhoneSms(phone: String, code: String) = flow {
//        emit(apiService.checkUpdatePhoneSms(phone, code))
//    }
//
//    /** 修改手机号 */
//    fun updatePhoneNumber(body: UpdateAccountInfoBody) = flow {
//        emit(apiService.updatePhoneNumber(body.toRequestBody()))
//    }
//
//    /** 保存意见反馈 */
//    fun saveFeedback(content: ContentBody) = flow {
//        emit(apiService.saveFeedback(content.toRequestBody()))
//    }
//
//    /** 分页查询客服列表 */
//    fun pageServicePhone(type: String = "") = flow {
//        emit(apiService.pageServicePhone(serviceType = type))
//    }
//
//    fun findUserInfo() = flow {
//        emit(apiService.findUserInfo())
//    }
//
//    fun updateMemberInfo(body: UserMemberInfoBody) = flow {
//        emit(apiService.updateMemberInfo(body.toRequestBody()))
//    }
//
//    /** 保存售后申请 */
//    fun saveConcatServer(body: AfterSalesSaveBody) = flow {
//        emit(apiService.saveConcatServer(body.toRequestBody()))
//    }
//
//    /** 查看售后申请 */
//    fun findConcatServerInfo(id: String) = flow {
//        emit(apiService.findConcatServerInfo(id))
//    }
//
//    /** 分页售后申请记录 */
//    fun pageConcatServer(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageConcatServer(page, pageSize))
//    }
//
//    /** 保存试驾 */
//    fun saveBookingInfo(body: BookingSaveBody) = flow {
//        emit(apiService.saveBookingInfo(body.toRequestBody()))
//    }
//
//    /** 保存试驾 */
//    fun saveFlowRecharge(body: FlowRechargeSaveBody) = flow {
//        emit(apiService.saveFlowRecharge(body.toRequestBody()))
//    }
//
//    /** 查看试驾 */
//    fun findBookingInfo(id: String) = flow {
//        emit(apiService.findBookingInfo(id))
//    }
//
//    fun flowRechargeInfo(orderNo: String) = flow {
//        emit(apiService.flowRechargeInfo(orderNo))
//    }
//
//    /** 分页试驾申请记录 */
//    fun pageBookingHistory(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageBookingHistory(page, pageSize))
//    }
//
//    /** 试驾车型列表 */
//    fun pageBookingModels(page: Int, pageSize: Int, keyword: String = "") = flow {
//        emit(apiService.pageBookingModels(page, pageSize, keyword))
//    }
//
//    /** 保存失窃上报 */
//    fun saveStolenInfo(body: StolenSaveBody) = flow {
//        emit(apiService.saveStolenInfo(body.toRequestBody()))
//    }
//
//    /** 已找到车 */
//    fun dealStolenCar(id: String) = flow {
//        emit(apiService.dealStolenCar(id))
//    }
//
//    /** 失窃上报详情 */
//    fun findStolenInfo(id: String) = flow {
//        emit(apiService.findStolenInfo(id))
//    }
//
//    /** 分页失窃上报记录 */
//    fun pageStolenHistory(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageStolenHistory(page, pageSize))
//    }
//
//    /** 充值记录 */
//    fun pageFlowHistory(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageFlowHistory(page, pageSize))
//    }
//
//    /** 常见问题 */
//    fun faqTypeList() = flow {
//        emit(apiService.faqTypeList())
//    }
//
//    /** 常见问题 */
//    fun wikiTypeList() = flow {
//        emit(apiService.wikiTypeList())
//    }
//
//    /** 资讯类型 */
//    fun infoTypeList() = flow {
//        emit(apiService.infoTypeList())
//    }
//
//    /** 资讯列表 */
//    fun pageInformation(page: Int, pageSize: Int, typeId: Long) = flow {
//        emit(apiService.pageInformation(page, pageSize, typeId))
//    }
//
//    fun pageCollectionInformation(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageCollectionInformation(page, pageSize))
//    }
//
//    fun pageLikedInformation(page: Int, pageSize: Int) = flow {
//        emit(apiService.pageLikedInformation(page, pageSize))
//    }
//
//    /** 我的徽章列表 */
//    fun listMyMedal(memberId: String) = flow {
//        if (memberId.isEmpty())
//            emit(apiService.listMyMedal())
//        else
//            emit(apiService.listMedal(memberId))
//    }
//
//    fun pageShareStyle() = flow {
//        emit(apiService.pageShareStyle())
//    }
//
//    fun pageFaqList(page: Int = 1, pageSize: Int = 10, typeId: String = "", name: String = "") =
//        flow {
//            emit(apiService.pageFaqList(page, pageSize, typeId, name))
//        }
//
//    fun pageWikiList(page: Int = 1, pageSize: Int = 10, typeId: String = "", name: String = "") =
//        flow {
//            emit(apiService.pageWikiList(page, pageSize, typeId, name))
//        }
//
//    fun findWikiById(id: String) = flow {
//        emit(apiService.findWikiById(id))
//    }
//
//    fun findWikiTitleList(keyword: String) = flow {
//        emit(apiService.findWikiTitleList(keyword))
//    }
//
//    /** 使用说明 */
//    fun pageDirectionUsage(page: Int = 1, pageSize: Int = 10, type: Int) = flow {
//        emit(apiService.pageDirectionUsage(page, pageSize, type))
//    }
//
//    /** 流量配置 */
//    fun flowConfigList() = flow {
//        emit(apiService.flowConfigList())
//    }
//
//    /** 更新微信OpenId */
//    fun updateWxOpenId(body: BindWechatBody) = flow {
//        emit(apiService.updateWxOpenId(body.toRequestBody()))
//    }
//
//    /** 申请注销 */
//    fun applyDelAccount(memberId: String, reason: String) = flow {
//        emit(apiService.applyDelAccount(memberId, reason))
//    }
//
//    /** 取消申请注销 */
//    fun applyCancelDelAccount(memberId: String) = flow {
//        emit(apiService.cancelApplyDelAccount(memberId))
//    }
//
//    /** 注销记录 */
//    fun applyDelAccountList() = flow {
//        emit(apiService.applyDelAccountList())
//    }
//
//    /***************************1.2.0 售后部分API**************************/
//
//    @Inject
//    lateinit var openApiService: MallApiService
//
//    /** 获取OSS上传文件签名信息 */
//    fun getOssSignature() = flow {
//        emit(openApiService.getOssSignature())
//    }
//
//    /**门店分页查询*/
//    fun storePageQuery(param: StorePageBody) = flow {
//        emit(openApiService.storePageQuery(param.toRequestBody()))
//    }
//
//    //城市查询
//    fun sysAreQuerySon(body: CommonPageBody) = flow {
//        emit(openApiService.sysAreQuerySon(body.toRequestBody()))
//    }
//
//    /**门店详情*/
//    fun storeInfo(param: CommonSimpleBody) = flow {
//        emit(openApiService.storeInfo(param.toRequestBody()))
//    }
//
//    /**门店评价列表*/
//    fun storeEvaluateList(param: StoreEvaluatePageBody) = flow {
//        emit(openApiService.storeEvaluateList(param.toRequestBody()))
//    }
//
//    /**门店评价*/
//    fun storeEvaluate(body: StoreEvaluateBody) = flow {
//        emit(openApiService.storeEvaluate(body.toRequestBody()))
//    }
//
//    /**门店评价举报*/
//    fun storeEvaluateReport(body: StoreEvaluationReportBody) = flow {
//        emit(openApiService.storeEvaluateReport(body.toRequestBody()))
//    }
//
//    /**在线报修 - 提交维修信息*/
//    fun repairRecordReservation(param: OnlineFixBody) = flow {
//        emit(openApiService.repairRecordReservation(param.toRequestBody()))
//    }
//
//    /**维修记录*/
//    fun repairRecordList(param: CommonPageBody) = flow {
//        emit(openApiService.repairRecordList(param.toRequestBody()))
//    }
//
//    /**维修详情*/
//    fun repairRecordInfo(param: CommonSimpleBody) = flow {
//        emit(openApiService.repairRecordInfo(param.toRequestBody()))
//    }
//
//    /**取消维修*/
//    fun repairRecordRepeal(param: CommonSimpleBody) = flow {
//        emit(openApiService.repairRecordRepeal(param.toRequestBody()))
//    }
//
//
//    /**保养服务与 - 预约*/
//    fun maintainReservation(param: CareServiceBody) = flow {
//        emit(openApiService.maintainReservation(param.toRequestBody()))
//    }
//
//    /**保养记录*/
//    fun maintainList(param: CommonPageBody) = flow {
//        emit(openApiService.maintainList(param.toRequestBody()))
//    }
//
//    /**保养详情*/
//    fun maintainInfo(param: CommonSimpleBody) = flow {
//        emit(openApiService.maintainInfo(param.toRequestBody()))
//    }
//
//    /**取消保养*/
//    fun maintainRepeal(param: CommonSimpleBody) = flow {
//        emit(openApiService.maintainRepeal(param.toRequestBody()))
//    }
//
//    /**保养时间*/
//    fun maintainNextTime(body: MaintainNextTimeBody) = flow {
//        emit(openApiService.maintainNextTime(body.toRequestBody()))
//    }
//
//    /**预约试驾*/
//    fun bookingTestDriver(body: BookingDriverBody) = flow {
//        emit(openApiService.bookingTestDriver(body.toRequestBody()))
//    }
//
//
//    /**试驾车型*/
//    fun testDriverModel(body: TestDriverModelBody) = flow {
//        emit(openApiService.testDriverModel(body.toRequestBody()))
//    }
//
//    /**试驾验证码*/
//    fun testDriveSMSCode(body: TestDriverSmsCodeBody) = flow {
//        emit(openApiService.testDriveSMSCode(body.toRequestBody()))
//    }
//
//    /**一键救援创建*/
//    fun rescueCreate(body: RescueCreateBody) = flow {
//        emit(openApiService.rescueCreate(body.toRequestBody()))
//    }
//
//    /**一键救援取消*/
//    fun rescueCancel(body: CommonSimpleBody) = flow {
//        emit(openApiService.rescueCancel(body.toRequestBody()))
//    }
//
//    /**一键救援详情*/
//    fun rescueInfo(body: CommonSimpleBody) = flow {
//        emit(openApiService.rescueInfo(body.toRequestBody()))
//    }
//
//    /**一键救援记录*/
//    fun rescueQuery(body: RescueQueryBody) = flow {
//        emit(openApiService.rescueQuery(body.toRequestBody()))
//    }
//
//    /**一键救援状态*/
//    fun rescueWait(body: CommonSimpleBody) = flow {
//        emit(openApiService.rescueWait(body.toRequestBody()))
//    }
//
//    /**openApi车辆绑定*/
//    fun archiveBinding(frameNo: String) = flow {
//        val body = RequestBody.create(
//            "application/json".toMediaType(),
//            GSON.toJson(mapOf("frameNo" to frameNo))
//        )
//        emit(openApiService.archiveBinding(body))
//    }
//
//    /**openApi解除车辆*/
//    fun archiveUnbinding(frameNo: String) = flow {
//        val body = RequestBody.create(
//            "application/json".toMediaType(),
//            GSON.toJson(mapOf("frameNo" to frameNo))
//        )
//        emit(openApiService.archiveUnbinding(body))
//    }
//
//    /** c端-商品详情-推荐 */
//    fun mallGoodsInfoInfo(param: CommonSimpleBody) = flow {
//        emit(openApiService.mallGoodsInfoInfo(param.toRequestBody()))
//    }
//
//    /** 预约试驾 */
//    fun goodsAttributeSerApiService(param: BookingVehicleBody) = flow {
//        emit(openApiService.goodsAttributeSerApiService(param.toRequestBody()))
//    }
//
//    /**退出登录*/
//    fun logout() = flow {
//        emit(apiService.logout())
//    }
//
//    /***************** V2好友相关-1 ****************/
//    /** 推荐好友*/
//    fun recommendFriendsList() = flow {
//        emit(apiService.recommendFriendsList())
//    }
//
//    fun findFriendsByType(type: Int, page: Int, pageSize: Int, nickName: String = "") = flow {
//        when (type) {
//            UserRelationEnum.FANS.relation -> {
//                emit(apiService.findMyFans(page, pageSize, nickName))
//            }
//
//            UserRelationEnum.FOCUS.relation -> {
//                emit(apiService.findMyFollow(page, pageSize, nickName))
//            }
//
//            UserRelationEnum.FRIENDS.relation -> {
//                emit(apiService.findMyFriends(page, pageSize, nickName))
//            }
//        }
//    }
//
//    fun pageNearbyUsers(param: NearbyUserQueryParam) = flow {
//        emit(apiService.pageNearbyUsers(param.pageNum, param.pageSize, param.gender, param.order))
//    }
//
//    fun followUser(memberId: String) = flow {
//        emit(apiService.followUser(memberId))
//    }
//
//    fun followMulti(memberIds: List<String>) = flow {
//        val body = MultiFollowBody(memberIds)
//        emit(apiService.followMulti(body.toRequestBody()))
//    }
//
//    fun saveMyLocation(body: SaveMyLocationBody) = flow {
//        emit(apiService.saveMyLocation(body.toRequestBody()))
//    }
//
//    fun cancelFollow(memberId: String) = flow {
//        emit(apiService.cancelFollow(memberId))
//    }
//
//    fun checkIfHasFocus() = flow {
//        emit(apiService.checkIfHasFocus())
//    }
//    /***************** V2好友相关-2 ****************/
//
//    /***************** V2收藏相关-1 ****************/
//    fun pageCollectionPost() = flow {
//        emit(apiService.pageCollectionPost())
//    }
//
//    /** 批量删除 */
//    fun batchDeleteCollection(body: BatchDelCollectionBody) = flow {
//        emit(apiService.batchDeleteCollection(body.toRequestBody()))
//    }
//
//    /***************** V2收藏相关-2 ****************/
//
//
//    /***************** V2聊天群相关 ****************/
//
//    fun createChatTeam(chatTeamBody: ChatTeamBody) = flow {
//        emit(apiService.createChatTeam(chatTeamBody.toRequestBody()))
//    }
//
//    fun createChatTeamInvitation(chatTeamBody: ChatTeamBody) = flow {
//        emit(apiService.createChatTeamInvitation(chatTeamBody.toRequestBody()))
//    }
//
//    fun dissolveChatTeam(teamId: String) = flow {
//        emit(apiService.dissolveChatTeam(teamId))
//    }
//
//    fun muteChatTeam(teamId: String, ifMute: Int) = flow {
//        emit(apiService.muteChatTeam(teamId, ifMute))
//    }
//
//    fun leaveChatTeam(teamId: String) = flow {
//        emit(apiService.leaveChatTeam(teamId))
//    }
//
//    fun kickChatTeam(req: KickChatTeamBody) = flow {
//        emit(apiService.kickChatTeam(req.toRequestBody()))
//    }
//
//    fun changeChatTeamName(req: ChangeChatTeamNameBody) = flow {
//        emit(apiService.changeChatTeamName(req.toRequestBody()))
//    }
//
//    fun checkContent(content: String) = flow {
//        emit(apiService.checkContent(content))
//    }
//
//    fun checkCanSendMessage(content: String) = flow {
//        emit(apiService.checkCanSendMessage(content))
//    }


    /***************** V2聊天群相关 ****************/
}

