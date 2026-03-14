package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * <h1>Юнит-тесты с использованием Mockito</h1>
 * 
 * <p>Мы имитируем поведение <code>NotificationService</code>, чтобы
 * проверить логику <code>UserNotificationService</code> без реальной отправки сообщений.</p>
 */
@ExtendWith(MockitoExtension.class)
public class TestingNotesTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private UserNotificationService userNotificationService;

    /**
     * Тест успешного сценария.
     */
    @Test
    public void testNotifySuccess() {
        // Обучаем мок: при любом вызове send возвращать true
        when(notificationService.send(anyString())).thenReturn(true);

        String result = userNotificationService.notifyUser("Привет!");

        assertEquals("SUCCESS", result);
        // Проверяем, что метод send действительно был вызван 1 раз
        verify(notificationService, times(1)).send("Привет!");
    }

    /**
     * Тест сценария с пустым сообщением (бизнес-валидация).
     */
    @Test
    public void testNotifyWithEmptyMessage() {
        String result = userNotificationService.notifyUser("");

        assertEquals("FAILURE", result);
        // Проверяем, что мок ВООБЩЕ не вызывался, так как валидация не прошла
        verifyNoInteractions(notificationService);
    }

    /**
     * Тест случая, когда служба отправки вернула ошибку.
     */
    @Test
    public void testNotifyRetry() {
        when(notificationService.send(anyString())).thenReturn(false);

        String result = userNotificationService.notifyUser("Важное сообщение");

        assertEquals("RETRY", result);
    }
}
