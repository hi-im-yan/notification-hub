package com.yanajiki.srv.notifierhub.infra.http.discord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class DiscordChannelResponse {

    private String id;
    private int type;
    private String lastMessageId;
    private int flags;
    private String guildId;
    private String name;
    private String parentId;
    private int rateLimitPerUser;
    private String topic;
    private int position;
    private List<Object> permissionOverwrites;
    private boolean nsfw;
    private String themeColor;
}
