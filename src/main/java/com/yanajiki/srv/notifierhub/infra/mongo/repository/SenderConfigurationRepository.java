package com.yanajiki.srv.notifierhub.infra.mongo.repository;

import com.yanajiki.srv.notifierhub.infra.mongo.document.SenderConfigurationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SenderConfigurationRepository {

    private final SenderConfigurationMongoRepository repository;

    public Optional<SenderConfigurationData> findByName(String name){
        return repository.findByName(name);
    }
}
