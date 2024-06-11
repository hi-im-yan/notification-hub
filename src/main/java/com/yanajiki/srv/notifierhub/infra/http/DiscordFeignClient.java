package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.infra.http.discord.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "discordClient", url = "https://discord.com/api/v9")
public interface DiscordFeignClient {

    @GetMapping("/users/@me/guilds")
    List<MyDiscordGuildResponse> getAllMyGuilds(@RequestHeader("Authorization") String authToken);

    @GetMapping("/guilds/{guildId}")
    DiscordGuildResponse getGuildInfo(@PathVariable String guildId,
                                      @RequestHeader("Authorization") String authToken);

    @GetMapping("/guilds/{guildId}/channels")
    List<DiscordChannelResponse> getGuildChannels(@PathVariable String guildId,
                                                  @RequestHeader("Authorization") String authToken);

    @PostMapping("/channels/{channelId}/messages")
    DiscordMessageResponse sendMessage(@PathVariable String channelId,
                                       @RequestHeader("Authorization") String authToken,
                                       @RequestBody DiscordMessageRequest reqBody);
}



