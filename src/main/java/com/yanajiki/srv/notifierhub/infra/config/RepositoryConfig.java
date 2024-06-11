package com.yanajiki.srv.notifierhub.infra.config;

import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    @Qualifier("EmailScheduleNotificationRepository")
    public NotificationRepository emailNotificationRepository(EmailScheduleNotificationMongoRepository mongoRepository) {
        return new EmailScheduleNotificationRepository(mongoRepository);
    }

    @Bean
    @Qualifier("SmsScheduleNotificationRepository")
    public NotificationRepository smsNotificationRepository(SmsScheduleNotificationMongoRepository mongoRepository) {
        return new SmsScheduleNotificationRepository(mongoRepository);
    }

    @Bean
    @Qualifier("PushScheduleNotificationRepository")
    public NotificationRepository pushNotificationRepository(PushBulletScheduleNotificationMongoRepository mongoRepository) {
        return new PushBulletScheduleNotificationRepository(mongoRepository);
    }
}
