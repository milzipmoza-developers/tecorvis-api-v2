package com.woowacourse.tecorvis.web.service

import com.woowacourse.tecorvis.web.dto.NotificationRequestDto
import org.springframework.stereotype.Component

@Component
class TecorvisMessageFactory {

    fun wishBookMessage(requestDto: NotificationRequestDto) = """
            주인님:face_with_cowboy_hat:, 새로운 희망 도서:book: 입니다.

            :one:제목 - ${requestDto.title}
            :two:저자 - ${requestDto.author}
            :three:출판사 - ${requestDto.publisher}
            :four:ISBN - ${requestDto.isbn}
        """.trimIndent()


    fun libraryBookMessage(requestDto: NotificationRequestDto) = """
        여러분:face_with_cowboy_hat:, 테코브러리 신착 도서 알림 입니다!

        :one:제목 - ${requestDto.title}
        :two:저자 - ${requestDto.author}
        :three:출판사 - ${requestDto.publisher}
    """.trimIndent()
}
