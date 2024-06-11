package com.yanajiki.srv.notifierhub.infra.kafka.dto;

import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class NotificationDTO {
    private String sender;
    private String receiver;
    private String message;
    private String scheduledTime;
    private String subject;
    private NotificationType type;
}
