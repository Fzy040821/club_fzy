package com.bw.kf.club_fengzy.base

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.whenStateAtLeast
import com.bw.kf.club_fengzy.mmkv.AuthMMKV
import com.jczy.lib_net.exception.ApiCode
import com.jczy.lib_net.exception.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    fun <M: BaseModel<T> , T> execute(
        action: Flow<M>,
        onStart: (suspend ()->Unit)?=null,
        onError: (suspend (String)->Unit)?=null,
        onFinished:(suspend (M)->Unit)? = null,
        handleApiExceptionBySelf: Boolean = false
    ) = viewModelScope.launch {
        action.onStart {
            onStart?.invoke()
        }.handleApiException2Model(handleApiExceptionBySelf).collect{
            if(it.code == ApiCode.OK){
                //完成网络请求的处理
                onFinished?.invoke(it)
            }else{
                if(it.code == ApiCode.LOGIN_TIMEOUT){
                    AuthMMKV.accessToken = ""
                    if(it.msg == "令牌不能为空"){
                        onError?.invoke("")
                        return@collect
                    }
                }
                onError?.invoke(it.msg)
            }
        }
    }

}

fun <M : BaseModel<T>, T> Flow<M>.handleApiException2Model(handleApiExceptionBySelf: Boolean = false) =
    flowOn(Dispatchers.IO).onEach { model ->
        if (model.code != ApiCode.OK && !handleApiExceptionBySelf) {
            throw  ApiException.getApiException(model.code, model.msg)
        }
    }.catch { e ->
        e.printStackTrace()
        val apiException = if (e !is ApiException) {
            ApiException.getApiException(e)
        } else {
            e
        }
        emit(BaseModel(apiException.code, apiException.message, null) as M)
    }.flowOn(Dispatchers.Main)


inline fun <reified T> StateFlow<T>.observeWithLifecycle(
    fragment: Fragment,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit,
): Job = fragment.viewLifecycleOwner.lifecycleScope.launch {
    fragment.viewLifecycleOwner.lifecycle.whenStateAtLeast(minState){
        collectLatest {
            action.invoke(it)
        }
    }
}

inline fun <reified T> Flow<T>.observeWithLifecycle(
    fragment: Fragment,
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit,
): Job = fragment.viewLifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(fragment.viewLifecycleOwner.lifecycle, minState).collectLatest {
        action.invoke(it)
    }
}