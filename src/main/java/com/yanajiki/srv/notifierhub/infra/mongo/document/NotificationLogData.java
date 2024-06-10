package com.yanajiki.srv.notifierhub.infra.mongo.document;

import com.yanajiki.srv.notifierhub.core.domain.NotificationStatus;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notification_log")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NotificationLogData {

    @Id
    private String id;
    private String notificationId;
    private NotificationStatus status;
    private String sender;
    private String message;
    private LocalDateTime createdAt;
    private NotificationType type;

}
