package com.group.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "currency-api")
public class CurrencyApiProperty {

    private String baseUrl;
    private String apiKey;

}
