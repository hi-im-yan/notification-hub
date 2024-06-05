package com.yanajiki.srv.notifierhub.infra.mongo.document;

import com.yanajiki.srv.notifierhub.core.domain.NotificationType;

import java.time.LocalDateTime;

public interface INotificationData {

    String getId();
    String getSender();
    String getReceiver();
    String getMessage();
    LocalDateTime getScheduledTime();
    NotificationType getType();
}
