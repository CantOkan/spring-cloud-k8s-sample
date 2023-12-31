package com.canok.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long customerId;
    private String email;
    private String message;
}
