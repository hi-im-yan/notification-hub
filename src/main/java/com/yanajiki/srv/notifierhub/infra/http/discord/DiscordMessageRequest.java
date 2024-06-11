package com.yanajiki.srv.notifierhub.infra.http.discord;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiscordMessageRequest {

    private String content;
    private boolean tts;
}
