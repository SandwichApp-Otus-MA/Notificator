package com.sandwich.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandwich.app.domain.dto.NotificationDto;
import com.sandwich.app.domain.dto.NotificationMessage;
import com.sandwich.app.domain.dto.pagination.PageData;
import com.sandwich.app.domain.dto.pagination.PageMetaData;
import com.sandwich.app.domain.entity.NotificationEntity;
import com.sandwich.app.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ObjectMapper objectMapper;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void save(NotificationMessage notification) {
        notificationRepository.save(new NotificationEntity()
            .setUserId(notification.getUserId())
            .setMessage(getMessage(notification)));
    }

    @Transactional(readOnly = true)
    public PageData<NotificationDto> getAll(int pageNumber, int pageSize) {
        var page = notificationRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PageData<NotificationDto>()
            .setContent(page.getContent().stream()
                .map(n -> new NotificationDto()
                    .setUserId(n.getUserId())
                    .setMessage(n.getMessage())
                    .setCreatedAt(n.getCreatedAt()))
                .toList())
            .setMetaData(new PageMetaData()
                .setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements()));
    }

    @SneakyThrows
    private String getMessage(NotificationMessage notification) {
        return objectMapper.writeValueAsString(notification.getMessage());
    }
}
