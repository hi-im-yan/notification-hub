package com.yanajiki.srv.notifierhub.infra.http.dto;

public record ResendRequest(String from,
                            String to,
                            String subject,
                            String text) {


}
