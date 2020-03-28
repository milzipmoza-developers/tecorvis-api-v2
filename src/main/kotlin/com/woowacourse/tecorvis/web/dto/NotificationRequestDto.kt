package com.woowacourse.tecorvis.web.dto

data class NotificationRequestDto(
        val title: String?,
        val author: String?,
        val publisher: String?,
        val isbn: String?,
        val createdAt: String?
)
