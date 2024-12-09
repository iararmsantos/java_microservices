package com.iara.rabbittest;

import io.micrometer.tracing.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/cambio-service/rabbitmq")
public class RabbitMQController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMQService service;

    @Autowired
    private Tracer tracer;

    @Operation(summary = "Send message to rabbitmq ")
    @GetMapping()
    public void sendTestMessage() {
        service.sendTestMessage();
    }

    @GetMapping("/test-trace")
    public String testTrace() {
        tracer.nextSpan().name("test-trace-span").start().end();
        return "Trace sent";
    }
}
