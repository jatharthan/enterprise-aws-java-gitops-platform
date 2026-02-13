package com.jatharthan;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StartApplication {

    private final MeterRegistry meterRegistry;

    // Constructor injection of MeterRegistry
    public StartApplication(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/")
    public String index(final Model model) {
        // Increment custom counter each time this endpoint is called
        meterRegistry.counter("custom_index_requests_total").increment();

        model.addAttribute("title", "Spring Boot Application Successfully Deployed");
		model.addAttribute("msg", "Built with Maven and deployed to AWS EKS via Argo CD");
		model.addAttribute("msg2", "Deployment Rollout – Testing Phase 1");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
