package com.yanajiki.srv.notifierhub.infra.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PushBulletRequest {

    @JsonProperty("device_iden")
    private final String deviceIden;

    private final String type;
    private final String title;
    private final String body;

    public PushBulletRequest(String deviceIden, String title, String body) {
        this.deviceIden = deviceIden;
        this.type = "note";
        this.title = title;
        this.body = body;
    }
}
