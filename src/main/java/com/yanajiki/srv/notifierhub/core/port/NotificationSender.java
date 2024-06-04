package com.yanajiki.srv.notifierhub.core.port;

import com.yanajiki.srv.notifierhub.core.domain.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
