package com.yanajiki.srv.notifierhub.infra.job;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.domain.usecase.NotifyUseCase;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SmsScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.sender.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SmsScheduleNotifierJob {
    private final NotifyUseCase useCase;
    private final SmsScheduleNotificationRepository repository;
    private final SmsSender sender;

    @Scheduled(cron = "0 * * * * *") // Executes every minute
    @Async
    public void executePeriodically() {
        saveMockPendingNotifications();
        System.out.println("Executing sms scheduler job...");
        useCase.execute(repository, sender);
        System.out.println("Finished sms scheduler job...");
    }

    private void saveMockPendingNotifications() {
        for (int i = 0; i < 100; i++) {
            repository.persist(generateMockNotification());
        }

    }

    private Notification generateMockNotification() {
        return new Notification(
                UUID.randomUUID().toString(),
                "SENDER",
                "RECEIVER",
                "MESSAGE",
                LocalDateTime.now(),
                NotificationType.SMS
        );
    }
}
