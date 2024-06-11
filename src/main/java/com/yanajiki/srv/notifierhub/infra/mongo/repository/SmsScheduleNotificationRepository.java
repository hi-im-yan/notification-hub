package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SmsScheduleNotificationRepository implements NotificationRepository {

    private final SmsScheduleNotificationMongoRepository mongoRepository;

    @Override
    public Notification persist(Notification notification) {
        var data = NotificationMapper.toSmsData(notification);

        var savedData = mongoRepository.save(data);

        return NotificationMapper.toDomainModel(savedData);
    }

    @Override
    public List<Notification> findByDateTime(LocalDateTime scheduledTime) {
        var formattedScheduledTime = setSecondsAndNanoToZero(scheduledTime);

        var pendingNotifications = mongoRepository.findByScheduledTime(formattedScheduledTime);

        return pendingNotifications.stream().map(NotificationMapper::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public void delete(Notification notification) {
        var toDelete = NotificationMapper.toSmsData(notification);

        mongoRepository.delete(toDelete);
    }

    @Override
    public void deleteOverdue(int minutesOverdue) {
        mongoRepository.deleteByScheduledTimeLessThan(LocalDateTime.now().minusMinutes(minutesOverdue));
    }

    private LocalDateTime setSecondsAndNanoToZero(LocalDateTime time) {
        return time.withSecond(0).withNano(0);
    }
}
