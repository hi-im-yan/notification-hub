package com.yanajiki.srv.notifierhub.infra.http;

import com.yanajiki.srv.notifierhub.infra.http.dto.PushBulletRequest;
import com.yanajiki.srv.notifierhub.infra.http.dto.PushBulletResponse;
import com.yanajiki.srv.notifierhub.infra.http.dto.TwillioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pushbulletClient", url = "https://api.pushbullet.com/v2/pushes")
public interface PushBulletFeignClient {

    @PostMapping
    PushBulletResponse sendMessage(@RequestBody PushBulletRequest reqBody,
                                   @RequestHeader("Authorization") String authToken);
}



