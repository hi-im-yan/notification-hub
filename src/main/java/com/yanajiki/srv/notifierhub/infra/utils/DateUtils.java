package com.yanajiki.srv.notifierhub.infra.utils;

import java.time.LocalDateTime;

public class DateUtils {

    public static LocalDateTime setSecondsAndNanoToZero(LocalDateTime time) {
        return time.withSecond(0).withNano(0);
    }
}
