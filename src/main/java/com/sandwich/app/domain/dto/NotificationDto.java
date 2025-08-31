package com.sandwich.app.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto {
    private UUID userId;
    private Object message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant createdAt;
}
