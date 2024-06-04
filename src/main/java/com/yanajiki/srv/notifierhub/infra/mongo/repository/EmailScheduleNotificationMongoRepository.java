package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.EmailScheduleNotificationData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailScheduleNotificationMongoRepository extends MongoRepository<EmailScheduleNotificationData, String> {
}
