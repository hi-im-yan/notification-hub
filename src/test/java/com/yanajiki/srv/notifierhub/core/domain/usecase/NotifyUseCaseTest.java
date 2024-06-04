package com.yanajiki.srv.notifierhub.core.domain.usecase;

import com.yanajiki.srv.notifierhub.core.domain.Notification;
import com.yanajiki.srv.notifierhub.core.domain.NotificationType;
import com.yanajiki.srv.notifierhub.core.port.NotificationRepository;
import com.yanajiki.srv.notifierhub.core.port.NotificationSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifyUseCaseTest {

    @Mock
    private NotificationRepository repository;

    @Mock
    private NotificationSender sender;

    private NotifyUseCase useCase;
    @BeforeEach
    void init() {
        this.useCase = new NotifyUseCase();
    }

    @Test
    @DisplayName("Should send all pending notifications for the current time")
    void shouldCallRepositoryAndSender() {
        var mockedPendingNotifications = Arrays.asList(
                generateMockNotification(),
                generateMockNotification(),
                generateMockNotification()
        );

        when(repository.findByDateTime(any())).thenReturn(mockedPendingNotifications);

        useCase.execute(repository, sender);

        verify(repository, times(1)).findByDateTime(any());
        verify(sender, times(mockedPendingNotifications.size())).send(any());
    }

    private Notification generateMockNotification() {
        return new Notification(
                UUID.randomUUID().toString(),
                "SENDER",
                "RECEIVER",
                "MESSAGE",
                null,
                NotificationType.EMAIL
        );
    }
}