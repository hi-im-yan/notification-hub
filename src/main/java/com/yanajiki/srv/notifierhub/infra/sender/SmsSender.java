package com.yanajiki.srv.notifierhub.infra.sender;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import com.yanajiki.srv.notifierhub.infra.http.TwillioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsSender implements NotificationSender {

    private final TwillioClient twillioClient;

    @Override
    public void send(Notification notification) {
        log.info("Sending SMS notification...");
        twillioClient.send(notification);
    }
}
