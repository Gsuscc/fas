package com.codecool.fas.schedule;

import com.codecool.fas.util.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutomaticAdvertisement {

    private final EmailServiceImpl emailService;

    @Autowired
    public AutomaticAdvertisement(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 15 17 * * ?")
    public void scheduleTaskUsingCronExpression() {

        emailService.sendSimpleMessage(
                "airlinefake@gmail.com",
                "Test",
                "Test crono message"
        );

    }
}
