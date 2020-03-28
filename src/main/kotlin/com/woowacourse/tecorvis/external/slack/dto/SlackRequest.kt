package com.woowacourse.tecorvis.external.slack.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SlackRequest(
        @JsonProperty("channel")
        val channel: String,
        @JsonProperty("text")
        val message: String
)