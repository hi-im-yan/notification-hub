package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationLog;
import com.yanajiki.srv.notifierhub.core.domain.NotificationStatus;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.exception.NotFoundException;
import com.yanajiki.srv.notifierhub.infra.http.discord.DiscordMessageRequest;
import com.yanajiki.srv.notifierhub.infra.http.discord.DiscordMessageResponse;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SenderConfigurationRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiscordClient {

    private final SenderConfigurationRepository configurationRepository;
    private final DiscordFeignClient discordFeignClient;

    private static final int TEXT_CHANNEL = 0;

    public NotificationLog send(Notification notification) {
        var senderConfig = configurationRepository.findByName(notification.sender())
                .orElseThrow(() -> new NotFoundException("Sender Configuration Not Found"));

        var auth = senderConfig.getAuth();
        String[] authParts = auth.split("\\|");
        var botToken = authParts[0];
        var guildId = authParts[1];

        final String TOKEN = "Bot " + botToken;
        try {
            log.info("Consuming DISCORD API.");

            var guilds = discordFeignClient.getAllMyGuilds(TOKEN);

            for (var guild : guilds) {
                if (guild.getId().equals(guildId)) {
                    var guildChannels = discordFeignClient.getGuildChannels(guildId, TOKEN);

                    for (var channel : guildChannels) {
                        if (channel.getId().equals(notification.receiver()) && channel.getType() == TEXT_CHANNEL) {
                            var discordMessage = new DiscordMessageRequest(notification.message(), true);
                            var discordMessageResponse = discordFeignClient.sendMessage(notification.receiver(), TOKEN, discordMessage);

                            var notificationLog = new NotificationLog(
                                    notification.id(),
                                    NotificationStatus.DELIVERED,
                                    notification.sender(),
                                    discordMessageResponse.toString(),
                                    NotificationType.DISCORD
                            );

                            log.info("Successfully consumed DISCORD API. {}", notificationLog);
                            return notificationLog;
                        }
                    }

                    log.warn("The channelId [] is not from the guild or it's not a text channel");
                    return new NotificationLog(
                            notification.id(),
                            NotificationStatus.NOT_DELIVERED,
                            notification.sender(),
                            "The channelId is not from the guild or it's not a text channel",
                            NotificationType.DISCORD
                    );
                }
            }

            log.warn("Discord bot is not a member of the guild id: " + guildId);
            return new NotificationLog(
                    notification.id(),
                    NotificationStatus.NOT_DELIVERED,
                    notification.sender(),
                    "Discord bot is not a member of the guild id: " + guildId,
                    NotificationType.DISCORD
            );

        } catch (FeignException.FeignClientException exception) {
            var notificationLog = new NotificationLog(
                    notification.id(),
                    NotificationStatus.NOT_DELIVERED,
                    notification.sender(),
                    exception.toString(),
                    NotificationType.DISCORD
            );
            log.error("Failed to communicate with DISCORD API. {}", notificationLog, exception);

            return notificationLog;
        }
    }
}
