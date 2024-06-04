package com.yanajiki.srv.notifierhub.infra.sender;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSender implements NotificationSender {
    @Override
    public void send(Notification notification) {
        log.info("Sending notification by email: {}", notification);
    }
}
