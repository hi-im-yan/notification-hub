package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        return null;
    }

    @Override
    public void delete(Notification notification) {
        var toDelete = NotificationMapper.toEmailData(notification);

        mongoRepository.delete(toDelete);
    }
}
