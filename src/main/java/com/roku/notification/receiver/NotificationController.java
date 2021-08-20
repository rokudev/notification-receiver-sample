package com.roku.notification.receiver;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
public class NotificationController {
    private JwtMessageService actualJwtMessageService;
    private JwtMessageService testJwtMessageService;

    @Autowired
    public NotificationController(@Qualifier("actualJwtMessageService") JwtMessageService actualJwtMessageService,
                                  @Qualifier("testJwtMessageService") JwtMessageService testJwtMessageService) {
        this.actualJwtMessageService = actualJwtMessageService;
        this.testJwtMessageService = testJwtMessageService;
    }

    @PostMapping(path = "/notification/actual",
                consumes = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void actualNotification(@RequestBody String jwtMessage) {
        this.actualJwtMessageService.processMessage(jwtMessage);
    }

    @PostMapping(path = "/notification/test",
            consumes = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void testNotification(@RequestBody String jwtMessage) {
        this.testJwtMessageService.processMessage(jwtMessage);
    }
}

