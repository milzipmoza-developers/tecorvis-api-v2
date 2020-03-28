package com.woowacourse.tecorvis.external.slack

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class SlackWebClient(
        @Value("\${slack.authentication}") val authentication: String,
        @Value("\${slack.baseUri}") val baseUri: String
) {

    companion object {
        const val SLACK_BOT_WEB_CLIENT = "slackBotWebClient"
        const val CONTENT_TYPE = "Content-Type"
        const val AUTHORIZATION = "Authorization"
        const val APPLICATION_JSON_UTF8 = "application/json;utf-8"
    }

    @Bean(SLACK_BOT_WEB_CLIENT)
    fun webClient(builder: WebClient.Builder): WebClient {
        return builder
                .apply {
                    it.defaultHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8)
                            .defaultHeader(AUTHORIZATION, "Bearer $authentication")
                            .baseUrl(baseUri)
                }
                .build()
    }
}