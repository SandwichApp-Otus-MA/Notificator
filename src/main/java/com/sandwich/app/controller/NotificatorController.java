package com.sandwich.app.controller;

import com.sandwich.app.domain.dto.NotificationDto;
import com.sandwich.app.domain.dto.pagination.PageData;
import com.sandwich.app.service.NotificationService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/v1/notificator")
@RequiredArgsConstructor
public class NotificatorController {

    private final NotificationService notificationService;

    @PostMapping("/search")
    public ResponseEntity<PageData<NotificationDto>> checkStatus(@RequestParam @Positive int pageNumber,
                                                                 @RequestParam(required = false, defaultValue = "10") @Positive int pageSize) {
        return ResponseEntity.ok(notificationService.getAll(pageNumber - 1, pageSize));
    }
}
