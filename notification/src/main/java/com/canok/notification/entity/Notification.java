package com.canok.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Long id;

    @Column
    private String message;

    @Column
    private String sender;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "to_customer_email")
    private String toCustomerEmail;

    @Column(name = "to_customer_id")
    private Long toCustomerId;
}
