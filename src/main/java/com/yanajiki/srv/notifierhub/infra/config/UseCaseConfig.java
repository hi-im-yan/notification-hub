package com.yanajiki.srv.notifierhub.infra.config;

import com.yanajiki.srv.notifierhub.core.domain.usecase.CleanOverdueNotificationsUseCase;
import com.yanajiki.srv.notifierhub.core.domain.usecase.NotifyUseCase;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.NotificationLogDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Value("${CLEAN_OVERDUE_NOTIFICATIONS_INTERVAL_IN_MINUTES}")
    private Integer cleanOverdueNotificationsInterval;

    @Bean
    public NotifyUseCase notifyUseCase(NotificationLogDataRepository logDataRepository) {
        return new NotifyUseCase(logDataRepository);
    }

    @Bean
    public CleanOverdueNotificationsUseCase cleanOverdueNotificationsUseCase() {
        return new CleanOverdueNotificationsUseCase(cleanOverdueNotificationsInterval);
    }
}
