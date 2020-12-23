package com.capgemini.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    private static final String TRY_AGAIN = " Please try again later.";

    @GetMapping("/consultantServiceFallBack")
    public String consultantServiceFallBackMethod() {
        return "Consultant Service is taking longer than Expected." +
                TRY_AGAIN;
    }

    @GetMapping("/teamServiceFallBack")
    public String teamServiceFallBackMethod() {
        return "Team Service is taking longer than Expected." +
                TRY_AGAIN;
    }

    @GetMapping("/engagementServiceFallBack")
    public String engagementServiceFallBackMethod() {
        return "Engagement Service is taking longer than Expected." +
                TRY_AGAIN;
    }

    @GetMapping("/customerServiceFallBack")
    public String customerServiceFallBackMethod() {
        return "Customer Service is taking longer than Expected." +
                TRY_AGAIN;
    }
}
