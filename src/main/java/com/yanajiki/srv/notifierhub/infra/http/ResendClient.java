package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.domain.NotificationStatus;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.exception.NotFoundException;
import com.yanajiki.srv.notifierhub.infra.http.dto.ResendRequest;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SenderConfigurationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResendClient {

    private final SenderConfigurationRepository configurationRepository;
    private final ResendFeignClient resendFeignClient;

    public NotificationLog send(Notification notification) {
        var senderConfig = configurationRepository.findByName(notification.sender())
                .orElseThrow(() -> new NotFoundException("Sender Configuration Not Found"));

        var auth = senderConfig.getAuth();
        String[] authParts = auth.split("\\|");
        var bearerToken = authParts[0];
        var senderDomain = authParts[1];

        try {
            log.info("Consuming RESEND API.");

            var reqBody = new ResendRequest(
                    senderDomain,
                    notification.receiver(),
                    notification.subject(),
                    notification.message()

            );
            var twillioResponse = resendFeignClient.sendMessage(reqBody, "Bearer " + bearerToken);

            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.DELIVERED,
                    notification.sender(),
                    twillioResponse.toString(),
                    NotificationType.EMAIL
            );

            log.info("Successfully consumed RESEND API. {}", notificationLog);

            return notificationLog;
        } catch (FeignException.FeignClientException exception) {
            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.NOT_DELIVERED,
                    notification.sender(),
                    exception.toString(),
                    NotificationType.EMAIL
            );
            log.error("Failed to communicate with RESEND API. {}", notificationLog, exception);

            return notificationLog;
        }
    }
}
