package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.SenderConfigurationData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SenderConfigurationMongoRepository extends MongoRepository<SenderConfigurationData, String> {

    Optional<SenderConfigurationData> findByName(String name);
}
