package com.yanajiki.srv.notifierhub.infra.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseNotificationData {
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime scheduledTime;

}
