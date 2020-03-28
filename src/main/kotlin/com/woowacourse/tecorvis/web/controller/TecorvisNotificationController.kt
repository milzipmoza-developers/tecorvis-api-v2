package com.woowacourse.tecorvis.web.controller

import com.woowacourse.tecorvis.web.dto.ApiResponse
import com.woowacourse.tecorvis.web.dto.NotificationRequestDto
import com.woowacourse.tecorvis.web.dto.NotificationResultDto
import com.woowacourse.tecorvis.web.service.TecorvisNotificationService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Controller
@RequestMapping("/api/v1/notify")
class TecorvisNotificationController (
    val tecorvisNotificationService: TecorvisNotificationService
) {

    @PostMapping("/librarybook")
    fun newLibraryBook(@RequestBody requestDto: NotificationRequestDto): Mono<ApiResponse<NotificationResultDto>> {
        return Mono.just(ApiResponse.ok(tecorvisNotificationService.libraryBookNotification(requestDto)))
    }

    @PostMapping("/wishbook")
    fun newWishBook(@RequestBody requestDto: NotificationRequestDto): Mono<ApiResponse<NotificationResultDto>> {
        return Mono.just(ApiResponse.ok(tecorvisNotificationService.wishBookNotification(requestDto)))
    }
}