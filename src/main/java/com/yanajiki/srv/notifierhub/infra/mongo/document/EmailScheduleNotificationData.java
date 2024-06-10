package com.yanajiki.srv.notifierhub.infra.mongo.document;

import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "email_schedule_notification")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EmailScheduleNotificationData implements INotificationData {

    @Id
    private String id;
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime scheduledTime;
    private String subject;
    private NotificationType type;

    public void setType(NotificationType notificationType) {
        this.type = NotificationType.EMAIL;
    }

}
