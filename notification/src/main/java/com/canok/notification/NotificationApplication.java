package com.canok.notification;


import com.canok.amqp.RabbitMQMessageProducer;
import com.canok.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {"com.canok.amqp",
                "com.canok.notification"
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
}
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer messageProducer, NotificationConfig notificationConfig) {
        return args -> {
            messageProducer.publish("foo",
                    notificationConfig.getInternalExchange(), notificationConfig.getInternalNotificationRoutingKey());

        };
    }
     */
}
