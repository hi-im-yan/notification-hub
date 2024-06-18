package com.yanajiki.srv.notifierhub.infra.kafka.dto;

import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class NotificationDTO {
    private String sender;
    /*
        In case that is a email, specify the receiver email.
        In case that is a sms, specify the receiver number.
        In case that is a pushbullet, specify the device_id.
        In case that is a discord, specify the chat_id from the server that the bot is in.
     */
    private String receiver;
    private String message;
    private String scheduledTime;
    private String subject;
    private NotificationType type;
}
