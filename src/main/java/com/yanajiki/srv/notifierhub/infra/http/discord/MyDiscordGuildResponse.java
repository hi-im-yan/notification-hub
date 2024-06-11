package com.yanajiki.srv.notifierhub.infra.http.discord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class MyDiscordGuildResponse {
    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private String permissions;
    private List<String> features;
}
