package com.bw.kf.club_fengzy.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bw.kf.club_fengzy.base.BaseViewModel
import com.bw.kf.club_fengzy.model.MotoCircleNavigationEnum
import com.bw.kf.club_fengzy.model.MotoCircleNavigationItem
import com.bw.kf.club_fengzy.model.UpdateModel
import com.bw.kf.club_fengzy.state.Default
import com.bw.kf.club_fengzy.state.RequestEvent
import com.bw.kf.club_fengzy.state.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mRepository: MainRepository): BaseViewModel() {
    val mNavigationItem = mutableListOf<MotoCircleNavigationItem>()
    private val _navigationState = MutableStateFlow<RequestEvent>(Default)
    val navigationState
        get() = _navigationState.asStateFlow()

    //版本更新
    val updateLiveData by lazy {
        MutableLiveData<UpdateModel>()
    }
    fun updateApp() {
        execute(action = mRepository.updateApp(),
            onFinished = {
                if (it.data != null)
                    updateLiveData.postValue(it.data)
            }
        )
    }


    //TODO 导航栏 依赖注入
    fun getFixedNavigationItem(){
        val navList = listOf(
            MotoCircleNavigationEnum.HOT,
            MotoCircleNavigationEnum.TOPIC,
            MotoCircleNavigationEnum.INFORMATION,
            MotoCircleNavigationEnum.FOLLOW
        )
        execute(action = mRepository.blankAPI() , onStart = {
            mNavigationItem.clear()
            navList.forEach {
                mNavigationItem.add(
                    MotoCircleNavigationItem(
                        navigationId =it.navId.toString(),
                        sort =it.sort,
                        status = 1,
                        title = it.title,
                        type = it.type,
                        value = it.name
                    )
                )
            }
            _navigationState.emit(Success)
        })

    }

}