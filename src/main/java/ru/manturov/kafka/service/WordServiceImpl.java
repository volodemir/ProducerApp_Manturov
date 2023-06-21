package ru.manturov.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.manturov.modules.WordGenerator;
import ru.manturov.dto.WordDto;

@Service
@Slf4j
@Profile("kafka")
public class WordServiceImpl implements WordService {
    private final KafkaTemplate<Long, WordDto> kafkaWordTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic}")
    private String topic;

    public WordServiceImpl(KafkaTemplate<Long, WordDto> kafkaWordTemplate, ObjectMapper objectMapper) {
        this.kafkaWordTemplate = kafkaWordTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send() {
        WordDto wordDto = new WordDto();
        wordDto.setWord(WordGenerator.generateRndWord());
        log.info("<= sending {}", writeValueAsString(wordDto));
        kafkaWordTemplate.send(topic, wordDto);
    }

    private String writeValueAsString(WordDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}