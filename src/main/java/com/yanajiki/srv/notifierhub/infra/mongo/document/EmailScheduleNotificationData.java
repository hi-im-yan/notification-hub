package com.yanajiki.srv.notifierhub.infra.mongo.document;

import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "email_schedule_notification")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EmailScheduleNotificationData extends BaseNotificationData {

    @Id
    private String id;
    private final NotificationType type = NotificationType.EMAIL;

    public EmailScheduleNotificationData(String id,
                                         String sender,
                                         String receiver,
                                         String message,
                                         LocalDateTime scheduledTime) {
        super(sender, receiver, message, scheduledTime);
        this.id = id;
    }
}
