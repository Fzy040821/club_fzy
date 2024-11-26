package com.bw.kf.club_fengzy.ui.main

import com.bw.kf.club_fengzy.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    //一个空的API
    fun blankAPI() = flow {
        emit(apiService.blankApi())
    }

    fun updateApp() = flow {
        delay(1_000)
        emit(apiService.appUpdate())
    }.flowOn(Dispatchers.IO)
}