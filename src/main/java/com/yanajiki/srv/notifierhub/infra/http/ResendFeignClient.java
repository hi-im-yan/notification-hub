package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.infra.http.dto.ResendRequest;
import com.yanajiki.srv.notifierhub.infra.http.dto.ResendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "resendClient", url = "https://api.resend.com")
public interface ResendFeignClient {

    @PostMapping("/emails")
    ResendResponse sendMessage(@RequestBody ResendRequest reqBody, @RequestHeader("Authorization") String authToken);
}



