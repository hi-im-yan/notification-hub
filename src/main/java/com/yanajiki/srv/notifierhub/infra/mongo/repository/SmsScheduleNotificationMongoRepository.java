package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.SmsScheduleNotificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsScheduleNotificationMongoRepository extends MongoRepository<SmsScheduleNotificationData, String> {
    List<SmsScheduleNotificationData> findByScheduledTime(LocalDateTime scheduledTime);
    void deleteByScheduledTimeLessThan(LocalDateTime time);
}
