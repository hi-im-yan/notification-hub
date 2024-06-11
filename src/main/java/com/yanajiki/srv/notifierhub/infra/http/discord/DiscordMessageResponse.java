package com.yanajiki.srv.notifierhub.infra.http.discord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class DiscordMessageResponse {

    // Ignoring Author attribute

    private int type;
    private String channelId;
    private String content;
    private List<Object> attachments;
    private List<Object> embeds;
    private OffsetDateTime timestamp;
    private OffsetDateTime editedTimestamp;
    private int flags;
    private List<Object> components;
    private String id;
    private List<Object> mentions;
    private List<Object> mentionRoles;
    private boolean pinned;
    private boolean mentionEveryone;
    private boolean tts;
}
