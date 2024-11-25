package com.jczy.lib_net.exception

//后续自定义
object ApiCode {
    const val OK = 0
    const val LOGIN_TIMEOUT = 401
    const val SERVER_ERROR = 500

    const val UNKNOWN_ERROR = 1000
    const val PARSE_ERROR = 1001
    const val NET_ERROR = 1002
    const val RESPONSE_EMPTY = 1003
}