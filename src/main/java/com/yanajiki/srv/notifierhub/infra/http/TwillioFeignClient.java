package com.yanajiki.srv.notifierhub.infra.http;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "twillioClient", url = "https://api.twilio.com/2010-04-01")
public interface TwillioFeignClient {

    @PostMapping("/Accounts/{accountSID}/Messages.json")
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    void sendMessage(@PathVariable("accountSID") String accountSID,
                     @RequestBody String urlEncodedReqBody,
                     @RequestHeader("Authorization") String authToken);
}



