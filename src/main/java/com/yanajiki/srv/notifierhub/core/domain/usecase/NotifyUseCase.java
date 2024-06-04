package com.yanajiki.srv.notifierhub.core.domain.usecase;

import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class NotifyUseCase {

    public void execute(NotificationRepository repository, NotificationSender sender) {
        var scheduledTime = LocalDateTime.now().withSecond(0);

        var pendingNotifications = repository.findByDateTime(scheduledTime);

        int pendingCount = pendingNotifications.size();

        if (pendingCount == 0) {
            return;
        }

        log.info("Found {} pending notifications for type [{}]", pendingCount, pendingNotifications.get(0).type());
        pendingNotifications.forEach(sender::send);

    }
}
