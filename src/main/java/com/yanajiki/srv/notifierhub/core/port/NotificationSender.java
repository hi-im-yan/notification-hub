package com.yanajiki.srv.notifierhub.core.port;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;

public interface NotificationSender {
    NotificationLog send(Notification notification);
}
