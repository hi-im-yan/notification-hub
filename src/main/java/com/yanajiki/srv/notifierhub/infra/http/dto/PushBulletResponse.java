package com.yanajiki.srv.notifierhub.infra.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PushBulletResponse {

    private boolean active;

    private String iden;

    private String created;

    private String modified;

    private String type;

    private boolean dismissed;

    private String direction;

    @JsonProperty("sender_iden")
    private String senderIden;

    @JsonProperty("sender_email")
    private String senderEmail;

    @JsonProperty("sender_email_normalized")
    private String senderEmailNormalized;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("receiver_iden")
    private String receiverIden;

    @JsonProperty("receiver_email")
    private String receiverEmail;

    @JsonProperty("receiver_email_normalized")
    private String receiverEmailNormalized;

    @JsonProperty("target_device_iden")
    private String targetDeviceIden;

    private String title;

    private String body;
}
