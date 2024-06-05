package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailScheduleNotificationMongoRepository extends MongoRepository<EmailScheduleNotificationData, String> {
    List<EmailScheduleNotificationData> findByScheduledTime(LocalDateTime scheduledTime);

    void deleteByScheduledTimeLessThan(LocalDateTime time);

}
