package com.woowacourse.tecorvis.external.slack

import com.woowacourse.tecorvis.external.slack.dto.SlackRequest
import com.woowacourse.tecorvis.external.slack.dto.SlackResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

/**
 * api docs - https://api.slack.com/methods/chat.postMessage
 */
@Component
class SlackBotModule(
        @Value("\${slack.requestedChannel}")
        val requestedChannel: String,
        @Value("\${slack.enrolledChannel}")
        val enrolledChannel: String,
        @Qualifier(SlackWebClient.SLACK_BOT_WEB_CLIENT)
        val webClient: WebClient
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(SlackBotModule::class.java)
    }

    fun sendWishBookNotification(message: String) = send(requestedChannel, message)
    fun sendLibraryBookNotification(message: String) = send(enrolledChannel, message)

    private fun send(channel: String, message: String): Mono<SlackResponse> {
        return webClient.post()
                .body(BodyInserters.fromValue(SlackRequest(channel, message)))
                .retrieve()
                .bodyToMono(SlackResponse::class.java)
                .flatMap { response: SlackResponse? ->
                    when (response!!.ok) {
                        true -> {
                            log.info("[SlackBotApi][sendMessage][SUCCESS] 슬랙 메시지 전송 성공, channel={}, message={}", response.channel, response.message)
                            Mono.just(response)
                        }
                        false -> {
                            log.error("[SlackBotApi][sendMessage][ERROR] 슬랙 메시지 전송 실패, channel={}, message={}", channel, message)
                            Mono.error(IllegalArgumentException("슬랙 메시지 전송에 실패하였습니다."))
                        }
                    }
                }
    }
}