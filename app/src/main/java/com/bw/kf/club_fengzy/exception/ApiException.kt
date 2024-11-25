package com.jczy.lib_net.exception

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ApiException constructor(val code: Int, override val message: String) : RuntimeException() {

    companion object {

        fun getApiException(t: Throwable): ApiException {
            return when (t) {
                is JsonParseException, is JsonSyntaxException, is MalformedJsonException -> {
                    ParseException()
                }
                is UnknownHostException, is ConnectException, is SocketTimeoutException -> {
                    NetworkException()
                }
                else -> {
                    UnknownException(message = t.message ?: "其他异常")
                }
            }
        }

        fun getApiException(errorCode: Int): ApiException {
            return when (errorCode) {
                ApiCode.NET_ERROR -> NetworkException()
                ApiCode.PARSE_ERROR -> ParseException()
                ApiCode.RESPONSE_EMPTY -> ResponseEmptyException()
                ApiCode.LOGIN_TIMEOUT -> LoginTimeoutException()
                ApiCode.SERVER_ERROR -> ServerException()
                else -> UnknownException()
            }
        }

        fun getApiException(errorCode: Int, message: String): ApiException {
            return when (errorCode) {
                ApiCode.NET_ERROR -> NetworkException()
                ApiCode.PARSE_ERROR -> ParseException()
                ApiCode.RESPONSE_EMPTY -> ResponseEmptyException()
                ApiCode.LOGIN_TIMEOUT -> LoginTimeoutException(message = message)
                ApiCode.SERVER_ERROR -> ServerException(message = message)
                else -> UnknownException()
            }
        }

        fun getApiException(code: String, message: String): ApiException {
            return UnknownException(message = message)
        }

    }
}


class UnknownException(code: Int = ApiCode.UNKNOWN_ERROR, message: String = "其他异常") : ApiException(code, message)
class ParseException(code: Int = ApiCode.PARSE_ERROR, message: String = "数据解析异常") : ApiException(code, message)
class NetworkException(code: Int = ApiCode.NET_ERROR, message: String = "网络请求异常") : ApiException(code, message)

class ResponseEmptyException(code: Int = ApiCode.RESPONSE_EMPTY, message: String = "请求数据为空") : ApiException(code, message)
class LoginTimeoutException(code: Int = ApiCode.LOGIN_TIMEOUT, message: String = "登录过期") : ApiException(code, message)
class ServerException(code: Int = ApiCode.SERVER_ERROR, message: String = "服务器错误") : ApiException(code, message)