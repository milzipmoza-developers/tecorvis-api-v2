package com.woowacourse.tecorvis.web.service

import com.woowacourse.tecorvis.external.slack.SlackBotModule
import com.woowacourse.tecorvis.external.slack.dto.SlackResponse
import com.woowacourse.tecorvis.web.dto.NotificationRequestDto
import com.woowacourse.tecorvis.web.dto.NotificationResultDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TecorvisNotificationService(
        private val slackBotModule: SlackBotModule,
        private val tecorvisMessageFactory: TecorvisMessageFactory
) {

    fun wishBookNotification(requestDto: NotificationRequestDto) = send(
            requestDto,
            tecorvisMessageFactory::wishBookMessage,
            slackBotModule::sendWishBookNotification
    )
    fun libraryBookNotification(requestDto: NotificationRequestDto) = send(
            requestDto,
            tecorvisMessageFactory::libraryBookMessage,
            slackBotModule::sendLibraryBookNotification
    )

    private fun send(
            requestDto: NotificationRequestDto,
            messageFactory: (req: NotificationRequestDto) -> String,
            sendMessage: (message: String) -> Mono<SlackResponse>
    ): NotificationResultDto {
        val result = sendMessage(messageFactory(requestDto)).block()
        return NotificationResultDto(result!!.ok)
    }
}