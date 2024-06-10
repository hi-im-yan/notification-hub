package com.yanajiki.srv.notifierhub.infra.mongo.repository;


import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.port.NotificationLogRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.document.NotificationLogData;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class NotificationLogDataRepository implements NotificationLogRepository {

    private final NotificationLogMongoRepository mongoRepository;

    @Override
    public void persist(NotificationLog log) {

        var notificationLogData = new NotificationLogData();
        notificationLogData.setNotificationId(log.notificationId());
        notificationLogData.setType(log.type());
        notificationLogData.setMessage(log.message());
        notificationLogData.setSender(log.sender());
        notificationLogData.setCreatedAt(LocalDateTime.now());
        notificationLogData.setStatus(log.status());

        mongoRepository.save(notificationLogData);
    }
}
