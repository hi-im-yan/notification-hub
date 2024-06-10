package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.NotificationLogData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationLogMongoRepository extends MongoRepository<NotificationLogData, String> {
}
