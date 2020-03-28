package com.woowacourse.tecorvis.external.slack.dto

data class SlackResponse(
        val ok: Boolean,
        val channel: String?,
        val ts: String?,
        val message: SlackResponseMessage?,
        val error: String?
)