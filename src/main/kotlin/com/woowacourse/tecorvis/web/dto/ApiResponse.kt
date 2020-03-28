package com.woowacourse.tecorvis.web.dto

import java.time.LocalDateTime

class ApiResponse<T> (
        val statusCode: Int,
        val message: String,
        val serverDateTime: LocalDateTime,
        val data: T?
) {
    companion object {
        private const val OK_STATUS_CODE = 200
        private const val OK_DEFAULT_MESSAGE = "성공"

        private const val FAILED_STATUS_CODE = 400
        private const val FAILED_DEFAULT_MESSAGE = "실패"

        fun <T> ok(data: T) = ApiResponse(OK_STATUS_CODE, OK_DEFAULT_MESSAGE, LocalDateTime.now(), data)
        fun <T> ok(message: String, data: T) = ApiResponse(OK_STATUS_CODE, message, LocalDateTime.now(), data)

        fun <T> failed(data: T) = ApiResponse(FAILED_STATUS_CODE, FAILED_DEFAULT_MESSAGE, LocalDateTime.now(), data)
        fun <T> failed(message: String, data: T) = ApiResponse(FAILED_STATUS_CODE, message, LocalDateTime.now(), data)
    }

    override fun toString(): String {
        return "ApiResponse(statusCode=$statusCode, message='$message', serverDateTime=$serverDateTime, data=$data)"
    }
}
