package com.sandwich.app.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandwich.app.domain.dto.NotificationMessage;
import com.sandwich.app.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
        containerFactory = "notificationListenerContainerFactory",
        topics = "${sandwich.kafka.consumer.topic.notification}",
        groupId = "${sandwich.kafka.consumer.group-id}",
        errorHandler = "kafkaListenerErrorHandler",
        autoStartup = "false")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        try {
            var notification = objectMapper.readValue(consumerRecord.value(), NotificationMessage.class);
            notificationService.save(notification);
        } catch (Exception ex) {
            log.error("Произошла ошибка при обработке сообщения", ex);
        }
    }
}
