package com.yanajiki.srv.notifierhub.infra.mapper;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.infra.mongo.document.BaseNotificationData;
import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;

public class NotificationMapper {

    public static Notification toDomainModel(BaseNotificationData notificationData) {

        return new Notification(
                null,
                notificationData.getSender(),
                notificationData.getReceiver(),
                notificationData.getMessage(),
                notificationData.getScheduledTime(),
                null
                );
    }

    public static EmailScheduleNotificationData toEmailData(Notification domainModel) {
        return new EmailScheduleNotificationData(
                domainModel.id(),
                domainModel.sender(),
                domainModel.receiver(),
                domainModel.message(),
                domainModel.scheduledTime()
        );
    }
}
