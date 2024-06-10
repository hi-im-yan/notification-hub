package com.yanajiki.srv.notifierhub.infra.mapper;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;
import com.yanajiki.srv.notifierhub.infra.mongo.document.INotificationData;
import com.yanajiki.srv.notifierhub.infra.mongo.document.PushBulletScheduleNotificationData;
import com.yanajiki.srv.notifierhub.infra.mongo.document.SmsScheduleNotificationData;
import com.yanajiki.srv.notifierhub.infra.utils.DateUtils;


public class NotificationMapper {

    public static Notification toDomainModel(INotificationData notificationData) {

        return new Notification(
                notificationData.getId(),
                notificationData.getSender(),
                notificationData.getReceiver(),
                notificationData.getMessage(),
                DateUtils.setSecondsAndNanoToZero(notificationData.getScheduledTime()),
                notificationData.getSubject(),
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
                scheduledTime,
                domainModel.subject(),
                NotificationType.EMAIL
        );
    }

    public static SmsScheduleNotificationData toSmsData(Notification domainModel) {
        var scheduledTime = DateUtils.setSecondsAndNanoToZero(domainModel.scheduledTime());
        return new SmsScheduleNotificationData(
                domainModel.id(),
                domainModel.sender(),
                domainModel.receiver(),
                domainModel.message(),
                scheduledTime,
                domainModel.subject(),
                NotificationType.SMS
        );
    }

    public static PushBulletScheduleNotificationData toPushBulletData(Notification domainModel) {
        var scheduledTime = DateUtils.setSecondsAndNanoToZero(domainModel.scheduledTime());
        return new PushBulletScheduleNotificationData(
                domainModel.id(),
                domainModel.sender(),
                domainModel.receiver(),
                domainModel.message(),
                scheduledTime,
                domainModel.subject(),
                NotificationType.PUSHBULLET
        );
    }
}
