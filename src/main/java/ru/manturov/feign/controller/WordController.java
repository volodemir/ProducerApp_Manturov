package ru.manturov.feign.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.manturov.dto.WordDto;
import ru.manturov.feign.service.WordServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("word")
@Profile("feign")
public class WordController {
    private final WordServiceImpl service;

    @GetMapping
    @Scheduled(fixedDelayString = "${feign.producer.send.interval}")
    public ResponseEntity<WordDto> getWords() {
        return ResponseEntity.ok(service.send());
    }
}