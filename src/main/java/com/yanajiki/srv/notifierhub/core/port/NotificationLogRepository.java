package com.yanajiki.srv.notifierhub.core.port;

import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;

public interface NotificationLogRepository {

    void persist(NotificationLog log);
}
