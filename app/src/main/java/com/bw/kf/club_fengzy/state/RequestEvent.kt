package com.bw.kf.club_fengzy.state

sealed class RequestEvent
object Success: RequestEvent()
object Default: RequestEvent()
class Failed(val message: String): RequestEvent()
object Loading: RequestEvent()
object Empty: RequestEvent()