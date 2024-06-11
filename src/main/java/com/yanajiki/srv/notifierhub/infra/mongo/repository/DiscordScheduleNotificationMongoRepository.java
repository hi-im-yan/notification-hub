package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.DiscordScheduleNotificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscordScheduleNotificationMongoRepository extends MongoRepository<DiscordScheduleNotificationData, String> {
    List<DiscordScheduleNotificationData> findByScheduledTime(LocalDateTime scheduledTime);
    void deleteByScheduledTimeLessThan(LocalDateTime time);
}
