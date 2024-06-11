package com.yanajiki.srv.notifierhub.infra.sender;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(3)
public class PushBullerSender implements NotificationSender {
    @Override
    public NotificationLog send(Notification notification) {
        log.info("Sending notification by PUSH_BULLET: {}", notification);
        return null;
    }
}
