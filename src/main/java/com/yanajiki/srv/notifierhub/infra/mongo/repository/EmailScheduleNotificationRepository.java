package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mapper.NotificationMapper;
import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmailScheduleNotificationRepository implements NotificationRepository {

    private final EmailScheduleNotificationMongoRepository mongoRepository;

    @Override
    public Notification persist(Notification notification) {
        var data = NotificationMapper.toEmailData(notification);

        var savedData = mongoRepository.save(data);

        return NotificationMapper.toDomainModel(savedData);
    }

    @Override
    public List<Notification> findByDateTime(LocalDateTime scheduledTime) {
        Notification notification = new Notification(
                UUID.randomUUID().toString(),
                "SENDER",
                "RECEIVER",
                "MESSAGE",
                LocalDateTime.now().withSecond(0).withNano(0),
                NotificationType.EMAIL
        );
        mongoRepository.save(NotificationMapper.toEmailData(notification));

        var formattedScheduledTime = setSecondsAndNanoToZero(scheduledTime);

        var pendingNotifications = mongoRepository.findByScheduledTime(formattedScheduledTime);

        return pendingNotifications.stream().map(NotificationMapper::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public void delete(Notification notification) {
        var toDelete = NotificationMapper.toEmailData(notification);

        mongoRepository.delete(toDelete);
    }

    private LocalDateTime setSecondsAndNanoToZero(LocalDateTime time) {
        return time.withSecond(0).withNano(0);
    }
}
