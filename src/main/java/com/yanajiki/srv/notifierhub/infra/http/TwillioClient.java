package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.domain.NotificationStatus;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.exception.NotFoundException;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SenderConfigurationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class TwillioClient {

    private final SenderConfigurationRepository configurationRepository;
    private final TwillioFeignClient twillioFeignClient;

    public NotificationLog send(Notification notification) {
        var senderConfig = configurationRepository.findByName(notification.sender())
                .orElseThrow(() -> new NotFoundException("Sender Configuration Not Found"));

        var auth = senderConfig.getAuth();
        String[] authParts = auth.split("\\|");
        var accountSID = authParts[0];
        var authToken = authParts[1];
        var twillioPhoneNumber = authParts[2];

        var encodedAuthToken = String.format("%s:%s", accountSID, authToken);
        var basicAuthToken = "Basic " + Base64.getEncoder().encodeToString(encodedAuthToken.getBytes());


        try {
            log.info("Consuming TWILLIO API.");

            var reqBody = String.format("To=%s&From=%s&Body='%s'",
                    notification.receiver(), twillioPhoneNumber, notification.message());
            var twillioResponse = twillioFeignClient.sendMessage(accountSID, reqBody, basicAuthToken);

            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.DELIVERED,
                    notification.sender(),
                    twillioResponse.toString(),
                    NotificationType.SMS
            );

            log.info("Successfully consumed TWILLIO API. {}", notificationLog);

            return notificationLog;
        } catch (FeignException.FeignClientException exception) {
            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.NOT_DELIVERED,
                    notification.sender(),
                    exception.toString(),
                    NotificationType.SMS
            );
            log.error("Failed to communicate with TWILLIO API. {}", notificationLog, exception);

            return notificationLog;
        }
    }
}
