package com.yanajiki.srv.notifierhub.core.port;

import com.yanajiki.srv.notifierhub.core.domain.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository {

    Notification persist(Notification notification);
    List<Notification> findByDateTime(LocalDateTime scheduledTime);
    void delete(Notification notification);
}
