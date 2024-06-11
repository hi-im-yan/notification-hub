package com.yanajiki.srv.notifierhub.infra.job;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.EmailScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.PushBulletScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SmsScheduleNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MockingScheduledNotificationsJob {

    private final EmailScheduleNotificationRepository emailRepository;
    private final PushBulletScheduleNotificationRepository pushBulletRepository;
    private final SmsScheduleNotificationRepository smsRepository;

    @Scheduled(cron = "0 * * * * *") // Executes every minute
    @Async
    public void executePeriodically() {
        saveMockPendingNotifications();
    }

    private void saveMockPendingNotifications() {
//        for (int i = 0; i < 1; i++) {
//            emailRepository.persist(generateMockNotification(NotificationType.EMAIL));
//        }

//        for (int i = 0; i < 1; i++) {
//            smsRepository.persist(generateMockNotification(NotificationType.SMS));
//        }

//        for (int i = 0; i < 100; i++) {
//            pushBulletRepository.persist(generateMockNotification(NotificationType.PUSHBULLET));
//        }
    }

    private Notification generateMockNotification(NotificationType type) {
        return new Notification(
                UUID.randomUUID().toString(),
                "RESEND - TEST",
                "yanajiki@gmail.com",
                "Amo muito minha namorada",
                LocalDateTime.now().plusMinutes(1),
                "Prova de que posso agendar uma mensagem pra ser enviada pra ela hihihi.",
                type
        );
    }
}
