package ru.manturov.feign.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.manturov.dto.WordDto;
import ru.manturov.modules.WordGenerator;

@Service
@AllArgsConstructor
@Slf4j
@Profile("feign")
public class WordServiceImpl implements WordService{
    private final ObjectMapper objectMapper;

    @Override
    public WordDto send() {
        WordDto wordDto = new WordDto();
        wordDto.setWord(WordGenerator.generateRndWord());
        log.info("<= sending {}", writeValueAsString(wordDto));
        return wordDto;
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