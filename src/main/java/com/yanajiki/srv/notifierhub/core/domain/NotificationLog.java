package com.yanajiki.srv.notifierhub.core.domain;

public record NotificationLog(String notificationId,
                              NotificationStatus status,
                              String sender,
                              String message,
                              NotificationType type) {


}
