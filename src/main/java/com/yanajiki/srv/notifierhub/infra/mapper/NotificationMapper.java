package com.yanajiki.srv.notifierhub.infra.mapper;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.infra.kafka.dto.NotificationDTO;
import com.yanajiki.srv.notifierhub.infra.mongo.document.*;
import com.yanajiki.srv.notifierhub.infra.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NotificationMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

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

    public static Notification toDomainModel(NotificationDTO notificationDTO) {
        LocalDateTime scheduledTime = LocalDateTime.parse(notificationDTO.getScheduledTime(), formatter);

        return new Notification(
                null,
                notificationDTO.getSender(),
                notificationDTO.getReceiver(),
                notificationDTO.getMessage(),
                DateUtils.setSecondsAndNanoToZero(scheduledTime),
                notificationDTO.getSubject(),
                notificationDTO.getType()
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

    public static DiscordScheduleNotificationData toDiscordData(Notification domainModel) {
        var scheduledTime = DateUtils.setSecondsAndNanoToZero(domainModel.scheduledTime());
        return new DiscordScheduleNotificationData(
                domainModel.id(),
                domainModel.sender(),
                domainModel.receiver(),
                domainModel.message(),
                scheduledTime,
                domainModel.subject(),
                NotificationType.DISCORD
        );
    }
}
