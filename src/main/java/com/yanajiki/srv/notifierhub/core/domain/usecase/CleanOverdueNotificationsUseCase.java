package com.yanajiki.srv.notifierhub.core.domain.usecase;

import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CleanOverdueNotificationsUseCase {

    @NonNull
    private final Integer MINUTES_OVERDUE;

    public void execute(List<NotificationRepository> repositories) {
        repositories.forEach(this::clean);
    }

    private void clean(NotificationRepository repository) {
        log.info("Cleaning repository: {}", repository.getClass().getSimpleName());
        repository.deleteOverdue(MINUTES_OVERDUE);
    }
}
