package com.yanajiki.srv.notifierhub.core.domain;

import java.time.LocalDateTime;

public record Notification(
        String id,
        String sender,
        String receiver,
        String message,
        LocalDateTime scheduledTime,
        String subject,
        NotificationType type) {
}
