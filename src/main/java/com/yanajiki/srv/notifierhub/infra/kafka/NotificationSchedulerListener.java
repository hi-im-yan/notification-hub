package com.yanajiki.srv.notifierhub.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.infra.kafka.dto.NotificationDTO;
import com.yanajiki.srv.notifierhub.infra.mapper.NotificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class NotificationSchedulerListener {

    private final Map<NotificationType, NotificationRepository> notificationRepositoryMap;

    @Autowired
    public NotificationSchedulerListener(NotificationRepository emailNotificationRepository,
                                         NotificationRepository smsNotificationRepository,
                                         NotificationRepository discordNotificationRepository,
                                         NotificationRepository pushbulletNotificationRepository) {

        notificationRepositoryMap = new HashMap<>();
        notificationRepositoryMap.put(NotificationType.EMAIL, emailNotificationRepository);
        notificationRepositoryMap.put(NotificationType.SMS, smsNotificationRepository);
        notificationRepositoryMap.put(NotificationType.DISCORD, discordNotificationRepository);
        notificationRepositoryMap.put(NotificationType.PUSHBULLET, pushbulletNotificationRepository);

    }

    @KafkaListener(topics = "schedule-notification", containerFactory = "newNotificationSchedulerContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var scheduledDTO = objectMapper.readValue(message, NotificationDTO.class);

        log.info("Received notification schedule by KAFKA. {}", scheduledDTO);
        var scheduledDomain = NotificationMapper.toDomainModel(scheduledDTO);

        var notificationRepository = notificationRepositoryMap.get(scheduledDomain.type());

        var persisted = notificationRepository.persist(scheduledDomain);

        log.info("Notification was scheduled. {}", persisted);
    }
}
