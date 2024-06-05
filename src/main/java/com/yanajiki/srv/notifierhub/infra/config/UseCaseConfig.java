package com.yanajiki.srv.notifierhub.infra.config;

import com.yanajiki.srv.notifierhub.core.domain.usecase.CleanOverdueNotificationsUseCase;
import com.yanajiki.srv.notifierhub.core.domain.usecase.NotifyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public NotifyUseCase notifyUseCase() {
        return new NotifyUseCase();
    }

    @Bean
    public CleanOverdueNotificationsUseCase cleanOverdueNotificationsUseCase() {
        return new CleanOverdueNotificationsUseCase();
    }
}
