package com.yanajiki.srv.notifierhub.core.domain.usecase;

import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;

import java.time.LocalDateTime;

public class NotifyUseCase {

    public void execute(NotificationRepository repository, NotificationSender sender) {
        var scheduledTime = LocalDateTime.now().withSecond(0);

        var pendingNotifications = repository.findByDateTime(scheduledTime);

        pendingNotifications.forEach(sender::send);
    }
}
