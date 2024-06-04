package com.yanajiki.srv.notifierhub.infra.mapper;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;
import com.yanajiki.srv.notifierhub.infra.utils.DateUtils;


public class NotificationMapper {

    public static Notification toDomainModel(EmailScheduleNotificationData notificationData) {

        return new Notification(
                notificationData.getId(),
                notificationData.getSender(),
                notificationData.getReceiver(),
                notificationData.getMessage(),
                DateUtils.setSecondsAndNanoToZero(notificationData.getScheduledTime()),
                notificationData.getType()
                );
    }

    public static EmailScheduleNotificationData toEmailData(Notification domainModel) {
        var scheduledTime = DateUtils.setSecondsAndNanoToZero(domainModel.scheduledTime());
        return new EmailScheduleNotificationData(
                domainModel.id(),
                domainModel.sender(),
                domainModel.receiver(),
                domainModel.message(),
                scheduledTime
        );
    }
}
