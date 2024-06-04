package com.yanajiki.srv.notifierhub.infra.job;

import com.yanajiki.srv.notifierhub.core.domain.usecase.NotifyUseCase;
import com.yanajiki.srv.notifierhub.infra.mongo.repository.EmailScheduleNotificationRepository;
import com.yanajiki.srv.notifierhub.infra.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduleNotifierJob {

    private final NotifyUseCase useCase;
    private final EmailScheduleNotificationRepository repository;
    private final EmailSender sender;

    @Scheduled(cron = "0 * * * * *") // Executes every minute
    public void executePeriodically() {
        System.out.println("Executing email scheduler job...");
        useCase.execute(repository, sender);
        System.out.println("Finished email scheduler job...");
    }
}
