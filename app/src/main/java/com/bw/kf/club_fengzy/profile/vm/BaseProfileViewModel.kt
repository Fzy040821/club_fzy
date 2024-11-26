package com.bw.kf.club_fengzy.profile.vm


import com.bw.kf.club_fengzy.base.BaseViewModel
import com.bw.kf.club_fengzy.model.UploadImageModel
import com.bw.kf.club_fengzy.profile.ProfileRepository
import com.bw.kf.club_fengzy.state.Failed
import com.bw.kf.club_fengzy.state.Loading
import com.bw.kf.club_fengzy.state.RequestEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.concurrent.CancellationException

open class BaseProfileViewModel(
    private val mRepository: ProfileRepository
) : BaseViewModel() {
    companion object {
        const val SUCCESS_STR = "SUCCESS"
    }

    protected val _stateListener = MutableSharedFlow<RequestEvent>()
    val stateListener get() = _stateListener.asSharedFlow()

    /** 批量上传文件 */
    fun uploadFiles(files: List<File>, onUploadSuccess: (data: UploadImageModel) -> Unit) {
        execute(
            action = mRepository.uploadImages(files),
            onStart = {
                _stateListener.emit(Loading)
            },
            onFinished = { model ->
                if (model.data != null) {
                    onUploadSuccess(model.data!!)
                }
            },
            onError = {
                _stateListener.emit(Failed(it))
            }
        )
    }

//    fun sysAreQuerySon(
//        areaId: Long, parent: String = "",
//        onSuccess: (list: MutableList<CityCodeData>) -> Unit,
//        onFailed: (parent: String, msg: String) -> Unit
//    ) {
//        executeOpenApi(action = mRepository.sysAreQuerySon(CommonPageBody(areaId = areaId, limit = 100, start = 1)),
//            onApiError = { onFailed.invoke(parent, "加载失败") },
//            onRequestError = { onFailed.invoke(parent, "加载失败") },
//            onFinished = {
//                _stateListener.emit(Success)
//                // cityCodeLiveData.postValue(it.children)
//                val list = it.children?.toMutableList()
//                onSuccess.invoke(list ?: mutableListOf())
//            })
//    }

//    /**上传图片*/
//    /** 获取OSS上传文件签名信息 */
//    private fun getOssSignature(onSuccess: suspend (sign: OssSignResponse) -> Unit) {
//        executeOssSign(
//            action = mRepository.getOssSignature(),
//            onStart = { _stateListener.emit(Loading) },
//            onRequestError = { _stateListener.emit(Failed(it.detail + "请求错误")) },
//            onFinished = {
//                //  _stateListener.emit(Success)
//                onSuccess.invoke(it)
//            }
//        )
//    }

//    fun onUploadFiles2OSS(
//        files: List<File>,
//        onSuccess: (resultMap: HashMap<Int, Long>) -> Unit,
//        onFailed: (msg: String) -> Unit
//    ) {
//        val resultMap by lazy {
//            HashMap<Int, Long>()
//        }
//        // 从接口获取上传参数
//        getOssSignature { sign ->
//            withContext(Dispatchers.IO) {
//                // 由于不支持批量上传，执行循环单个上传
//                files.forEachIndexed { index, file ->
//                    val temp = file.formatImage()
//                    uploadFile2OSSByPost(temp, sign, onSuccess = {
//                        resultMap[index] = it
//                        if (resultMap.size >= files.size) {
//                            onSuccess.invoke(resultMap)
//                        }
//                    }, onFailed = {
//                        onFailed.invoke(it)
//                        //失败直接中断循环请求
//                        try {
//                            coroutineContext.cancel(CancellationException(it))
//                        } catch (e: CancellationException) {
//                        }
//                    })
//                }
//
//            }
//        }
//    }
//
//    /** 使用OKHttp上传图片到服务器 */
//    private fun uploadFile2OSSByPost(
//        file: File, sign: OssSignResponse,
//        onSuccess: (id: Long) -> Unit,
//        onFailed: (msg: String) -> Unit
//    ) {
//        // 创建表单参数
//        val requestBody: RequestBody = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("success_action_status", "200")
//            .addFormDataPart("key", sign.randomObjectKey(file))
//            .addFormDataPart("policy", sign.policy)
//            .addFormDataPart("OSSAccessKeyId", sign.accessid)
//            .addFormDataPart("Signature", sign.signature)
//            .addFormDataPart("callback", sign.callback)
//            .addFormDataPart("Filename", file.name)
//            .addFormDataPart(
//                "file", file.name, RequestBody.create(
//                    "application/octet-stream".toMediaTypeOrNull(),
//                    File(file.absolutePath)
//                )
//            ).build()
//
//        // 创建请求
//        val request: Request = Request.Builder()
//            .url(sign.host)
//            .post(requestBody)
//            .build()
//
//        // 发送请求并获取响应
//        // 有关图片接口返回数据 会按找resourceId的顺序来返回，
//        // 上传顺序为图1 图2 图3，完成顺序为图2 图1 图3 ,接口结果返回图2 图1 图3 ，至少在线保修那里是这样的
//        // 只能按顺序来，第一张传完了传第二张，修改为okhttp同步请求的代码，一个一个执行
//        try {
//            val response = okHttpClient.newCall(request).execute()
//            val json = response.body?.string() ?: "{}"
//            JLogUtil.d("response", json)
//            try {
//                val resp = GSON.formJson(json, OssUploadResponse::class.java)
//                onSuccess.invoke(resp.resourceId)
//            } catch (e: Exception) {
//                onFailed.invoke(json)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            onFailed.invoke(e.message ?: "上传失败")
//        }


//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                // TODO 从response里面提取需要的字段
//                val json = response.body()?.string() ?: "{}"
//                JLogUtil.d("response", json)
//                try {
//                    val resp = GSON.formJson(json, OssUploadResponse::class.java)
//                    onSuccess.invoke(resp.resourceId)
//                } catch (e: Exception) {
//                   onFailed.invoke(json)
//                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                onFailed.invoke(e.message ?: "上传失败")
//            }
//        })
//    }


}