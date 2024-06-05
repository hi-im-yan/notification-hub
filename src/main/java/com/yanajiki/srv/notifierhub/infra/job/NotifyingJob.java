package com.yanajiki.srv.notifierhub.infra.job;

import com.yanajiki.srv.notifierhub.core.domain.usecase.NotifyUseCase;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotifyingJob {

    private final List<NotificationRepository> repositories;
    private final List<NotificationSender> senders;
    private final NotifyUseCase useCase;

    @Scheduled(cron = "0 * * * * *") // Executes every minute
    @Async
    public void executePeriodically() {

        for (int i = 0; i < repositories.size(); i++) {
            var currRepository = repositories.get(i);
            var currSender = senders.get(i);

            var currRepositoryName = currRepository.getClass().getSimpleName();
            var currSenderName = currSender.getClass().getSimpleName();

            log.info("Calling NotifyUseCase({}, {})", currRepositoryName, currSenderName);
            CompletableFuture.runAsync(() -> useCase.execute(currRepository, currSender));
        }
    }
}
