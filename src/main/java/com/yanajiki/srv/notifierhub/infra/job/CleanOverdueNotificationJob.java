package com.yanajiki.srv.notifierhub.infra.job;

import com.yanajiki.srv.notifierhub.core.domain.usecase.CleanOverdueNotificationsUseCase;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CleanOverdueNotificationJob {

    private final List<NotificationRepository> repositories;
    private final CleanOverdueNotificationsUseCase useCase;

    @Scheduled(cron = "0 * * * * *") // Executes every minute
    public void executePeriodically() {
        System.out.println("Start cleaning overdue scheduled notifications...");
        useCase.execute(repositories);
        System.out.println("Finished cleaning overdue scheduled notifications...");
    }
}
