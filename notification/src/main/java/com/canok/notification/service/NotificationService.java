package com.canok.notification.service;

import com.canok.clients.dto.NotificationRequest;
import com.canok.notification.entity.Notification;
import com.canok.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest){

        Notification notification= Notification.builder()
                .toCustomerId(notificationRequest.getCustomerId())
                .toCustomerEmail(notificationRequest.getEmail())
                .message(notificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .sender("CANOKAPP")
                        .build();

        log.info("notification: {}",notification);
        notificationRepository.save(notification);
    }
}
