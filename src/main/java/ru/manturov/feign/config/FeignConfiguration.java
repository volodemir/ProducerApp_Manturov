package ru.manturov.feign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:feign.properties")
@Profile("feign")
public class FeignConfiguration {
}