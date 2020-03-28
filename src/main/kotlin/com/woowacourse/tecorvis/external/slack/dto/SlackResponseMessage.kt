package com.woowacourse.tecorvis.external.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SlackResponseMessage(
        val text: String?,
        val username: String?,
        @JsonProperty("bot_id")
        val botId: String?,
        val attachments: List<SlackResponseContent>? = listOf(),
        val type: String?,
        val subtype: String?,
        val ts: String?
)