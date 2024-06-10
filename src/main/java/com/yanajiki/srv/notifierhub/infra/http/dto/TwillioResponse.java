package com.yanajiki.srv.notifierhub.infra.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class TwillioResponse {

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("api_version")
    private String apiVersion;

    private String body;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("date_sent")
    private String dateSent;

    @JsonProperty("date_updated")
    private String dateUpdated;

    private String direction;

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    private String from;

    @JsonProperty("messaging_service_sid")
    private String messagingServiceSid;

    @JsonProperty("num_media")
    private String numMedia;

    @JsonProperty("num_segments")
    private String numSegments;

    private String price;

    @JsonProperty("price_unit")
    private String priceUnit;

    private String sid;

    private String status;

    @JsonProperty("subresource_uris")
    private Map<String, String> subresourceUris;

    private String to;

    private String uri;
}
