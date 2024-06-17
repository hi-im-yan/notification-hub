package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.domain.NotificationStatus;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.exception.NotFoundException;
import com.yanajiki.srv.notifierhub.infra.http.dto.PushBulletRequest;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SenderConfigurationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PushBulletClient {

    private final SenderConfigurationRepository configurationRepository;
    private final PushBulletFeignClient pushBulletFeignClient;

    public NotificationLog send(Notification notification) {
        var senderConfig = configurationRepository.findByName(notification.sender())
                .orElseThrow(() -> new NotFoundException("Sender Configuration Not Found"));

        var auth = senderConfig.getAuth();
        String[] authParts = auth.split("\\|");
        var bearerToken = authParts[0];

        final String TOKEN = "Bearer " + bearerToken;
        try {
            log.info("Consuming PUSHBULLET API.");

            var reqBody = new PushBulletRequest(notification.receiver(), notification.subject(), notification.message());

            var pushBulletResponse = pushBulletFeignClient.sendMessage(reqBody, TOKEN);

            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.DELIVERED,
                    notification.sender(),
                    pushBulletResponse.toString(),
                    NotificationType.PUSHBULLET
            );

            log.info("Successfully consumed PUSHBULLET API. {}", notificationLog);
            return notificationLog;

        } catch (FeignException.FeignClientException exception) {
            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.NOT_DELIVERED,
                    notification.sender(),
                    exception.toString(),
                    NotificationType.PUSHBULLET
            );
            log.error("Failed to communicate with PUSHBULLET API. {}", notificationLog, exception);

            return notificationLog;
        }
    }
}
