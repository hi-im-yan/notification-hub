package com.yanajiki.srv.notifierhub.infra.http.discord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class DiscordGuildResponse {

    // ignoring "roles" attribute

    private String id;
    private String name;
    private String icon;
    private String description;
    private String homeHeader;
    private String splash;
    private String discoverySplash;
    private List<String> features;
    private String banner;
    private String ownerId;
    private String applicationId;
    private String region;
    private String afkChannelId;
    private int afkTimeout;
    private String systemChannelId;
    private int systemChannelFlags;
    private boolean widgetEnabled;
    private String widgetChannelId;
    private int verificationLevel;
    private int defaultMessageNotifications;
    private int mfaLevel;
    private int explicitContentFilter;
    private Integer maxPresences;
    private int maxMembers;
    private int maxStageVideoChannelUsers;
    private int maxVideoChannelUsers;
    private String vanityUrlCode;
    private int premiumTier;
    private int premiumSubscriptionCount;
    private String preferredLocale;
    private String rulesChannelId;
    private String safetyAlertsChannelId;
    private String publicUpdatesChannelId;
    private String hubType;
    private boolean premiumProgressBarEnabled;
    private String latestOnboardingQuestionId;
    private boolean nsfw;
    private int nsfwLevel;
    private List<String> emojis;
    private List<String> stickers;
    private String incidentsData;
    private String inventorySettings;
    private boolean embedEnabled;
    private String embedChannelId;
}
