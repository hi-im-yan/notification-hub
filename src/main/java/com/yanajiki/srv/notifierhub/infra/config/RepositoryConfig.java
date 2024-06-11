package com.yanajiki.srv.notifierhub.infra.config;

import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.DiscordScheduleNotificationMongoRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.DiscordScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.EmailScheduleNotificationMongoRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.EmailScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.PushBulletScheduleNotificationMongoRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.PushBulletScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SmsScheduleNotificationMongoRepository;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.SmsScheduleNotificationRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class RepositoryConfig {

    @Bean
    @Order(1)
    @Qualifier("EmailScheduleNotificationRepository")
    public NotificationRepository emailNotificationRepository(EmailScheduleNotificationMongoRepository mongoRepository) {
        return new EmailScheduleNotificationRepository(mongoRepository);
    }

    @Bean
    @Order(2)
    @Qualifier("SmsScheduleNotificationRepository")
    public NotificationRepository smsNotificationRepository(SmsScheduleNotificationMongoRepository mongoRepository) {
        return new SmsScheduleNotificationRepository(mongoRepository);
    }

    @Bean
    @Order(3)
    @Qualifier("PushBulletScheduleNotificationRepository")
    public NotificationRepository pushbulletNotificationRepository(PushBulletScheduleNotificationMongoRepository mongoRepository) {
        return new PushBulletScheduleNotificationRepository(mongoRepository);
    }

    @Bean
    @Order(4)
    @Qualifier("DiscordScheduleNotificationRepository")
    public NotificationRepository discordNotificationRepository(DiscordScheduleNotificationMongoRepository mongoRepository) {
        return new DiscordScheduleNotificationRepository(mongoRepository);
    }
}
