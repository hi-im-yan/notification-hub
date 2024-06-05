package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.PushBulletScheduleNotificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PushBulletScheduleNotificationMongoRepository extends MongoRepository<PushBulletScheduleNotificationData, String> {
    List<PushBulletScheduleNotificationData> findByScheduledTime(LocalDateTime scheduledTime);
    void deleteByScheduledTimeLessThan(LocalDateTime time);
}
