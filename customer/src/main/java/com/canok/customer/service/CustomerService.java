package com.canok.customer.service;

import com.canok.amqp.RabbitMQMessageProducer;
import com.canok.clients.fraud.FraudClient;
import com.canok.customer.dto.CustomerRegistrationRequest;
import com.canok.customer.entity.Customer;
import com.canok.customer.repository.CustomerRepository;
import com.canok.clients.dto.FraudCheckResponse;
import com.canok.clients.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.canok.clients.notification.NotificationClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();


        customerRepository.save(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        log.info("FraudCheckResponse:{}", fraudCheckResponse);

        if (Objects.nonNull(fraudCheckResponse) && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest request = NotificationRequest.builder()
                .customerId(customer.getId())
                .email(customer.getEmail())
                .message(String.format("Hi %s, welcome  Onboard...", customer.getFirstName()))
                .build();

        rabbitMQMessageProducer.publish(request, "internal.exchange","internal.notification.routingKey");

      /*  notificationClient.sendNotification(
                NotificationRequest.builder()
                        .customerId(customer.getId())
                        .email(customer.getEmail())
                        .message(String.format("Hi %s, welcome  Onboard...", customer.getFirstName()))
                        .build()
        ); */

    }
}
