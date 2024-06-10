package com.yanajiki.srv.notifierhub.infra.sender;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import com.yanajiki.srv.notifierhub.infra.http.ResendClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSender implements NotificationSender {

    private final ResendClient resendClient;

    @Override
    public NotificationLog send(Notification notification) {
        log.info("Sending notification by email: {}", notification);

        return resendClient.send(notification);
    }
}
