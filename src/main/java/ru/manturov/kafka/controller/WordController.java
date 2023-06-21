package ru.manturov.kafka.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.manturov.kafka.service.WordService;

@RestController
@RequestMapping("word")
@AllArgsConstructor
@Profile("kafka")
public class WordController {
    private final WordService service;

    @PostMapping
    @Scheduled(fixedDelayString = "${kafka.producer.send.interval}")
    public void send() {
        service.send();
    }
}